package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_select);


    }
    public void Add_Data(View view){
        Intent intent = new Intent(this,PAdd_Data.class);
        Button btn = (Button) findViewById(R.id.button9);
        startActivity(intent);

    }
    public void share_Data(View view){
        Intent intent = new Intent(this,NShare_Data.class);
        Button btn = (Button) findViewById(R.id.button10);
        startActivity(intent);
    }
    //redirect to the bill using intent
    public void bill(View view){
        Intent intent = new Intent(this,MBill.class);
        Button btn = (Button) findViewById(R.id.button11);
        startActivity(intent);
    }
    //redirect to the profile using intent
    public void displayProfile(View view){
        Intent intent = new Intent(this,MProfile.class);
        ImageButton FullName = (ImageButton) findViewById(R.id.imageButton);
        startActivity(intent);

    }

}

