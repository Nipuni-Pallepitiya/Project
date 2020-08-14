package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NReportDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_report_display);
    }

    public void NBackClick(View v)
    {
        Intent intent=new Intent(this,NShare_Data.class);
        Button btn1=(Button)findViewById(R.id.Nback);
        startActivity(intent);

    }
}