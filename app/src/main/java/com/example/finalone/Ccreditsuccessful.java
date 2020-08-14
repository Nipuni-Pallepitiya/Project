package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ccreditsuccessful extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccreditsuccessful);
    }

    public void credit3(View view){
        Intent credit3 = new Intent(this, Ccreditcard3.class);
        Button credit3Button = (Button) findViewById(R.id.c_btn1);
        startActivity(credit3);
    }

    public void credit4(View view){
        Intent credit4 = new Intent(this, Ccreditcarddetails.class);
        Button credit4Button = (Button) findViewById(R.id.c_button50);
        startActivity(credit4);
    }

}