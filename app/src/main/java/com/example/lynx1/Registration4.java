package com.example.lynx1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration4 extends AppCompatActivity {

    String first;
    String last;
    String day;
    String month;
    String year;
    String sex;

    //Firebase
    private FirebaseAuth mAuth;
    private Uri selectedImageUri;

    Button btn_next_reg4;
    EditText et_phone_reg4;
    EditText et_email_reg4;
    EditText et_password_reg4;
    EditText et_reappass_reg4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration4);

        Intent intent=getIntent();
        first = intent.getStringExtra("first");
        last = intent.getStringExtra("last");
        day = intent.getStringExtra("day");
        month = intent.getStringExtra("month");
        year = intent.getStringExtra("year");
        sex = intent.getStringExtra("sex");

        btn_next_reg4 = (Button) findViewById(R.id.btn_next_reg4);
        et_phone_reg4 = (EditText) findViewById(R.id.et_phone_reg4);
        et_email_reg4 = (EditText) findViewById(R.id.et_email_reg4);
        et_password_reg4 = (EditText) findViewById(R.id.et_password_reg4);
        et_reappass_reg4 = (EditText) findViewById(R.id.et_reappass_reg4);

        mAuth = FirebaseAuth.getInstance();

        btn_next_reg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_password_reg4.getText().toString().equals(et_reappass_reg4.getText().toString())) {
                    registration(et_email_reg4.getText().toString(), et_password_reg4.getText().toString());
                }

                else {
                    Toast.makeText(Registration4.this,"The passwords do not match",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void registration(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (/*selectedImageUri !=null &&*/ task.isSuccessful()){
//                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//                                final StorageReference ref = storageReference.child(user.getUid());
//                                ref.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                    @Override
//                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                            @Override
//                                            public void onSuccess(Uri uri) {
//                                                downloadUri=uri;
//                                                Toast.makeText(ARegisterActivity.this,"Registered successfully. Please check your email for verification",Toast.LENGTH_SHORT).show();
//
//                                                FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).setValue(
//                                                        new UserInformation(downloadUri.toString(),user.getUid(),et_username_main.getText().toString()));
//
//                                                Intent intent = new Intent(ARegisterActivity.this,BLoginActivity.class);
//                                                startActivity(intent);
//                                            }
//                                        });
//                                    }
//                                });

                                Log.wtf("email","email@ lava");

                                Intent intent = new Intent(Registration4.this,Registration5.class);
                                startActivity(intent);
                            }

                            else {
                                Toast.makeText(Registration4.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                else {
                    Toast.makeText(Registration4.this,"Registration failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
