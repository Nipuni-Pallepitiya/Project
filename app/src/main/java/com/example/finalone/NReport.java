package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NReport extends AppCompatActivity {

    EditText txtPhnTo;
    TextView tv;
    public static final String EXTRAMESSAGE="message";
    public static final String EXTRAMESSAGE1="phn";
    String message,phn;
    Button b;
    Button r;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_report);
        final Intent intent = getIntent();
        final String phone = intent.getStringExtra("EXTRA_MESSAGE");
        tv = findViewById(R.id.ntextView2);
        tv.setText(phone);

        r=findViewById(R.id.nbutton2);
        //b=findViewById(R.id.btnnew);
        txtPhnTo=findViewById(R.id.NeditTextPhone);
        //newdate=findViewById(R.id.txtnew);
        phn=tv.getText().toString();

        //dbRef=FirebaseDatabase.getInstance().getReference();

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = tv.getText().toString();
                intent.putExtra("EXTRA_MESSAGE",phone);
                final DatabaseReference dbRef=FirebaseDatabase.getInstance().getReference().child("ShareData");
                Query data= dbRef.orderByChild("phnTo").equalTo(txtPhnTo.getText().toString());
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot ds: snapshot.getChildren())
                        {
                            message=ds.child("date").getValue().toString();

                        }

                        Intent i=new Intent(NReport.this,NReportDisplay.class);
                        i.putExtra(EXTRAMESSAGE, message);
                        i.putExtra(EXTRAMESSAGE1,phn);
                        //startActivity(intent);
                        startActivity(i);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });

    }


   /* public void OnClickSubmitReport(View v)
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




    }*/

    public void NBackClick(View v)
    {
        Intent intent=new Intent(this,NShare_Data.class);
        Button btn1=(Button)findViewById(R.id.Nback);
        startActivity(intent);

    }
}