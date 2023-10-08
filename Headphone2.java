package com.example.gamegearhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Headphone2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headphone2);
        String HeadphoneTitle [] =  {"JBL Tune 720","JBL Tune 660NC","Corsair HS65","Razer Kraken","HyperX Cloud Core"};
        String HeadphoneDesc [] = {"The JBL Tune 720BT headphones stream powerful JBL Pure Bass sound thanks to the latest 5.3 BT technology.","With the Tune 660NC Noise Cancelling headphones, you'll get great sound, noise-free! Enjoy JBL Pure Bass Sound for up to 44 hours with ANC on and then recharge in a flash (just 5 minutes for 2 extra hours of battery).","The CORSAIR HS65 SURROUND Gaming Headset delivers all-day comfort and sound with memory foam leatherette ear pads and Dolby Audio 7.1 surround sound on PC and Mac, bolstered by lightweight construction reinforced with aluminum.","The Razer Kraken Kitty Edition | RZ04-02980 features Kitty Ears powered by Razer Chroma, with 16.8 million color options for you to customize to your heart's desire. Equipped with an active noise-canceling microphone.","It also features signature HyperX memory foam and soft leatherette, making it comfortable for long gaming sessions. The detachable noise-cancelling microphone keeps ambient sounds out of your voice chat and can be removed when not in use."};
        String HeadphonePrice [] = {"P 4,500","P 5,900","P 4,000","P 4,950","P 3,500"};
        int HeadphoneImg [] = {R.drawable.headphone1,R.drawable.headphone2,R.drawable.headphone3,R.drawable.headphone4,R.drawable.headphone5};

        ImageView imageView = findViewById(R.id.imgs);
        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);
        TextView price = findViewById(R.id.price);
        Button addtocart = findViewById(R.id.addtocart);

        imageView.setImageResource(HeadphoneImg[1]);
        title.setText(HeadphoneTitle[1]);
        description.setText(HeadphoneDesc[1]);
        price.setText(HeadphonePrice[1]);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView title = findViewById(R.id.title);
                TextView price = findViewById(R.id.price);

                String mousetitle = title.getText().toString();
                String mouseprice = price.getText().toString();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Cart");
                databaseReference.child("12").child("title").setValue(mousetitle);
                databaseReference.child("12").child("price").setValue(mouseprice);

            }
        });
    }
}