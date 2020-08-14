package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ccreditcard2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccreditcard2);
    }

    public void creditSuccessful(View view){
        Toast.makeText(Ccreditcard2.this, "Saving credit card details", Toast.LENGTH_SHORT).show();
        Toast.makeText(Ccreditcard2.this, "Paying", Toast.LENGTH_SHORT).show();
        Intent creditSuccessful = new Intent(this, Ccreditsuccessful.class);
        Button creditSuccessButton = (Button) findViewById(R.id.c_button13);
        startActivity(creditSuccessful);
    }
}