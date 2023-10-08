package com.example.gamegearhaven

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val btn1 = findViewById<ImageView>(R.id.mouse)
        val btn2 = findViewById<ImageView>(R.id.keyboard)
        val btn3 = findViewById<ImageView>(R.id.headset)
        val btn4 = findViewById<ImageView>(R.id.monitor)
        val profile = findViewById<TextView>(R.id.profile);
        val cart = findViewById<TextView>(R.id.cart);


        btn1.setOnClickListener {
            val intent = Intent(this@MainActivity, MouseActivity::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener {
            val intent = Intent(this@MainActivity, KeyboardActivity::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener {
            val intent = Intent(this@MainActivity, HeadphoneActivity::class.java)
            startActivity(intent)
        }
        btn4.setOnClickListener {
            val intent = Intent(this@MainActivity, MonitorActivity::class.java)
            startActivity(intent)
        }
        profile.setOnClickListener {
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(intent)
        }
        cart.setOnClickListener {
            val intent = Intent(this@MainActivity, ShowCart::class.java)
            startActivity(intent)
        }


    }
}