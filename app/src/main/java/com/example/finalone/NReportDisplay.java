package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NReportDisplay extends AppCompatActivity {

    TextView tv;
    TextView phnno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_report_display);
        Intent i=getIntent();
        String phnmsg=i.getStringExtra(NReport.EXTRAMESSAGE1);
        String message= i.getStringExtra(NReport.EXTRAMESSAGE);
        tv=findViewById(R.id.Ndate1);
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