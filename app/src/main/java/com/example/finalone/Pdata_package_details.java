package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Pdata_package_details extends AppCompatActivity {

    Button btn1;
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_data_package_details);

        btn1 = findViewById(R.id.pbutton13);
        img = findViewById(R.id.pimageButton5);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Pdata_package_details.this,MProfile.class);
                startActivity(i);
            }
        });
    }
    public void openDialog(){
        PDialog PDialog = new PDialog();
        PDialog.show(getSupportFragmentManager(),"dialog");
    }

}