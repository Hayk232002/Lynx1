package com.example.lynx1;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
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

    //Guidelines
    Guideline gl_btn_login_top1;
    Guideline gl_btn_login_top2;
    Guideline gl_btn_login_bottom1;
    Guideline gl_btn_login_bottom2;
    Guideline gl_et_password_bottom;
    Guideline gl_tv_forgot_top1;
    Guideline gl_tv_forgot_top2;
    Guideline gl_tv_forgot_bottom1;
    Guideline gl_tv_forgot_bottom2;

    Button btn_createacc_login;
    EditText et_email_login;
    EditText et_password_login;
    TextView tv;
    TextView tv_forgot;
    Button btn_signIn_login;
    ConstraintLayout cl_login;
    ConstraintSet constraintSet;

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

        gl_btn_login_top1 = (Guideline) findViewById(R.id.gl_btn_login_top1);
        gl_btn_login_top2 = (Guideline) findViewById(R.id.gl_btn_login_top2);
        gl_btn_login_bottom1 = (Guideline) findViewById(R.id.gl_btn_login_bottom1);
        gl_btn_login_bottom2 = (Guideline) findViewById(R.id.gl_btn_login_bottom2);
        gl_et_password_bottom = (Guideline) findViewById(R.id.gl_et_password_bottom);
        gl_tv_forgot_top1 = (Guideline) findViewById(R.id.gl_tv_forgot_top1);
        gl_tv_forgot_top2 = (Guideline) findViewById(R.id.gl_tv_forgot_top2);
        gl_tv_forgot_bottom1 = (Guideline) findViewById(R.id.gl_tv_forgot_bottom1);
        gl_tv_forgot_bottom2 = (Guideline) findViewById(R.id.gl_tv_forgot_bottom2);

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

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!getCoordinates){
                    btn_signIn_login_y = btn_signIn_login.getY();
                    tv_forgot_y = tv_forgot.getY();

                    getCoordinates = true;
                }

                if(charSequence.toString().trim().length()!=0 && count==0){
                    Log.wtf("gnal:",charSequence.toString());

                    objectAnimator_tv_forgot_gnal=ObjectAnimator.ofFloat(tv_forgot,"y",/*1050*/ 0);
                    objectAnimator_btn_login_gnal=ObjectAnimator.ofFloat(btn_signIn_login,"y",/*910*/ 0);

                    objectAnimator_btn_login_gnal.setDuration(350);
                    objectAnimator_tv_forgot_gnal.setDuration(350);
//                    objectAnimator_tv_forgot_gnal.start();
//                    objectAnimator_btn_login_gnal.start();

                    et_password_login = new EditText(Login.this);

                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(et_email_login.getWidth(), et_email_login.getHeight());

                    et_password_login.setId(EditText.generateViewId());
                    et_password_login.setLayoutParams(layoutParams);
//                    et_password_login.setX(0);
//                    et_password_login.setY(gl_et_password_y);
                    et_password_login.setPadding(et_email_login.getPaddingLeft(),et_email_login.getPaddingTop(),et_email_login.getPaddingRight(),et_email_login.getPaddingBottom());
                    et_password_login.setBackgroundResource(R.drawable.rounded_edittect_registr_login);
                    et_password_login.setHint("Password");
                    et_password_login.setInputType(/*InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT*/129);
//                    et_password_login.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    cl_login.addView(et_password_login);

                    constraintSet = new ConstraintSet();
                    constraintSet.clone(cl_login);

                    //et_password_login
                    constraintSet.connect(et_password_login.getId(),ConstraintSet.TOP,gl_btn_login_top1.getId(),ConstraintSet.BOTTOM,0);
                    constraintSet.connect(et_password_login.getId(),ConstraintSet.BOTTOM,gl_et_password_bottom.getId(),ConstraintSet.TOP,0);
                    constraintSet.connect(et_password_login.getId(),ConstraintSet.START,et_email_login.getId(),ConstraintSet.START,0);
                    constraintSet.connect(et_password_login.getId(),ConstraintSet.END,et_email_login.getId(),ConstraintSet.END,0);

                    //btn_signIn_login
                    constraintSet.clear(btn_signIn_login.getId(), ConstraintSet.TOP);
                    constraintSet.clear(btn_signIn_login.getId(), ConstraintSet.BOTTOM);
                    constraintSet.connect(btn_signIn_login.getId(),ConstraintSet.TOP,gl_btn_login_top2.getId(),ConstraintSet.BOTTOM,0);
                    constraintSet.connect(btn_signIn_login.getId(),ConstraintSet.BOTTOM,gl_btn_login_bottom2.getId(),ConstraintSet.TOP,0);

                    //tv_forgot_login
                    constraintSet.clear(tv_forgot.getId(), ConstraintSet.TOP);
                    constraintSet.clear(tv_forgot.getId(), ConstraintSet.BOTTOM);
                    constraintSet.connect(tv_forgot.getId(),ConstraintSet.TOP,gl_tv_forgot_top2.getId(),ConstraintSet.BOTTOM,0);
                    constraintSet.connect(tv_forgot.getId(),ConstraintSet.BOTTOM,gl_tv_forgot_bottom2.getId(),ConstraintSet.TOP,0);


                    et_password_login.setAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.fade_in));
                    AutoTransition autoTransition = new AutoTransition();
                    autoTransition.setDuration(200);
                    TransitionManager.beginDelayedTransition(cl_login,autoTransition);
                    constraintSet.applyTo(cl_login);

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
//                    objectAnimator_tv_forgot_gal.start();
//                    objectAnimator_btn_login_gal.start();

                    constraintSet = new ConstraintSet();
                    constraintSet.clone(cl_login);

                    //btn_signIn_login
                    constraintSet.clear(btn_signIn_login.getId(), ConstraintSet.TOP);
                    constraintSet.clear(btn_signIn_login.getId(), ConstraintSet.BOTTOM);
                    constraintSet.connect(btn_signIn_login.getId(),ConstraintSet.TOP,gl_btn_login_top1.getId(),ConstraintSet.BOTTOM,0);
                    constraintSet.connect(btn_signIn_login.getId(),ConstraintSet.BOTTOM,gl_btn_login_bottom1.getId(),ConstraintSet.TOP,0);

                    //tv_forgot_login
                    constraintSet.clear(tv_forgot.getId(), ConstraintSet.TOP);
                    constraintSet.clear(tv_forgot.getId(), ConstraintSet.BOTTOM);
                    constraintSet.connect(tv_forgot.getId(),ConstraintSet.TOP,gl_tv_forgot_top1.getId(),ConstraintSet.BOTTOM,0);
                    constraintSet.connect(tv_forgot.getId(),ConstraintSet.BOTTOM,gl_tv_forgot_bottom1.getId(),ConstraintSet.TOP,0);

                    AutoTransition autoTransition = new AutoTransition();
                    autoTransition.setDuration(200);
                    TransitionManager.beginDelayedTransition(cl_login,autoTransition);
                    constraintSet.applyTo(cl_login);

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
