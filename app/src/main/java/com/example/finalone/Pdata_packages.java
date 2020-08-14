package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Pdata_packages extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    ImageButton img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_data_packages);

        btn1 = findViewById(R.id.pbutton9);
        btn2 = findViewById(R.id.pbutton10);
        btn3 = findViewById(R.id.pbutton11);
        btn4 = findViewById(R.id.pbutton12);
        img = findViewById(R.id.pimageButton4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pdata_packages.this, Pdata_package_details.class);
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pdata_packages.this, Pdata_package_details.class);
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pdata_packages.this, Pdata_package_details.class);
                startActivity(i);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pdata_packages.this, Pdata_package_details.class);
                startActivity(i);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pdata_packages.this,MProfile.class);
                startActivity(i);
            }
        });
    }
}