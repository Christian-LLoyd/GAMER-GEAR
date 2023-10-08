package com.example.gamegearhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MonitorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        setContentView(R.layout.activity_monitor);
        String MonitorTitle [] =  {"Samsung 32 Odyssey","LG 22MP410-B 21.45","HP M24F 2E2Y4AA 23.8","Asus TUF VG30VQL1A 29.5","Nvision  24  Inch Ips Monitor"};
        String MonitorDesc [] = {"Conquer every enemy, even at soaring speeds. 240Hz RapidCurve eliminates lag for exhilarating gameplay with ultra-smooth action. Jump on enemies right when you see them with a 1ms response time, precise mouse movements, and blur-free frames with no ghosting.","21.45-inch Full HD (1920x1080) Display delivers precise and clear imagery through outstanding colour accuracy. Reader Mode adjusts colour temperature and luminance similar to reading a paper book. Flicker Safe provides a comfortable working environment for a long time.","FHD display with IPS technology, 99% sRGB color gamut and FreeSync. Hardware-integrated low blue light technology that doesn't sacrifice color quality. Monitor designed with 85% recycled materials and sustainably packaged. Slim profile, innovative cable containment and seamless design for side-by-side screens.","The TUF Gaming VG30VQL1A is a 29.5-inch WFHD (2560 x 1080) 1500R curved gaming monitor with an ultrafast 200 Hz refresh rate and 1 ms (MPRT) response time for extremely immersive gameplay.","The Nvision N2455 24-inch Monitor features a 75Hz refresh rate while rocking a 1ms response time. The N2455 24-inch Monitor has dimensions of 541 x 412 x 149mm and weighs in at 2.9kg. It also has a 178° screen with a display resolution of 1920 x 1080pixels for you to enjoy all types of entertainment!"};
        String MonitorPrice [] = {"P 8,000","P 4,500","P 6,900","P 5,000","P 3,200"};
        int MonitorImg [] = {R.drawable.monitor1,R.drawable.monitor2,R.drawable.monitor3,R.drawable.monitor4,R.drawable.monitor5};

        ImageView monitor1 = findViewById(R.id.monitor1);
        ImageView monitor2 = findViewById(R.id.monitor2);
        ImageView monitor3 = findViewById(R.id.monitor3);
        ImageView monitor4 = findViewById(R.id.monitor4);
        ImageView monitor5 = findViewById(R.id.monitor5);

        monitor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonitorActivity.this, Monitor1.class);
                startActivity(intent);
            }
        });
        monitor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonitorActivity.this, Monitor2.class);
                startActivity(intent);
            }
        });
        monitor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonitorActivity.this, Monitor3.class);
                startActivity(intent);
            }
        });
        monitor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonitorActivity.this, Monitor4.class);
                startActivity(intent);
            }
        });
        monitor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonitorActivity.this, Monitor5.class);
                startActivity(intent);
            }
        });


    }
}