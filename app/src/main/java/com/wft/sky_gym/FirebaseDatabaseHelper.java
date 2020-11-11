package com.wft.sky_gym;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wft.sky_gym.Admin.UsersHelperClass;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<UsersHelperClass> users= new ArrayList<>();
    public FirebaseDatabaseHelper(){
        firebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");

    }
    public void Readusers(final DataStatus dataStatus){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                List<String> Keys = new ArrayList<>();
                for (DataSnapshot KeyNode:snapshot.getChildren()){
                    Keys.add(KeyNode.getKey());
                    UsersHelperClass user=KeyNode.getValue(UsersHelperClass.class);
                    users.add(user);
                }
                dataStatus.DataIsLoaded(users,Keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public  void Addusers(UsersHelperClass user, final DataStatus dataStatus){
       String Key= databaseReference.push().getKey();
       databaseReference.child(Key).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               dataStatus.DataIsInserted();
           }
       });
    }
}
