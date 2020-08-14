package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CcashDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccash_details);
    }

    public void cashSuccessful(View view){
        Toast.makeText(CcashDetails.this, "Saving bill details", Toast.LENGTH_SHORT).show();
        Intent cashSuccessful = new Intent(this, Ccashsuccessful.class);
        Button cashButton = (Button) findViewById(R.id.c_button20);
        startActivity(cashSuccessful);
    }
}