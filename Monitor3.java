package com.example.gamegearhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Monitor3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_monitor3);
        String MonitorTitle [] =  {"Samsung 32 Odyssey","LG 22MP410-B 21.45","HP M24F 2E2Y4AA 23.8","Asus TUF VG30VQL1A 29.5","Nvision  24  Inch Ips Monitor"};
        String MonitorDesc [] = {"Conquer every enemy, even at soaring speeds. 240Hz RapidCurve eliminates lag for exhilarating gameplay with ultra-smooth action. Jump on enemies right when you see them with a 1ms response time, precise mouse movements, and blur-free frames with no ghosting.","21.45-inch Full HD (1920x1080) Display delivers precise and clear imagery through outstanding colour accuracy. Reader Mode adjusts colour temperature and luminance similar to reading a paper book. Flicker Safe provides a comfortable working environment for a long time.","FHD display with IPS technology, 99% sRGB color gamut and FreeSync. Hardware-integrated low blue light technology that doesn't sacrifice color quality. Monitor designed with 85% recycled materials and sustainably packaged. Slim profile, innovative cable containment and seamless design for side-by-side screens.","The TUF Gaming VG30VQL1A is a 29.5-inch WFHD (2560 x 1080) 1500R curved gaming monitor with an ultrafast 200 Hz refresh rate and 1 ms (MPRT) response time for extremely immersive gameplay.","The Nvision N2455 24-inch Monitor features a 75Hz refresh rate while rocking a 1ms response time. The N2455 24-inch Monitor has dimensions of 541 x 412 x 149mm and weighs in at 2.9kg. It also has a 178° screen with a display resolution of 1920 x 1080pixels for you to enjoy all types of entertainment!"};
        String MonitorPrice [] = {"P 8,000","P 4,500","P 6,900","P 5,000","P 3,200"};
        int MonitorImg [] = {R.drawable.monitor1,R.drawable.monitor2,R.drawable.monitor3,R.drawable.monitor4,R.drawable.monitor5};

        ImageView imageView = findViewById(R.id.imgs);
        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);
        TextView price = findViewById(R.id.price);
        Button addtocart = findViewById(R.id.addtocart);

        imageView.setImageResource(MonitorImg[2]);
        title.setText(MonitorTitle[2]);
        description.setText(MonitorDesc[2]);
        price.setText(MonitorPrice[2]);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView title = findViewById(R.id.title);
                TextView price = findViewById(R.id.price);

                String mousetitle = title.getText().toString();
                String mouseprice = price.getText().toString();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Cart");
                databaseReference.child("18").child("title").setValue(mousetitle);
                databaseReference.child("18").child("price").setValue(mouseprice);

            }
        });
    }
}