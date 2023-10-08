package com.example.gamegearhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MouseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);

        String Mouse [] =  {"Asus Tuf Gaming","Gigabyte m4 Rgb","Zeus M330 Gaming","Razer 6400 DPI","MSI Clutch GM31"};
        String MouseDesc [] = {"Bristling with high-refresh rate displays and competitive GPUs, ultra-durable TUF Gaming laptops deliver a reliable portable gaming experience to a wide audience of gamers.","It is comfortable for either hand, due to the perfect weight-balanced design, ergonomic shape and 2 pairs of side buttons. AORUS M4 boasts an enthusiast-grade 6400 dpi optical sensor (Pixart 3988), capable of 200 ips and 50G acceleration, that gives you the ultimate accuracy for competitive gaming.","Featuring swift responsiveness the Zeus M330 High Speed Gaming Mouse with Mouse Pad is engineered to provide lightning-fast and accurate movement so you can make quick and precise movements during intense gaming sessions.","Razer mice feature the latest 5g sensor with sensitivity up to 26,000 DPI (dots per inch) and tracking up to 650 IPS (Inches per Second). The Razer Mouse is specifically designed to provide gamers with the utmost precision and speed.","The CLUTCH GM31 LIGHTWEIGHT WIRELESS is packed with state-of-the-art low latency wireless technology and various features that provide performance boosts to your gameplay. Play endlessly with over 110 hours of battery life and fast charging dock."};
        String MousePrice [] = {"P 1,800","P 1,400","P 500","P 950","P 1,600"};
        int MouseImg [] = {R.drawable.mouse1,R.drawable.mouse2,R.drawable.mouse3,R.drawable.mouse4,R.drawable.mouse5};

        ImageView mouse1 = findViewById(R.id.mouse1);
        ImageView mouse2 = findViewById(R.id.mouse2);
        ImageView mouse3 = findViewById(R.id.mouse3);
        ImageView mouse4 = findViewById(R.id.mouse4);
        ImageView mouse5 = findViewById(R.id.mouse5);

        mouse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MouseActivity.this, Mouse1.class);
                startActivity(intent);
            }
        });

        mouse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MouseActivity.this, Mouse2.class);
                startActivity(intent);
            }
        });

        mouse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MouseActivity.this, Mouse3.class);
                startActivity(intent);
            }
        });

        mouse4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MouseActivity.this, Mouse4.class);
                startActivity(intent);
            }
        });

        mouse5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MouseActivity.this, Mouse5.class);
                startActivity(intent);
            }
        });
    }
}