package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ccreditcard3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccreditcard3);
    }

    public void creditCardView(View view){
        Intent creditView = new Intent(this, Ccreditcarddetails.class);
        Button creditViewButton = (Button) findViewById(R.id.c_button21);
        startActivity(creditView);
    }

    public void creditCardViewSuccessful(View view){
        Toast.makeText(Ccreditcard3.this, "Saving credit card details", Toast.LENGTH_SHORT).show();
        Toast.makeText(Ccreditcard3.this, "Paying", Toast.LENGTH_SHORT).show();
        Intent creditViewSuccessful = new Intent(this, Ccreditsuccessful.class);
        Button creditViewSuccessfulButton = (Button) findViewById(R.id.c_button42);
        startActivity(creditViewSuccessful);
    }
}