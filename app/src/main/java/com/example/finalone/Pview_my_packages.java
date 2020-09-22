package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Pview_my_packages extends AppCompatActivity {

    TextView tvName1,tvName2,tvName3,tvName4,tvName5,tvPhone;
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_view_my_packages);

        img = findViewById(R.id.pimageButton6);

        tvName1=findViewById(R.id.ptvPack1);
        tvName2=findViewById(R.id.ptvPack2);
        tvName3=findViewById(R.id.ptvPack3);
        tvName4=findViewById(R.id.ptvPack4);
        tvName5=findViewById(R.id.ptvPack5);
        tvPhone=findViewById(R.id.ptvPhoneViewMyPacks);

        Intent i1 = getIntent();
        String fixname= i1.getStringExtra("fixname");
        String dname1= i1.getStringExtra("dname1");
        String dname2= i1.getStringExtra("dname2");
        String dname3= i1.getStringExtra("dname3");
        String dname4= i1.getStringExtra("dname4");
        String phone = i1.getStringExtra("phone");

        tvName1.setText(dname1);
        tvName2.setText(dname2);
        tvName3.setText(dname3);
        tvName4.setText(dname4);
        tvName5.setText(fixname);
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