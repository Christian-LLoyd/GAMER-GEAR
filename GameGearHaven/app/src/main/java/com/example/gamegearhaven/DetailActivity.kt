package com.example.gamegearhaven

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamegearhaven.Image
import com.example.gamegearhaven.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val image = intent.getParcelableExtra<Image>(MouseActivity.INTENT_PARCELABLE)
        val imgSrc =  findViewById<ImageView>(R.id._imageDetail)
        val imgTitle = findViewById<TextView>(R.id._imageTitle)
        val imgDesc = findViewById<TextView>(R.id._imageDesc)
        val imgtotal = findViewById<TextView>(R.id._imagetotal)
        val addtocartbtn = findViewById<Button>(R.id.addtocartbtn)


        if (image != null) {
            imgSrc.setImageResource(image.imageSrc)
            imgTitle.text = image.imageTitle
            imgDesc.text = image.imageDesc
            imgtotal.text = image.total
        }

        addtocartbtn.setOnClickListener {
            Toast.makeText(
                this,
                "Added to cart",
                Toast.LENGTH_SHORT
            ).show()

        }

    }
}