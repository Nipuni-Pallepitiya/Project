package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Pfixed_package_details extends AppCompatActivity {

    Button btn;
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_fixed_package_details);

        btn = findViewById(R.id.pbutton8);
        img = findViewById(R.id.pimageButton3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pfixed_package_details.this,MProfile.class);
                startActivity(i);
            }
        });
    }
    public void openDialog(){
        PDialog PDialog = new PDialog();
        PDialog.show(getSupportFragmentManager(),"dialog");
    }

}