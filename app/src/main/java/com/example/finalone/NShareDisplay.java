package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NShareDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_share_display);


        Intent intent = getIntent();
        String message = intent.getStringExtra(NShare_Main.EXTRA_MESSAGE);
        String message2 = intent.getStringExtra(NShare_Main.EXTRA_MESSAGE2);
        String message3 = intent.getStringExtra(NShare_Main.EXTRA_MESSAGE3);
        String message4 = intent.getStringExtra(NShare_Main.EXTRA_MESSAGE4);
        String message5 = intent.getStringExtra(NShare_Main.EXTRA_MESSAGE5);


        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.nshareData);
        textView.setText(message);
        TextView textView2 = findViewById(R.id.nphnFrom);
        textView2.setText(message2);
        TextView textView3 = findViewById(R.id.nphnTo);
        textView3.setText(message3);
        TextView textView4 = findViewById(R.id.NAmtofData2);
        textView4.setText(message4);
        TextView textView5 = findViewById(R.id.ndate6);
        textView5.setText(message5);


    }


    public void NBackClick(View v)
    {
        Intent intent=new Intent(this,NShare_Data.class);
        Button btn1=(Button)findViewById(R.id.Nback);
        startActivity(intent);


    }

    public void NOnClick(View v)
    {
        openDialog();

    }

    public void openDialog()
    {

        NDialog ex=new NDialog();
        ex.show(getSupportFragmentManager(),"example dialog");

    }

}