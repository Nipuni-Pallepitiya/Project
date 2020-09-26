package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Pview_my_packages extends AppCompatActivity {

    TextView tvName,tvFrom,tvTo,tvPhone;
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_view_my_packages);

        img = findViewById(R.id.pimageButton6);

        tvName=findViewById(R.id.pName);
        tvFrom=findViewById(R.id.pFrom);
        tvTo=findViewById(R.id.pTo);
        tvPhone=findViewById(R.id.ptvPhoneViewMyPacks);

        Intent i1 = getIntent();
        String name= i1.getStringExtra("name");
        String from= i1.getStringExtra("from");
        String to= i1.getStringExtra("to");
        String phone = i1.getStringExtra("phone");

        tvName.setText(name);
        tvFrom.setText(from);
        tvTo.setText(to);
        tvPhone.setText(phone);



        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Pview_my_packages.this,MProfile.class);
                startActivity(i);
            }
        });
    }


}