package com.wft.sky_gym.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wft.sky_gym.Admin.AddEvent;
import com.wft.sky_gym.Admin.AddTrainer;
import com.wft.sky_gym.Admin.HomeAdmin;
import com.wft.sky_gym.Admin.UsersHelperClass;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    DatePickerDialog picker;
    ImageView pic;
    FirebaseAuth auth;
    Button update;
    DatabaseReference refrence;
    FirebaseDatabase firebaseDatabase;
    EditText gymname, gymid, gymadd, fname, lname, gender, contact, emailt, password;
    TextView dob;
    ImageView camera;
    LinearLayout l1;
    SharedPrefs sharedPrefs;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int CAMERA_REQUEST = 9999;
    private static final int PERMISSION_CODE = 1001;
    Uri imageuri;
    View view;
    FirebaseStorage storage;
    StorageReference storageReference;
    UsersHelperClass data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        sharedPrefs = new SharedPrefs(getActivity());


        data = sharedPrefs.getUserData();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        refrence = FirebaseDatabase.getInstance().getReference().child("Users");
        pic = view.findViewById(R.id.pic);
        camera = view.findViewById(R.id.camera);
        gymname = view.findViewById(R.id.gymname);
        gymadd = view.findViewById(R.id.gymadd);
        gymid = view.findViewById(R.id.gymid);
        fname = view.findViewById(R.id.fname);
        update = view.findViewById(R.id.update);
        l1 = view.findViewById(R.id.l1);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        auth = FirebaseAuth.getInstance();
        lname = view.findViewById(R.id.lname);
        gender = view.findViewById(R.id.gender);
        contact = view.findViewById(R.id.contact);
        emailt = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        dob = view.findViewById(R.id.dob);
        bindData();
        // read();
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

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//                alertDialogBuilder.setMessage("select your option");
//                alertDialogBuilder.setPositiveButton("image from gallery", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                            if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                                    == PackageManager.PERMISSION_DENIED) {
//                                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
//                                requestPermissions(permissions, PERMISSION_CODE);
//                            } else {
////permission already granted
//                                pickImageFromGallery();
//                            }
//                        } else {
//                            //version less than marshmallow
//                            pickImageFromGallery();
//                        }
//
//                    }
//
//                    //check runtime permission
//
//                });
//                alertDialogBuilder.setNegativeButton("capture image",new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(intent,CAMERA_REQUEST);
//                    }
//                });
//
//                AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();
//            //    uploadImage();
//            }
//                  });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });

        HomeAdmin.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEdtTexts();


            }
        });

        return view;

    }

    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    uploadImage();
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                       Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        imageuri=data.getData();
                         uploadImage();
                    pic.setImageBitmap(bitmap);

                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        imageuri = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        uploadImage();
                        if (imageuri != null) {
                            uploadImage();
                            Cursor cursor = getActivity().getContentResolver().query(imageuri, filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                pic.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                uploadImage();
                                cursor.close();
                            }

                        }

                    }
                    break;
            }
        }

    }


        private void uploadImage() {

            if (imageuri != null) {
                // Code for showing progressDialog while uploading
                final ProgressDialog progressDialog
                        = new ProgressDialog(getActivity());
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                // Defining the child of storageReference
                StorageReference ref = storageReference.child("profile/" + UUID.randomUUID().toString());

                // adding listeners on upload
                // or failure of image
                ref.putFile(imageuri)
                        .addOnSuccessListener(
                                new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        // Image uploaded successfully
                                        // Dismiss dialog
                                        progressDialog.dismiss();
                                        Toast.makeText(getActivity(), "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                    }
                                })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                // Error, Image not uploaded
                                progressDialog.dismiss();
                                Toast
                                        .makeText(getActivity(),
                                                "Failed " + e.getMessage(),
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .addOnProgressListener(
                                new OnProgressListener<UploadTask.TaskSnapshot>() {

                                    // Progress Listener for loading
                                    // percentage on the dialog box
                                    @Override
                                    public void onProgress(
                                            UploadTask.TaskSnapshot taskSnapshot) {
                                        double progress
                                                = (100.0
                                                * taskSnapshot.getBytesTransferred()
                                                / taskSnapshot.getTotalByteCount());
                                        progressDialog.setMessage(
                                                "Uploaded "
                                                        + (int) progress + "%");
                                    }
                                });
            }

        }



//    private void pickImageFromGallery () {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent, IMAGE_PICK_CODE);
//
//
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case PERMISSION_CODE:{
//                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                    pickImageFromGallery();
//                }
//                else{
//                    Toast.makeText(getActivity(),"permission denied",Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RESULT_OK && resultCode == IMAGE_PICK_CODE) {
//            imageuri=data.getData();
//            try {
//                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageuri);
//                pic.setImageBitmap(bitmap);
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//        else  {
//            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
//            pic.setImageBitmap(bitmap);
//        }
//
//    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void updateData() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        final String gname = gymname.getText().toString();
        final String gid = gymid.getText().toString();
        final String gadd = gymadd.getText().toString();
        final String fn = fname.getText().toString();
        final String lnamee = lname.getText().toString();
        final String dobb = dob.getText().toString();
        final String emaill = emailt.getText().toString();
        final String pass = password.getText().toString();
        final String mno = contact.getText().toString();
        final String genderr = gender.getText().toString();
        UsersHelperClass user = new UsersHelperClass(gname, gid, gadd, fn, lnamee, dobb, emaill, pass, mno, genderr);
Toast.makeText(getActivity(),"profile updated successfully",Toast.LENGTH_SHORT).show();
        reference.child(gid).setValue(user);
        sharedPrefs.createUserDataSession(user);
    }

    public void enableEdtTexts() {
        update.setVisibility(View.VISIBLE);
        HomeAdmin.editBtn.setVisibility(View.GONE);
        gymname.setEnabled(true);
        gymadd.setEnabled(true);
        gymid.setEnabled(true);
        fname.setEnabled(true);
        lname.setEnabled(true);
        dob.setEnabled(true);
        password.setEnabled(true);
        emailt.setEnabled(true);
        contact.setEnabled(true);
        gender.setEnabled(true);
    }

    private void bindData() {

        gymname.setText(data.getGname());
        fname.setText(data.getFname1());
        lname.setText(data.getLname1());
        emailt.setText(data.getEmail());
        contact.setText(data.getMobileno());
        dob.setText(data.getDob());
        password.setText(data.getPassword());
        gender.setText(data.getGender());
        gymadd.setText(data.getGadd());
        gymid.setText(data.getGid());


    }
}


