package com.example.gamegearhaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamegearhaven.Image
import com.example.gamegearhaven.ImageAdapter
import com.example.gamegearhaven.R

class MonitorActivity : AppCompatActivity() {

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor)
        val imagelist = listOf<Image>(
            Image(R.drawable.monitor1,"Samsung 32 Odyssey G7 Gaming Monitor","Conquer every enemy, even at soaring speeds. 240Hz RapidCurve eliminates lag for exhilarating gameplay with ultra-smooth action. Jump on enemies right when you see them with a 1ms response time, precise mouse movements, and blur-free frames with no ghosting.","P 38,000"),
            Image(R.drawable.monitor2,"LG 22MP410-B 21.45","21.45-inch Full HD (1920x1080) Display delivers precise and clear imagery through outstanding colour accuracy. Reader Mode adjusts colour temperature and luminance similar to reading a paper book. Flicker Safe provides a comfortable working environment for a long time.","P 4,500"),
            Image(R.drawable.monitor3,"HP M24F 2E2Y4AA 23.8","FHD display with IPS technology, 99% sRGB color gamut and FreeSync. Hardware-integrated low blue light technology that doesn't sacrifice color quality. Monitor designed with 85% recycled materials and sustainably packaged. Slim profile, innovative cable containment and seamless design for side-by-side screens.","P 6,950"),
            Image(R.drawable.monitor4,"Asus TUF VG30VQL1A 29.5","The TUF Gaming VG30VQL1A is a 29.5-inch WFHD (2560 x 1080) 1500R curved gaming monitor with an ultrafast 200 Hz refresh rate and 1 ms (MPRT) response time for extremely immersive gameplay. It features exclusive Extreme Low Motion Blur (ELMB) technology and AMD FreeSync™ Premium to eliminate ghosting and tearing.","P 500"),
            Image(R.drawable.monitor5,"Nvision  24  Inch Ips Monitor 75Hz Frameless","The Nvision N2455 24-inch Monitor features a 75Hz refresh rate while rocking a 1ms response time. The N2455 24-inch Monitor has dimensions of 541 x 412 x 149mm and weighs in at 2.9kg. It also has a 178° screen with a display resolution of 1920 x 1080pixels for you to enjoy all types of entertainment!","P 3,200"),
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