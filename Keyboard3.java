package com.example.gamegearhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Keyboard3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard3);
        String KeyboardTitle [] =  {"Asus Tuf Gaming","Gigabyte m4 Rgb","Zeus M330 Gaming","Razer 6400 DPI","MSI Clutch GM31"};
        String KeyboardDesc [] = {"Bristling with high-refresh rate displays and competitive GPUs, ultra-durable TUF Gaming laptops deliver a reliable portable gaming experience to a wide audience of gamers.","It is comfortable for either hand, due to the perfect weight-balanced design, ergonomic shape and 2 pairs of side buttons. AORUS M4 boasts an enthusiast-grade 6400 dpi optical sensor (Pixart 3988), capable of 200 ips and 50G acceleration, that gives you the ultimate accuracy for competitive gaming.","Featuring swift responsiveness the Zeus M330 High Speed Gaming Mouse with Mouse Pad is engineered to provide lightning-fast and accurate movement so you can make quick and precise movements during intense gaming sessions.","Razer mice feature the latest 5g sensor with sensitivity up to 26,000 DPI (dots per inch) and tracking up to 650 IPS (Inches per Second). The Razer Mouse is specifically designed to provide gamers with the utmost precision and speed.","The CLUTCH GM31 LIGHTWEIGHT WIRELESS is packed with state-of-the-art low latency wireless technology and various features that provide performance boosts to your gameplay. Play endlessly with over 110 hours of battery life and fast charging dock."};
        String KeyboardPrice [] = {"P 1,800","P 1,400","P 500","P 950","P 1,600"};
        int KeyboardImg [] = {R.drawable.keyboard1,R.drawable.keyboard2,R.drawable.keyboard3,R.drawable.keyboard4,R.drawable.keyboard5};

        ImageView imageView = findViewById(R.id.imgs);
        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);
        TextView price = findViewById(R.id.price);
        Button addtocart = findViewById(R.id.addtocart);

        imageView.setImageResource(KeyboardImg[2]);
        title.setText(KeyboardTitle[2]);
        description.setText(KeyboardDesc[2]);
        price.setText(KeyboardPrice[2]);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView title = findViewById(R.id.title);
                TextView price = findViewById(R.id.price);

                String mousetitle = title.getText().toString();
                String mouseprice = price.getText().toString();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Cart");
                databaseReference.child("8").child("title").setValue(mousetitle);
                databaseReference.child("8").child("price").setValue(mouseprice);

            }
        });
    }
}