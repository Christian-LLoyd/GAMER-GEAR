package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText ipEmail,ipPassword;
    Button btnlogin;
    private FirebaseAuth mAuth;
    private ProgressDialog mloadingbar;
    boolean passwordVisible;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ipEmail=findViewById(R.id.ipEmail);
        //password para sa hide or view password in short gumawa ako ng new data type and variable para maayos
        //tignan sa ontouch listener ng password toggle.
        password=findViewById(R.id.ipPassword);
        //ipPassword ginawa ko to para sa if, else if, and else ng log in activity kung tinutukoy nito
        // yung id nung asa xml at para malinis na rin tignan at hindi magulo although iisa lang si
        //password and ipPassword.
        ipPassword=findViewById(R.id.ipPassword);
        TextView btn = findViewById(R.id.textViewSignUp);
        TextView forgotPass = findViewById(R.id.forgotPassword);
        btnlogin=findViewById(R.id.btnlogin);
        mAuth=FirebaseAuth.getInstance();
        mloadingbar= new ProgressDialog(LoginActivity.this);


        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            }
        });

        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        passwordVisible=false;
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction()==motionEvent.ACTION_UP){
                    if(motionEvent.getRawX()>=password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = password.getSelectionEnd();
                        if(passwordVisible){
                            //set drawble image
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            //set hide password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                            //PasswordTransformationMethod
                            //HideReturnsTransformationMethod
                        }
                        else{
                            //set drawble image
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            //set hide password
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();

            }
        });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }

    private void checkCredentials() {
        String email = ipEmail.getText().toString();
        String password = ipPassword.getText().toString();


        if (email.isEmpty() || email.length() < 7) {
            showError(ipEmail, "Your Email is not valid!");
        }
        else if (password.isEmpty() || password.length() < 7) {
            showError(ipPassword, "Please check your email and password");
        }
        else{
            mloadingbar.setTitle("Login");
            mloadingbar.setMessage("Please wait while checking your account");
            mloadingbar.setCanceledOnTouchOutside(false);
            mloadingbar.show();

            mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mloadingbar.setTitle("Logging in");
                                mloadingbar.setMessage("Please wait, while checking your credentials");
                                mloadingbar.setCanceledOnTouchOutside(false);
                                mloadingbar.show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                mloadingbar.dismiss();
                            }
                        },3000);
                    }

                    else{
                        Toast.makeText(LoginActivity.this,"Please try again",Toast.LENGTH_SHORT).show();
                        mloadingbar.dismiss();
                        showError(ipPassword,"Please Check your Email or Password and try again");
                    }
                }
            });
        }
    }



    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}