//    private void read() {
//        refrence.child("gid ").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                snapshot.child("email").getValue().toString();
//                emailt.setText("email");
//                String gname=snapshot.child("gymname").getValue().toString();
//                gymname.setText(gname);
//                String gadd=snapshot.child("gymadd").getValue().toString();
//                gymadd.setText(gadd);
//                String gid=snapshot.child("gymid").getValue().toString();
//                gymid.setText(gid);
//                String pass=snapshot.child("password").getValue().toString();
//                password.setText(pass);
//                String mobile_no=snapshot.child("mobile no").getValue().toString();
//               contact .setText(mobile_no);
//               String fname1 =snapshot.child("fname").getValue().toString();
//               fname.setText(fname1);
//               String lname1=snapshot.child("lname").getValue().toString();
//               lname.setText(lname1);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//
//            }
//        });
//    }


//    private void selectImage() {
//        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Add Photo!");
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                if (options[item].equals("Take Photo")) {
//                    if (checkPermission()) {
//
//                        Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG).show();
////                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////
////                        File f = null;
////                        try {
////                            f = createImageFile();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
////                        startActivityForResult(intent, 1);
//                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
//
//                    } else {
//                        requestPermission();
//
//                        Snackbar.make(view, "Please request permission.", Snackbar.LENGTH_LONG).show();
//                    }
//
//                } else if (options[item].equals("Choose from Gallery")) {
//                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, 2);
//                } else if (options[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  // prefix
//                ".jpg",         // suffix
//                storageDir      // directory
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        String mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        return image;
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == 1) {
//                File f = new File(Environment.getExternalStorageDirectory().toString());
//                for (File temp : f.listFiles()) {
//                    if (temp.getName().equals("temp.jpg")) {
//                        f = temp;
//                        break;
//                    }
//                }
//                try {
//                    Bitmap bitmap;
//                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
//                            bitmapOptions);
//                    pic.setImageBitmap(bitmap);
//                    String path = android.os.Environment
//                            .getExternalStorageDirectory()
//                            + File.separator
//                            + "Phoenix" + File.separator + "default";
//                    f.delete();
//                    OutputStream outFile = null;
//                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
//                    try {
//                        outFile = new FileOutputStream(file);
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
//                        outFile.flush();
//                        outFile.close();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else if (requestCode == 2) {
//                Uri selectedImage = data.getData();
//
//                String[] filePath = {MediaStore.Images.Media.DATA};
//                Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
//                c.moveToFirst();
//                int columnIndex = c.getColumnIndex(filePath[0]);
//                String picturePath = c.getString(columnIndex);
//                c.close();
//                Bitmap thumbnail = BitmapFactory.decodeFile(picturePath);
//                Log.v("path of image.........", picturePath + "");
//                pic.setImageBitmap(thumbnail);
//
//
//            } else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
//                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                pic.setImageBitmap(photo);
//            }
//        }
//    }
//
//    private boolean checkPermission() {
//        //   int result = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), ACCESS_FINE_LOCATION);
//        int result1 = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), CAMERA);
//
//        return result1 == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestPermission() {
//
//        ActivityCompat.requestPermissions(getActivity(), new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0) {
//
//                    //   boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//
//                    if (cameraAccepted)
//                        Snackbar.make(view, "Permission Granted, Now you can access location data and camera.", Snackbar.LENGTH_LONG).show();
//                    else {
//
//                        Snackbar.make(view, "Permission Denied, You cannot access location data and camera.", Snackbar.LENGTH_LONG).show();
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                            requestPermissions(new String[]{CAMERA},
//                                    PERMISSION_REQUEST_CODE);
//                            return;
////                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
////                          //      showMessageOKCancel("You need to allow access to both the permissions",
////                                        new DialogInterface.OnClickListener() {
////                                            @Override
////                                            public void onClick(DialogInterface dialog, int which) {
////                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                                                    requestPermissions(new String[]{ CAMERA},
////                                                            PERMISSION_REQUEST_CODE);
////                                                }
////                                            }
////                                        };
////                                return;
////                            }
//                        }
//
//                    }
//                }
//
//
//                break;
//        }
//    }
//
