package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NShare_Data extends AppCompatActivity {

    public static final String SHAREDATA="com.example.ourproject";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_share__data);
    }
    public void OnClickShareData(View v)
    {
        Intent intent=new Intent(this,NShare_Main.class);
        Button btn1=(Button)findViewById(R.id.Nbutton14);
        startActivity(intent);


    }
    public void OnClickReport(View v)
    {
        Intent intent=new Intent(this,NReport.class);
        Button btn1=(Button)findViewById(R.id.Nbutton15);
        startActivity(intent);

    }
}