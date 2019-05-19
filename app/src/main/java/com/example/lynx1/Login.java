package com.example.lynx1;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {


    //Firebase
    private FirebaseAuth mAuth;

    Button btn_createacc_login;
    EditText et_email_login;
    EditText et_password_login;
    TextView tv;
    TextView tv_forgot;
    Button btn_signIn_login;
    ConstraintLayout cl_login;

    ObjectAnimator objectAnimator_tv_forgot_gnal;
    ObjectAnimator objectAnimator_btn_login_gnal;

    ObjectAnimator objectAnimator_tv_forgot_gal;
    ObjectAnimator objectAnimator_btn_login_gal;

    int count = 0;
    boolean getCoordinates = false;
    float btn_signIn_login_y = 0;
    float tv_forgot_y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_createacc_login = (Button) findViewById(R.id.btn_createacc_login);
        tv = (TextView) findViewById(R.id.tv);
        tv_forgot = (TextView) findViewById(R.id.tv_forgot);
        btn_signIn_login = (Button) findViewById(R.id.btn_signIn_login);
        et_email_login = (EditText) findViewById(R.id.et_email_login);
        cl_login = (ConstraintLayout) findViewById(R.id.cl_login);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        btn_createacc_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,StartPage.class);
                startActivity(intent);
            }
        });

                btn_signIn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singing(et_email_login.getText().toString(),et_password_login.getText().toString());
            }
        });

        et_email_login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!getCoordinates){
                    btn_signIn_login_y = btn_signIn_login.getY();
                    tv_forgot_y = tv_forgot.getY();

                    getCoordinates = true;
                }

                if(charSequence.toString().trim().length()!=0 && count==0){
                    Log.wtf("gnal:",charSequence.toString());

                    objectAnimator_tv_forgot_gnal=ObjectAnimator.ofFloat(tv_forgot,"y",1050);
                    objectAnimator_btn_login_gnal=ObjectAnimator.ofFloat(btn_signIn_login,"y",910);

                    objectAnimator_btn_login_gnal.setDuration(350);
                    objectAnimator_tv_forgot_gnal.setDuration(350);
                    objectAnimator_tv_forgot_gnal.start();
                    objectAnimator_btn_login_gnal.start();

                    et_password_login = new EditText(Login.this);
                    ViewGroup.LayoutParams layoutParams =(ViewGroup.LayoutParams) et_email_login.getLayoutParams();

                    et_password_login.setLayoutParams(layoutParams);
                    et_password_login.setX(0);
                    et_password_login.setY(150);
                    et_password_login.setPadding(et_email_login.getPaddingLeft(),et_email_login.getPaddingTop(),et_email_login.getPaddingRight(),et_email_login.getPaddingBottom());
                    et_password_login.setBackgroundResource(R.drawable.rounded_edittect_registr_login);
                    et_password_login.setHint("Password");
                    et_password_login.setInputType(/*InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT*/129);
//                    et_password_login.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    cl_login.addView(et_password_login);

                    count=1;
                }

                else if (charSequence.toString().trim().length()==0 && count==1){
//                    Log.wtf("gal:",charSequence.toString());
                    Log.wtf("gal:","btn:" + btn_signIn_login_y);
                    Log.wtf("gal:","tv:" + tv_forgot_y);

                    objectAnimator_tv_forgot_gal=ObjectAnimator.ofFloat(tv_forgot,"y",/*990*/ tv_forgot_y);
                    objectAnimator_btn_login_gal=ObjectAnimator.ofFloat(btn_signIn_login,"y",/*860*/ btn_signIn_login_y);

                    et_password_login.setVisibility(View.GONE);

                    objectAnimator_btn_login_gal.setDuration(350);
                    objectAnimator_tv_forgot_gal.setDuration(350);
                    objectAnimator_tv_forgot_gal.start();
                    objectAnimator_btn_login_gal.start();

                    count=0;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        tv.setText("Touch coordinates : " +
                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
        return true;
    }

    public void singing(String email,String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    if (mAuth.getCurrentUser().isEmailVerified()){
                        Intent intent = new Intent(Login.this, StartPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    else {
                        Toast.makeText(Login.this,"Please verify your email address",Toast.LENGTH_SHORT).show();
                    }
                }

                else {
                    Toast.makeText(Login.this,"Sign in failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
