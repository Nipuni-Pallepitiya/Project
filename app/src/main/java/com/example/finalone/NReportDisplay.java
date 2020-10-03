package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NReportDisplay extends AppCompatActivity {

    TextView tv;
    TextView phnno,amtphn;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_report_display);
        Intent i=getIntent();
        final String phnmsg=i.getStringExtra(NReport.EXTRAMESSAGE1);
        String message= i.getStringExtra(NReport.EXTRAMESSAGE);
        String amt=i.getStringExtra(NReport.EXTRAMESSAGE2);
        tv=findViewById(R.id.Ndate1);
        amtphn=findViewById(R.id.Namt1);
        amtphn.setText(amt);
        phnno=findViewById(R.id.ntextView);
        phnno.setText(phnmsg);
        tv.setText(message);



    }

    /*public void NBackClick(View v)
    {
        Intent intent=new Intent(this,NShare_Data.class);
        Button btn1=(Button)findViewById(R.id.Nback);
        startActivity(intent);

    }*/
}