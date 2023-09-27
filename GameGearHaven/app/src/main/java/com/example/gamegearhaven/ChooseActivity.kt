package com.example.gamegearhaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        val btn1 = findViewById<ImageView>(R.id.mouse)
        val btn2 = findViewById<ImageView>(R.id.keyboard)
        val btn3 = findViewById<ImageView>(R.id.headphone)
        val btn4 = findViewById<ImageView>(R.id.monitor)
        val cart = findViewById<ImageView>(R.id.cart)

        btn1.setOnClickListener {
            val intent = Intent(this,MouseActivity::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener {
            val intent = Intent(this,KeyboardActivity::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener {
            val intent = Intent(this,HeadphoneActivity::class.java)
            startActivity(intent)
        }
        btn4.setOnClickListener {
            val intent = Intent(this,MonitorActivity::class.java)
            startActivity(intent)
        }

        cart.setOnClickListener {
            val intent = Intent (this,AddToCart::class.java)
            startActivity(intent)
        }
    }
}