package com.example.gamegearhaven

import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    var ipEmail: EditText? = null
    var ipPassword: EditText? = null
    var btnlogin: Button? = null
    private var mAuth: FirebaseAuth? = null
    private var mloadingbar: ProgressDialog? = null
    var passwordVisible = false
    var password: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        ipEmail = findViewById<EditText>(R.id.ipEmail)
        //password para sa hide or view password in short gumawa ako ng new data type and variable para maayos
        //tignan sa ontouch listener ng password toggle.
        var password = findViewById<EditText>(R.id.ipPassword)
        //ipPassword ginawa ko to para sa if, else if, and else ng log in activity kung tinutukoy nito
        // yung id nung asa xml at para malinis na rin tignan at hindi magulo although iisa lang si
        //password and ipPassword.
        ipPassword = findViewById<EditText>(R.id.ipPassword)
        val btn = findViewById<TextView>(R.id.textViewSignUp)
        val forgotPass = findViewById<TextView>(R.id.forgotPassword)
        val btnlogin = findViewById<Button>(R.id.btnlogin)
        mAuth = FirebaseAuth.getInstance()
        mloadingbar = ProgressDialog(this@LoginActivity)
        forgotPass.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
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
        btnlogin.setOnClickListener(View.OnClickListener { checkCredentials() })
        btn.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun checkCredentials() {
        val email = ipEmail!!.text.toString()
        val password = ipPassword!!.text.toString()
        if (email.isEmpty() || email.length < 7) {
            showError(ipEmail, "Your Email is not valid!")
        } else if (password.isEmpty() || password.length < 7) {
            showError(ipPassword, "Please check your email and password")
        } else {
            mloadingbar!!.setTitle("Login")
            mloadingbar!!.setMessage("Please wait while checking your account")
            mloadingbar!!.setCanceledOnTouchOutside(false)
            mloadingbar!!.show()
            mAuth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(object : OnCompleteListener<AuthResult?> {
                    override fun onComplete(task: Task<AuthResult?>) {
                        if (task.isSuccessful()) {
                            Handler().postDelayed({
                                mloadingbar!!.setTitle("Logging in")
                                mloadingbar!!.setMessage("Please wait, while checking your credentials")
                                mloadingbar!!.setCanceledOnTouchOutside(false)
                                mloadingbar!!.show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                                mloadingbar!!.dismiss()
                            }, 3000)
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Please try again",
                                Toast.LENGTH_SHORT
                            ).show()
                            mloadingbar!!.dismiss()
                            showError(
                                ipPassword,
                                "Please Check your Email or Password and try again"
                            )
                        }
                    }
                })
        }
    }

    private fun showError(input: EditText?, s: String) {
        input!!.error = s
        input.requestFocus()
    }
}