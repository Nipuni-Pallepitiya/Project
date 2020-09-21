package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NShare_Data extends AppCompatActivity {

    public static final String SHAREDATA="com.example.ourproject";
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_share__data);
        final Intent intent = getIntent();
        final String phone = intent.getStringExtra("EXTRA_MESSAGE");
        tv = findViewById(R.id.NtextView15);
        tv.setText(phone);

    }
    public void OnClickShareData(View v)
    {
        Intent intent=new Intent(this,NShare_Main.class);
        Button btn1=(Button)findViewById(R.id.Nbutton14);
        String phone = tv.getText().toString();
        intent.putExtra("EXTRA_MESSAGE",phone);
        startActivity(intent);


    }
    public void OnClickReport(View v)
    {
        Intent intent=new Intent(this,NReport.class);
        Button btn1=(Button)findViewById(R.id.Nbutton15);
        String phone = tv.getText().toString();
        intent.putExtra("EXTRA_MESSAGE",phone);
        startActivity(intent);

    }
}