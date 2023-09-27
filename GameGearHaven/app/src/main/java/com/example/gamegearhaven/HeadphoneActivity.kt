package com.example.gamegearhaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamegearhaven.Image
import com.example.gamegearhaven.ImageAdapter
import com.example.gamegearhaven.R

class HeadphoneActivity : AppCompatActivity() {

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headphone)
        val imagelist = listOf<Image>(
            Image(R.drawable.headphone1,"JBL Tune 720BT Wireless","The JBL Tune 720BT headphones stream powerful JBL Pure Bass sound thanks to the latest 5.3 BT technology. Easy to use, these headphones provide up to 76 hours of pure pleasure and an extra 3 hours of battery with just 5 minutes of charge.","P 4,500"),
            Image(R.drawable.headphone2,"JBL Tune 660NC Wireless","With the Tune 660NC Noise Cancelling headphones, you'll get great sound, noise-free! Enjoy JBL Pure Bass Sound for up to 44 hours with ANC on and then recharge in a flash (just 5 minutes for 2 extra hours of battery). Out of juice? Just plug in the additional detachable cable keep the flow going.","P 5,990"),
            Image(R.drawable.headphone3,"Corsair HS65 Surround Wired Gaming","The CORSAIR HS65 SURROUND Gaming Headset delivers all-day comfort and sound with memory foam leatherette ear pads and Dolby Audio 7.1 surround sound on PC and Mac, bolstered by lightweight construction reinforced with aluminum. Sonarworks SoundID Technology personalizes your headset settings to match your audio taste.","P 4,000"),
            Image(R.drawable.headphone4,"Razer Kraken Kitty Edition Razer","The Razer Kraken Kitty Edition | RZ04-02980 features Kitty Ears powered by Razer Chroma, with 16.8 million color options for you to customize to your heart's desire. Equipped with an active noise-canceling microphone, your voice will be loud and clear for your audience.","P 4,950"),
            Image(R.drawable.headphone5,"HyperX Cloud Core","The HyperX Cloud Core includes 2 years of DTS® Headphone:X®\n" +
                    "It also features signature HyperX memory foam and soft leatherette, making it comfortable for long gaming sessions. The detachable noise-cancelling microphone keeps ambient sounds out of your voice chat and can be removed when not in use.\n","P 3,450"),
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