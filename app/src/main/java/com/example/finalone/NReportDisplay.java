package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NReportDisplay extends AppCompatActivity {

    TextView tv;
    TextView phnno,amtphn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_report_display);
        Intent i=getIntent();
        String phnmsg=i.getStringExtra(NReport.EXTRAMESSAGE1);
        String message= i.getStringExtra(NReport.EXTRAMESSAGE);
        String amt=i.getStringExtra(NReport.EXTRAMESSAGE2);
        tv=findViewById(R.id.Ndate1);
        amtphn=findViewById(R.id.Namt1);
        amtphn.setText(amt);
        phnno=findViewById(R.id.ntextView);
        phnno.setText(phnmsg);
        tv.setText(message);
    }

    public void NBackClick(View v)
    {
        Intent intent=new Intent(this,NShare_Data.class);
        Button btn1=(Button)findViewById(R.id.Nback);
        startActivity(intent);

    }
}