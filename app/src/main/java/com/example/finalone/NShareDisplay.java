package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalone.Model.ShareData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NShareDisplay extends AppCompatActivity {


    //Button btn1;
    //public static final String EXTRAMESSAGEPHN="phnNo";
    TextView txtID,txtPhnTo,txtPhnFrom,txtAmt,txtDate,txtphn;
    DatabaseReference dbRef;
    ShareData std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_share_display);


        Intent i=getIntent();
        String message= i.getStringExtra(NShare_Main.EXTRAMESSAGE);

        txtID=findViewById(R.id.nshareData);
        txtPhnTo=findViewById(R.id.nphnTo);
        txtPhnFrom=findViewById(R.id.nphnFrom);
        txtAmt=findViewById(R.id.NAmtofData2);
        txtDate=findViewById(R.id.ndate6);
        txtphn=findViewById(R.id.ntextView3);
        //e=findViewById(R.id.edmain);
        dbRef= FirebaseDatabase.getInstance().getReference().child("ShareData");



        DatabaseReference readRef= FirebaseDatabase.getInstance().getReference().child("ShareData").child(message);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren())
                {
                    txtID.setText(dataSnapshot.child("id").getValue().toString());
                    txtPhnTo.setText(dataSnapshot.child("phnTo").getValue().toString());
                    txtPhnFrom.setText(dataSnapshot.child("phnFrom").getValue().toString());
                    txtphn.setText(dataSnapshot.child("phnFrom").getValue().toString());
                    txtAmt.setText(dataSnapshot.child("amt").getValue().toString());
                    txtDate.setText(dataSnapshot.child("date").getValue().toString());
                   /* txtID.setText(dataSnapshot.child("id").getValue().toString());
                    txtPhnFrom.setText(dataSnapshot.child("phnFrom").getValue().toString());
                    txtAmt.setText(dataSnapshot.child("phnTo").getValue().toString());*/



                }
                else
                    Toast.makeText(getApplicationContext(),"No source to display",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }   );






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