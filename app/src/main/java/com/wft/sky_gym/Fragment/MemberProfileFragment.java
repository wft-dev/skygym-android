package com.wft.sky_gym.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wft.sky_gym.Admin.MemberHelper;
import com.wft.sky_gym.Admin.UsersHelperClass;
import com.wft.sky_gym.Admin.ViewMember;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberProfileFragment extends Fragment {

    FirebaseAuth auth;

    DatabaseReference refrence;
    FirebaseDatabase firebaseDatabase;


    SharedPrefs sharedPrefs;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int CAMERA_REQUEST = 9999;
    private static final int PERMISSION_CODE = 1001;
    DatePickerDialog picker;
    LinearLayout l1;
    EditText mname,mid,gender, password, email, tname, address, contact;

    TextView doj, dob;
    Button next, update;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    MemberHelper member;
    ImageView camera, uploadid;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";

    Uri imageuri;
    View view;
    FirebaseStorage storage;
    StorageReference storageReference;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_memberprofile, container, false);
        l1 = view.findViewById(R.id.l1);
        mid = view.findViewById(R.id.mid);
        next=view.findViewById(R.id.next);
        gender = view.findViewById(R.id.gender);
        password = view.findViewById(R.id.password);
        email = view.findViewById(R.id.email);
        tname = view.findViewById(R.id.tname);
        address = view.findViewById(R.id.address);
        contact = view.findViewById(R.id.contact);
        doj = view.findViewById(R.id.doj);
        dob = view.findViewById(R.id.dob);
        update=view.findViewById(R.id.update);
        database=FirebaseDatabase.getInstance();
        mname= view.findViewById(R.id.membername);
        databaseReference=database.getReference("Member");
        member=new MemberHelper();
//        member = sharedPrefs.getMemberData();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        bindData();



        camera = view.findViewById(R.id.camera);
        uploadid = view.findViewById(R.id.uploadid);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        doj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                doj.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String memberid=mid.getText().toString();
               String joining=doj.getText().toString();
               String gen=gender.getText().toString();
               String pass=password.getText().toString();
               String trainername=tname.getText().toString();
               String email1=email.getText().toString();
               String add= address.getText().toString();
              String mobileno= contact.getText().toString();
              String dobirth=dob.getText().toString();
              String memname= mname.getText().toString();

                if (mid.getText().toString().trim().length() == 0) {
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    mid.startAnimation(shake);

                } else if (gender.getText().toString().trim().length() == 0) {
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    gender.startAnimation(shake);
                } else if (password.getText().toString().trim().length() == 0) {
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    password.startAnimation(shake);
                } else if (email.getText().toString().trim().length() == 0 || !checkEmailPatern(email.getText().toString())) {
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    email.startAnimation(shake);

                } else if (tname.getText().toString().trim().length() == 0) {
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    tname.startAnimation(shake);
                } else if (address.getText().toString().trim().length() == 0) {
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    email.startAnimation(shake);
                } else if (contact.getText().toString().trim().length() == 0 || !checkContactPatern(contact.getText().toString())) {
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    contact.startAnimation(shake);
                } else if (doj.getText().toString().trim().length() == 0) {
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    doj.startAnimation(shake);
                } else if (dob.getText().toString().trim().length() == 0) {
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    dob.startAnimation(shake);
                } else {
                    MemberHelper member=new MemberHelper(memname,memberid,joining,gen,pass,trainername,email1,add,mobileno,dobirth);
                    databaseReference.child(memberid).setValue(member);
                    Intent i= new Intent(getActivity(), ViewMember.class);
                    SharedPrefs sharedPreferences = new SharedPrefs(getActivity());
                    sharedPreferences.createMemberDataSession(member);

                    startActivity(i);

//                    MemberMembershipFragment memberMembershipFragment=new MemberMembershipFragment();
//                    Bundle bundle=new Bundle();
//                  bundle.putString("memberid", mid.getText().toString());
//                  bundle.putString(" joining",doj.getText().toString());
//                   bundle.putString("gen",gender.getText().toString());
//                  bundle.putString("pass",password.getText().toString());
//                   bundle.putString("trainername",tname.getText().toString());
//                    bundle.putString("email",email.getText().toString());
//                   bundle.putString("address",address.getText().toString());
//                   bundle.putString("contact",contact.getText().toString());
//                   bundle.putString("dob",dob.getText().toString());
//                    memberMembershipFragment.setArguments(bundle);
//                   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, memberMembershipFragment).commit();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedata();
            }
        });
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), HomeAdmin.class);
//                startActivity(i);
//            }
//        });
        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                hideKeyboard(view);
                return false;
            }
        });


        return view;
    }

    private void updatedata() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");

        final String memname = mname.getText().toString();
        final String memberid = mid.getText().toString();
        final String joining = doj.getText().toString();
        final String gen = gender.getText().toString();
        final String trainername = tname.getText().toString();
        final String email1 = email.getText().toString();
        final String add = address.getText().toString();
        final String mobileno = contact.getText().toString();
        final String dobirth = contact.getText().toString();
        final String pass = password.getText().toString();
        MemberHelper member=new MemberHelper(memname,memberid,joining,gen,pass,trainername,email1,add,mobileno,dobirth);

        reference.child(memberid).setValue(member);
        sharedPrefs.createMemberDataSession(member);
    }

    private void bindData() {
        mname.setText(member.getMemname());
        mid.setText(member.getMemid());
        contact.setText(member.getContact());
        doj.setText(member.getDoj());
        dob.setText(member.getDob());
        address.setText(member.getAddress());
        gender.setText(member.getGender());
        password.setText(member.getPassword());
        email.setText(member.getEmail());
        tname.setText(member.getTname());



    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }
    public boolean checkContactPatern(String contact) {
        Pattern pattern = Pattern.compile(MobilePattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }
    public boolean checkEmailPatern(String email) {
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}





