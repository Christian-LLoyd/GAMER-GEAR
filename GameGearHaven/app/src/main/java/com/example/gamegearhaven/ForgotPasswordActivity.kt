package com.example.gamegearhaven

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mloadingbar: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val accountreg = findViewById<TextView>(R.id.accountreg)
        val backbtn = findViewById<TextView>(R.id.backbtn)
        val resetemail = findViewById<EditText>(R.id.resetemail)
        val resetbtn = findViewById<Button>(R.id.resetbtn)
        mAuth = FirebaseAuth.getInstance()
        mloadingbar = ProgressDialog(this@ForgotPasswordActivity)
        accountreg.setOnClickListener {
            val intent = Intent(this@ForgotPasswordActivity, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        backbtn.setOnClickListener {
            val intent = Intent(this@ForgotPasswordActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        resetbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                checkCredentials()
            }

            private fun checkCredentials() {
                val reset = resetemail.text.toString()
                if (reset.isEmpty()) {
                    showError(resetemail, "Email is not valid")
                } else if (reset.length < 7) {
                    showError(resetemail, "Please try again")
                } else {
                    mloadingbar!!.setTitle("Resetting Password")
                    mloadingbar!!.setMessage("Please wait, while checking your credentials")
                    mloadingbar!!.setCanceledOnTouchOutside(false)
                    mloadingbar!!.show()
                    mAuth!!.sendPasswordResetEmail(reset).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                "Please check your email",
                                Toast.LENGTH_SHORT
                            ).show()
                            mloadingbar!!.dismiss()
                        } else {
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                "Email is not valid",
                                Toast.LENGTH_SHORT
                            ).show()
                            showError(resetemail, "Please try again")
                            mloadingbar!!.dismiss()
                        }
                    }
                }
            }

            private fun showError(input: EditText, s: String) {
                input.error = s
                input.requestFocus()
            }
        })
    }
}