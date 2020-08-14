package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalone.R;

public class CCredit_card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_credit_card);
    }

    public void creditCard(View view){
        Intent credit = new Intent(this, Ccreditcard2.class);
        Button creditButton = (Button) findViewById(R.id.c_button18);
        startActivity(credit);
    }

    public void cash(View view){
        Intent cash = new Intent(this, CcashDetails.class);
        Button caButton = (Button) findViewById(R.id.c_button20);
        startActivity(cash);
    }

}