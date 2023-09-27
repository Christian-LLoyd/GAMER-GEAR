package com.example.gamegearhaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamegearhaven.Image
import com.example.gamegearhaven.ImageAdapter
import com.example.gamegearhaven.R

class MouseActivity : AppCompatActivity() {

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mouse)
        val imagelist = listOf<Image>(
            Image(R.drawable.mouse1,"Asus Tuf Gaming","Bristling with high-refresh rate displays and competitive GPUs, ultra-durable TUF Gaming laptops deliver a reliable portable gaming experience to a wide audience of gamers.","P 1,800"),
            Image(R.drawable.mouse2,"Gigabyte m4 Rgb","It is comfortable for either hand, due to the perfect weight-balanced design, ergonomic shape and 2 pairs of side buttons. AORUS M4 boasts an enthusiast-grade 6400 dpi optical sensor (Pixart 3988), capable of 200 ips and 50G acceleration, that gives you the ultimate accuracy for competitive gaming.","P 1,400"),
            Image(R.drawable.mouse3,"Zeus M330 Gaming","Featuring swift responsiveness the Zeus M330 High Speed Gaming Mouse with Mouse Pad is engineered to provide lightning-fast and accurate movement so you can make quick and precise movements during intense gaming sessions.","P 500"),
            Image(R.drawable.mouse4,"Razer 6400 DPI","Razer mice feature the latest 5g sensor with sensitivity up to 26,000 DPI (dots per inch) and tracking up to 650 IPS (Inches per Second). The Razer Mouse is specifically designed to provide gamers with the utmost precision and speed.","P 950"),
            Image(R.drawable.mouse5,"MSI Clutch GM31","The CLUTCH GM31 LIGHTWEIGHT WIRELESS is packed with state-of-the-art low latency wireless technology and various features that provide performance boosts to your gameplay. Play endlessly with over 110 hours of battery life and fast charging dock.","P 1,600"),
        )


        val recyclerView = findViewById<RecyclerView>(R.id._imageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ImageAdapter(this,imagelist){
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE,it)
            startActivity(intent)
        }
    }
}