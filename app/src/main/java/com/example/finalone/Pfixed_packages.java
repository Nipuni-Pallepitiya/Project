package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Pfixed_packages extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_fixed_packages);

        btn1 = findViewById(R.id.pbutton4);
        btn2 = findViewById(R.id.pbutton5);
        btn3 = findViewById(R.id.pbutton6);
        btn4 = findViewById(R.id.pbutton7);
        img = findViewById(R.id.pimageButton2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pfixed_packages.this,Pfixed_package_details.class);
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pfixed_packages.this,Pfixed_package_details.class);
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pfixed_packages.this,Pfixed_package_details.class);
                startActivity(i);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pfixed_packages.this,Pfixed_package_details.class);
                startActivity(i);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pfixed_packages.this,MProfile.class);
                startActivity(i);
            }
        });
    }
}