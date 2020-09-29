package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalone.Model.FixData;
import com.example.finalone.Model.ShareData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class NShareDisplay extends AppCompatActivity {


    //Button btn1;
    //public static final String EXTRAMESSAGEPHN="phnNo";
    TextView txtID,txtPhnTo,txtPhnFrom,txtAmt,txtDate,txtphn,txtamtleft;
    String amthve;
    Double amth;
    String amtgave;
    Double amtgv;
    String amtleft;
    Double amtl;
    String ffixname,ffixdatef,ffixdateto,ffixprice;
    DatabaseReference dbRef,dbRef3;
    ShareData std;
    FixData f1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_share_display);
        f1=new FixData();

        Intent i=getIntent();
        final String message= i.getStringExtra(NShare_Main.EXTRAMESSAGE);
        String message2=i.getStringExtra(NShare_Main.EXTRAMESSAGE2);

        txtID=findViewById(R.id.nshareData);
        txtPhnTo=findViewById(R.id.nphnTo);
        txtPhnFrom=findViewById(R.id.nphnFrom);
        txtAmt=findViewById(R.id.NAmtofData2);
        txtDate=findViewById(R.id.ndate6);
        txtphn=findViewById(R.id.ntextView3);
        txtamtleft=findViewById(R.id.nAmtR);
        //e=findViewById(R.id.edmain);


        dbRef= FirebaseDatabase.getInstance().getReference().child("ShareData");
        dbRef3=FirebaseDatabase.getInstance().getReference().child("FixData");
        DatabaseReference readRef2= FirebaseDatabase.getInstance().getReference().child("FixData").child(message);
        readRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChildren())
                {
                    ffixname=snapshot.child("fixname").getValue().toString();
                    ffixdatef=snapshot.child("fixdatefrom").getValue().toString();
                    ffixdateto=snapshot.child("fixdateto").getValue().toString();
                    ffixprice=snapshot.child("fixprice").getValue().toString();
                    amthve=snapshot.child("fixdata").getValue().toString();
                    amth = Double.parseDouble(amthve);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No source to display",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        DatabaseReference readRef= FirebaseDatabase.getInstance().getReference().child("ShareData").child(message2);
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
                    amtgave=dataSnapshot.child("amt").getValue().toString();
                    amtgv=Double.parseDouble(amtgave);
                    /*amtl=((amth*1024)-amtgv)/1024;
                    txtamtleft.setText(amtl.toString());*/

                    amtl=calculation(amth,amtgv);
                    txtamtleft.setText(amtl.toString());




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

       DatabaseReference upRef= FirebaseDatabase.getInstance().getReference().child("FixData");
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(message))
                {
                    f1.setFixdata(txtamtleft.getText().toString().trim());
                    f1.setFixname(ffixname.trim());
                    f1.setFixdatefrom(ffixdatef.trim());
                    f1.setFixdateto(ffixdateto.trim());
                    f1.setFixprice(ffixprice.trim());
                    dbRef3=FirebaseDatabase.getInstance().getReference().child("FixData").child(message);
                    dbRef3.setValue(f1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








    }


    /*public void NBackClick(View v)
    {
        Intent intent=new Intent(this,NShare_Data.class);
        Button btn1=(Button)findViewById(R.id.Nback);
        startActivity(intent);


    }*/

    public void NOnClick(View v)
    {
        openDialog();

    }

    public void openDialog()
    {

        NDialog ex=new NDialog();
        ex.show(getSupportFragmentManager(),"example dialog");

    }

    public double calculation(double amth,double amtgv)
    {
        double result;
        result=((amth*1024)-amtgv)/1024;

        return result;

    }

}