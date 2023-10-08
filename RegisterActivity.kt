package com.example.gamegearhaven

import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    //Saint Benedict Victorio
    //BSIT-04

    private var inputUsername: EditText? = null
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var inputConformPassword: EditText? = null
    private var mAuth: FirebaseAuth? = null
    private var mloadingbar: ProgressDialog? = null
    var passwordVisible = false
    var password: EditText? = null
    var passwordd: EditText? = null
    var btnRegiter: Button? = null

    //FIRESTORE
    var userID: String? = null
    var fStore: FirebaseFirestore? = null

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val btn = findViewById<TextView>(R.id.alreadyHaveAccount)
        val password = findViewById<EditText>(R.id.inputPassword)
        val passwordd = findViewById<EditText>(R.id.inputConformPassword)
        inputUsername = findViewById<EditText>(R.id.inputUsername)
        inputEmail = findViewById<EditText>(R.id.inputEmail)
        inputPassword = findViewById<EditText>(R.id.inputPassword)
        inputConformPassword = findViewById<EditText>(R.id.inputConformPassword)
        val btnRegiter = findViewById<Button>(R.id.btnRegister)
        mAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()
        mloadingbar = ProgressDialog(this@RegisterActivity)

        //------------------------------------------------------------------------------------------//
        passwordd.setTransformationMethod(PasswordTransformationMethod.getInstance())
        passwordVisible = false
        passwordd.setOnTouchListener(OnTouchListener { view, motionEvent ->
            val Right = 2
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                if (motionEvent.rawX >= passwordd.getRight() - passwordd.getCompoundDrawables()[Right].bounds.width()) {
                    val selection = passwordd.getSelectionEnd()
                    passwordVisible = if (passwordVisible) {
                        //set drawble image
                        passwordd.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.baseline_visibility_off_24,
                            0
                        )
                        //set hide password
                        passwordd.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        false
                        //PasswordTransformationMethod
                        //HideReturnsTransformationMethod
                    } else {
                        //set drawble image
                        passwordd.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.baseline_visibility_24,
                            0
                        )
                        //set hide password
                        passwordd.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                        true
                    }
                    passwordd.setSelection(selection)
                    return@OnTouchListener true
                }
            }
            false
        })
        password.setTransformationMethod(PasswordTransformationMethod.getInstance())
        passwordVisible = false
        password.setOnTouchListener(OnTouchListener { view, motionEvent ->
            val Right = 2
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                if (motionEvent.rawX >= password.getRight() - password.getCompoundDrawables()[Right].bounds.width()) {
                    val selection = password.getSelectionEnd()
                    passwordVisible = if (passwordVisible) {
                        //set drawble image
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.baseline_visibility_off_24,
                            0
                        )
                        //set hide password
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        false
                        //PasswordTransformationMethod
                        //HideReturnsTransformationMethod
                    } else {
                        //set drawble image
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.baseline_visibility_24,
                            0
                        )
                        //set hide password
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                        true
                    }
                    password.setSelection(selection)
                    return@OnTouchListener true
                }
            }
            false
        })
        btnRegiter.setOnClickListener(View.OnClickListener { checkCredentials() })
        btn.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun checkCredentials() {
        val username = inputUsername!!.text.toString()
        val email = inputEmail!!.text.toString()
        val password = inputPassword!!.text.toString()
        val ConformPassword = inputConformPassword!!.text.toString()
        if (username.isEmpty() || inputUsername!!.length() > 8) {
            showError(inputUsername, "Username must 8 characters below")
        } else if (email.isEmpty() || !email.contains("@") || email.length > 20) {
            showError(inputEmail, "Email must 20 characters below")
        } else if (password.isEmpty() || password.length < 7) {
            showError(inputPassword, "Password must be 7 up!")
        } else if (ConformPassword.isEmpty() || ConformPassword != password) {
            showError(inputConformPassword, "Password is not match!")
        } else {
            mloadingbar!!.setTitle("Registeration")
            mloadingbar!!.setMessage("Please wait, while registering credentials")
            mloadingbar!!.setCanceledOnTouchOutside(false)
            mloadingbar!!.show()
            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                if (task.isSuccessful) { Toast.makeText(this@RegisterActivity, "Registration Success!", Toast.LENGTH_SHORT).show()
                    userID = mAuth!!.currentUser!!.uid
                    val documentReference = fStore!!.collection("Users").document(userID!!)
                    val user: MutableMap<String, Any> = HashMap()
                    user["email"] = email
                    user["username"] = username
                    user["password"] = password
                    documentReference.set(user).addOnSuccessListener { Log.d("TAG", "onSuccess user profile is created for $userID") }.addOnFailureListener { e -> Log.d("TAG", "onFailure: $e") }
                    Handler().postDelayed({
                        mloadingbar!!.setTitle("Registeration")
                        mloadingbar!!.setMessage("Please wait, while registering credentials")
                        mloadingbar!!.setCanceledOnTouchOutside(false)
                        mloadingbar!!.show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        mloadingbar!!.dismiss()
                    }, 3000)
                } else {
                    Toast.makeText(this@RegisterActivity, "Please try again", Toast.LENGTH_SHORT)
                        .show()
                    showError(inputEmail, "Email is already used!")
                    mloadingbar!!.dismiss()
                }
            }
        }
    }

    private fun showError(input: EditText?, s: String) {
        input!!.error = s
        input.requestFocus()
    }
}