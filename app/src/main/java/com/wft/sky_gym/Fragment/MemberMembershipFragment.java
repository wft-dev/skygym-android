package com.wft.sky_gym.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wft.sky_gym.Admin.HomeAdmin;
import com.wft.sky_gym.Admin.MemberHelper;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.Calendar;

public class MemberMembershipFragment  extends  Fragment{
    EditText membershipplans,membershipdetails,amount,totalamount,discount,paymenttype,dueamount;
    TextView startdate,enddate;
    Button update;
    View view;
    DatePickerDialog picker;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String  memberid,joining,gen,pass,trainername,email1,add,mobileno,dobirth;


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.fragment_membermembership, container, false);
            firebaseAuth=FirebaseAuth.getInstance();
            membershipplans=view.findViewById(R.id.membershipplans);
            membershipdetails=view.findViewById(R.id.mmembershipdetails);
            amount=view.findViewById(R.id.amount);
            totalamount=view.findViewById(R.id.totalamount);
            discount=view.findViewById(R.id.discount);
            paymenttype=view.findViewById(R.id.paymenttype);
            dueamount=view.findViewById(R.id.dueamount);
            startdate=view.findViewById(R.id.startdate);
            enddate=view.findViewById(R.id.enddate);
            update=view.findViewById(R.id.update);


//          memberid=getArguments().getString("memberid");
//          joining=getArguments().getString("joining");
//          gen=getArguments().getString("gen");
//          pass=getArguments().getString("pass");
//          trainername=getArguments().getString("trainername");
//          email1=getArguments().getString("email");
//          add=getArguments().getString("address");
//          mobileno=getArguments().getString("contact");
                          Bundle bundle = getArguments();
                                  if(bundle!= null)
                                  {
                                      memberid=getArguments().getString("memberid");
                                      joining=getArguments().getString("joining") ;
                                      gen= getArguments().getString("gen");
                                      pass=getArguments().getString("pass");
                                      trainername=getArguments().getString("trainername");
                                      email1=getArguments().getString("email")   ;
                                      add=getArguments().getString("address");
                                      mobileno=getArguments().getString("contact")  ;
                                  }


            startdate.setOnClickListener(new View.OnClickListener() {
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
                                    startdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                }
            });
           enddate .setOnClickListener(new View.OnClickListener() {
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
                                    enddate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                }
            });
           update.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                 
                   firebaseDatabase=FirebaseDatabase.getInstance();
                   databaseReference=firebaseDatabase.getReference("Member");
                   //get all the values


                                          String mplan=membershipplans.getText().toString();
                                          String mdetails=membershipdetails.getText().toString();
                                          String amount1=amount.getText().toString();
                                          String sdate=startdate.getText().toString();
                                          String edate= enddate.getText().toString();
                                          String tamount=totalamount.getText().toString();
                                          String dicount=discount.getText().toString();
                                          String paytype=paymenttype.getText().toString();
                                          String damount= dueamount.getText().toString();






                   Intent i = new Intent(getActivity(), HomeAdmin.class);
                   startActivity(i);
               }
           });

//           update.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   firebaseDatabase=FirebaseDatabase.getInstance();
//                   databaseReference=firebaseDatabase.getReference("Member");
//               }
//           });
return view;
        }

    }


