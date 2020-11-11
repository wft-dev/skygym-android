package com.wft.sky_gym.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wft.sky_gym.R;

public class Register1 extends AppCompatActivity {
    EditText gymname,gymid,gymadd,fname,lname;
    RelativeLayout layout;
    ImageView next;
    ProgressBar progress;
    FirebaseDatabase database;
    TextView back,tv1,tv2,tv3,tv4,tv5;
     FirebaseAuth firebaseAuth;
     DatabaseReference databaseReference;
     UsersHelperClass users;
    String gname,gid,gadd,fname1,lname1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.register1);
        gymname=findViewById(R.id.gymname);
        gymid=findViewById(R.id.gymid);
        gymadd=findViewById(R.id.gymadd);
        firebaseAuth=FirebaseAuth.getInstance();
        fname=findViewById(R.id.fname);        lname=findViewById(R.id.lname);
        next=findViewById(R.id.next);
        back=findViewById(R.id.back);
        progress=findViewById(R.id.progress);
        layout=findViewById(R.id.layout);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        users=new UsersHelperClass();

        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("Users");
        firebaseAuth=FirebaseAuth.getInstance();

        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideKeyboard(view);
                return false;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final String gname=gymname.getText().toString();
//                final String gid=gymid.getText().toString();
//                final  String gadd=gymadd.getText().toString();
//                final String fname1=fname.getText().toString();
//                final String lname1= lname.getText().toString();


                if (gymname.getText().toString().length() == 0) {
                    tv1.setVisibility(View.VISIBLE);

                    Animation shake = AnimationUtils.loadAnimation(Register1.this, R.anim.shake);
                    gymname.startAnimation(shake);


                } else if (gymid.getText().toString().length() == 0) {
                    tv2.setVisibility(View.VISIBLE);

                    Animation shake = AnimationUtils.loadAnimation(Register1.this, R.anim.shake);
                    gymid.startAnimation(shake);
                    Toast.makeText(Register1.this,"enter GymId",Toast.LENGTH_SHORT).show();

                }
                else if (gymadd.getText().toString().length()==0){
                    tv3.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(Register1.this, R.anim.shake);
                    gymadd.startAnimation(shake);
                    Toast.makeText(Register1.this,"enter Gym Address",Toast.LENGTH_SHORT).show();
                }
                else if (fname.getText().toString().length()==0){
                    tv4.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(Register1.this, R.anim.shake);
                    fname.startAnimation(shake);
                    Toast.makeText(Register1.this,"enter First Name",Toast.LENGTH_SHORT).show();
                }
                else if(lname.getText().toString().length()==0){
                    tv5.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(Register1.this, R.anim.shake);
                    lname.startAnimation(shake);
                    Toast.makeText(Register1.this,"enter Last Name",Toast.LENGTH_SHORT).show();
                }


                else {
                    progress.setVisibility(View.VISIBLE);
//                    register();
                    tv5.setVisibility(View.GONE);
                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    tv4.setVisibility(View.GONE);
                    Intent i=new Intent(Register1.this,Register2.class);
                     gname=gymname.getText().toString();
                    i.putExtra("name",gname);
                     gadd=gymadd.getText().toString();
                    i.putExtra("add",gadd);
                     gid=gymid.getText().toString();
                    i.putExtra("id",gid);
                     fname1=fname.getText().toString();
                    i.putExtra("fname",fname1);
                     lname1=lname.getText().toString();
                    i.putExtra("lname",lname1);
                    startActivity(i);
                    finish();



                }
            }
        });


back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent i=new Intent(Register1.this,LoginAdmin.class);

        startActivity(i);
    }
});
    }

//    private void register() {
//
//                    databaseReference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            getValues();
//                            databaseReference.push().setValue(users);
//        progress.setVisibility(View.GONE);
//        Intent i=new Intent(Register1.this,Register2.class);
//        startActivity(i);
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            progress.setVisibility(View.GONE);
//                            Toast.makeText(Register1.this,"data not entered",Toast.LENGTH_SHORT).show();
//
//                        }
//
//
//                    });
//
//

//    }

    //    private void register(final String gname, final String gid, final String gadd, final String fname1, final String lname1) {
//        firebaseAuth.createUserWithEmailAndPassword(fname1,lname1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    FirebaseUser rUser=firebaseAuth.getCurrentUser();
//                    String userid=rUser.getUid();
//                    databaseReference= FirebaseDatabase.getInstance().getReference("Users1").child(userid);
//
//                    HashMap<String,String> hashMap=new HashMap<>();
//                    hashMap.put("userid",userid);
//                    hashMap.put("fname",fname1);
//                    hashMap.put("lname",lname1);
//                    hashMap.put("gymname",gname);
//                    hashMap.put("gymid",gid);
//                    hashMap.put("gymadd",gadd);
//                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()){
//                                progress.setVisibility(View.GONE);
//                                Intent i = new Intent(Register1.this, HomeAdmin.class);
//                                startActivity(i);
//
//
//                            }
//                            else{
//                                progress.setVisibility(View.GONE);
//                                Toast.makeText(Register1.this,"registration is not completed",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }else{
//                    progress.setVisibility(View.GONE);
//                    Toast.makeText(Register1.this,"data not accepted",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//    }
//    private void getValues(){
//
//        users.setFname(fname.getText().toString());
//        users.setGadd(gymadd.getText().toString());
//        users.setGid(gymid.getText().toString());
//        users.setGname(gymname.getText().toString());
//        users.setLname(lname.getText().toString());
//
//    }


    private void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


}
