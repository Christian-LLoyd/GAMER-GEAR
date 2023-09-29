package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {
    //Saint Benedict Victorio
    //BSIT-04
    DatabaseReference databaseReferences = FirebaseDatabase.getInstance().getReferenceFromUrl("https://database-application-3079e-default-rtdb.firebaseio.com/");
    private EditText inputUsername,inputEmail,inputPassword,inputConformPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog mloadingbar;
    boolean passwordVisible;

    EditText password,passwordd;
    Button btnRegiter;

    //FIRESTORE
    String userID;
    FirebaseFirestore fStore;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView btn = findViewById(R.id.alreadyHaveAccount);
        password = findViewById(R.id.inputPassword);
        passwordd = findViewById(R.id.inputConformPassword);
        inputUsername=findViewById(R.id.inputUsername);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        inputConformPassword=findViewById(R.id.inputConformPassword);
        btnRegiter=findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mloadingbar = new ProgressDialog(RegisterActivity.this);

        //------------------------------------------------------------------------------------------//
        passwordd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        passwordVisible=false;
        passwordd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction()==motionEvent.ACTION_UP){
                    if(motionEvent.getRawX()>=passwordd.getRight()-passwordd.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = passwordd.getSelectionEnd();
                        if(passwordVisible){
                            //set drawble image
                            passwordd.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            //set hide password
                            passwordd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                            //PasswordTransformationMethod
                            //HideReturnsTransformationMethod
                        }
                        else{
                            //set drawble image
                            passwordd.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            //set hide password
                            passwordd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        passwordd.setSelection(selection);
                        return true;
                    }
                }

                return false;
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



        btnRegiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void checkCredentials() {
        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String ConformPassword = inputConformPassword.getText().toString();

        if(username.isEmpty() || inputUsername.length()>8){
            showError(inputUsername,"Username must 8 characters below");
        }
        else if(email.isEmpty() || !email.contains("@") ||email.length()>20)
        {
            showError(inputEmail,"Email must 20 characters below");
        }
        else if (password.isEmpty() || password.length()<7)
        {
            showError(inputPassword,"Password must be 7 up!");
        }
        else if(ConformPassword.isEmpty() || !ConformPassword.equals(password))
        {
            showError(inputConformPassword,"Password is not match!");
        }
        else
        {
           mloadingbar.setTitle("Registeration");
           mloadingbar.setMessage("Please wait, while registering credentials");
           mloadingbar.setCanceledOnTouchOutside(false);
           mloadingbar.show();

           mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {

                   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().push();
                   databaseReference.child("Registered Account").child("Username").setValue(username);
                   databaseReference.child("Registered Account").child("Email").setValue(email);
                   databaseReference.child("Registered Account").child("Password").setValue(password);

                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Registration Success!",Toast.LENGTH_SHORT).show();


                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("Users").document(userID);
                        Map<String,Object> user = new HashMap<>();
                        user.put("email",email);
                        user.put("username",username);

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG","onSuccess user profile is created for " + userID);
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "onFailure: "+e.toString());
                            }
                        });


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mloadingbar.setTitle("Registeration");
                                mloadingbar.setMessage("Please wait, while registering credentials");
                                mloadingbar.setCanceledOnTouchOutside(false);
                                mloadingbar.show();
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                mloadingbar.dismiss();
                            }
                        },3000);


                    }
                    else {
                        Toast.makeText(RegisterActivity.this,"Please try again",Toast.LENGTH_SHORT).show();
                        showError(inputEmail,"Email is already used!");
                        mloadingbar.dismiss();
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