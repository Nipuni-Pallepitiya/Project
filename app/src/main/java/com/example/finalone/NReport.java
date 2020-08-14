package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NReport extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_report);
    }

    public void OnClickSubmitReport(View v)
    {
        Intent intent=new Intent(this,NReportDisplay.class);
        Button btn1=(Button)findViewById(R.id.nbutton2);
        EditText editText5 = (EditText) findViewById(R.id.NeditTextPhone);
        String message5 = editText5.getText().toString();


        if ( !editText5.getText().toString().isEmpty() ) {
            Toast.makeText(NReport.this, "Processing request", Toast.LENGTH_LONG).show();
            startActivity(intent);

        } else
            Toast.makeText(NReport.this, "Please Enter a phone number", Toast.LENGTH_LONG).show();




    }

    public void NBackClick(View v)
    {
        Intent intent=new Intent(this,NShare_Data.class);
        Button btn1=(Button)findViewById(R.id.Nback);
        startActivity(intent);

    }
}