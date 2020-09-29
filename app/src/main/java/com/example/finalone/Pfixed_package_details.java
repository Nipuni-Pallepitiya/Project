package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalone.Model.Bill;
import com.example.finalone.Model.FixData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pfixed_package_details extends AppCompatActivity {

    TextView tvId,tvName,tvAny,tvNight,tvVoice,tvSms,tvValidity,tvPrice,tvPhone;
    Button btn;
    ImageButton img;
    DatabaseReference dbRef1,dbRef2;
    FixData f1 = new FixData();
    Bill b1 = new Bill();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_fixed_package_details);

        btn = findViewById(R.id.pbtnFixActivate);
        img = findViewById(R.id.pimageButton3);
        tvId=findViewById(R.id.ptvFixId);
        tvName=findViewById(R.id.ptvFixName);
        tvAny=findViewById(R.id.ptvFixAny);
        tvNight=findViewById(R.id.ptvFixNight);
        tvVoice=findViewById(R.id.ptvFixVoice);
        tvSms=findViewById(R.id.ptvFixSms);
        tvValidity=findViewById(R.id.ptvFixValidty);
        tvPrice=findViewById(R.id.ptvFixPrice);

        tvPhone=findViewById(R.id.ptvPhoneFixDetails);

        Intent i1 = getIntent();
        final String id= i1.getStringExtra("id");
        String name= i1.getStringExtra("name");
        String any= i1.getStringExtra("any");
        String night= i1.getStringExtra("night");
        String voice= i1.getStringExtra("voice");
        String sms= i1.getStringExtra("sms");
        String validity= i1.getStringExtra("val");
        String price= i1.getStringExtra("price");
        final Integer billno =1;

        final String phone = i1.getStringExtra("phone");

        tvId.setText(id);
        tvName.setText(name);
        tvAny.setText(any+" GB");
        tvNight.setText(night + " GB");
        tvVoice.setText(voice);
        tvSms.setText(sms);
        tvValidity.setText(validity);
        tvPrice.setText(price);

        tvPhone.setText(phone);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Pfixed_package_details.this);
                builder.setMessage("Do you really want to activate this package?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbRef1 = FirebaseDatabase.getInstance().getReference().child("FixData");
                                dbRef2 = FirebaseDatabase.getInstance().getReference().child("Bill");

                                DatabaseReference readRef1 = FirebaseDatabase.getInstance().getReference().child("FixData");
                                readRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.child(phone).exists()) {
                                            Toast.makeText(getApplicationContext(), "Package Already Activated", Toast.LENGTH_SHORT).show();
                                        } else if (id == "1") {

                                            f1.setFixname(tvName.getText().toString().trim());
                                            f1.setFixprice(tvPrice.getText().toString().trim());
                                            f1.setFixdatefrom(f1.getFixdatefrom());
                                            f1.setFixdateto(f1.getFixdateto());
                                            f1.setFixdata(tvAny.getText().toString().trim());
                                            dbRef1.child(phone).setValue(f1);

                                            Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), "Bill Added", Toast.LENGTH_SHORT).show();
                                        } else if (id == "2") {

                                            f1.setFixname(tvName.getText().toString().trim());
                                            f1.setFixprice(tvPrice.getText().toString().trim());
                                            f1.setFixdatefrom(f1.getFixdatefrom());
                                            f1.setFixdateto(f1.getFixdateto());
                                            f1.setFixdata(tvAny.getText().toString().trim());
                                            dbRef1.child(phone).setValue(f1);

                                            Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), "Bill Added", Toast.LENGTH_SHORT).show();
                                        } else if (id == "3") {

                                            f1.setFixname(tvName.getText().toString().trim());
                                            f1.setFixprice(tvPrice.getText().toString().trim());
                                            f1.setFixdatefrom(f1.getFixdatefrom());
                                            f1.setFixdateto(f1.getFixdateto());
                                            f1.setFixdata(tvAny.getText().toString().trim());
                                            dbRef1.child(phone).setValue(f1);

                                            Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), "Bill Added", Toast.LENGTH_SHORT).show();

                                        } else {
                                            f1.setFixname(tvName.getText().toString().trim());
                                            f1.setFixprice(tvPrice.getText().toString().trim());
                                            f1.setFixdatefrom(f1.getFixdatefrom());
                                            f1.setFixdateto(f1.getFixdateto());
                                            f1.setFixdata(tvAny.getText().toString().trim());
                                            dbRef1.child(phone).setValue(f1);
                                            Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), "Bill Added", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }


                                });

                                DatabaseReference readRef2 = FirebaseDatabase.getInstance().getReference().child("Bill");
                                readRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.child(phone).exists()) {
                                            Toast.makeText(getApplicationContext(), "Bill already added", Toast.LENGTH_SHORT).show();
                                        } else
                                            switch (id) {
                                                case "1": {
                                                    b1.setBillno(String.valueOf(billno + 1));
                                                    b1.setBilldatefrom(b1.getBilldatefrom());
                                                    b1.setBilldateto(b1.getBilldateto());
                                                    b1.setBillfixprice(tvPrice.getText().toString().trim());
                                                    dbRef2.child(phone).setValue(b1);
                                                }
                                                case "2": {
                                                    b1.setBillno(String.valueOf(billno + 1));
                                                    b1.setBilldatefrom(b1.getBilldatefrom());
                                                    b1.setBilldateto(b1.getBilldateto());
                                                    b1.setBillfixprice(tvPrice.getText().toString().trim());

                                                    dbRef2.child(phone).setValue(b1);
                                                }
                                                case "3": {
                                                    b1.setBillno(String.valueOf(billno + 1));
                                                    b1.setBilldatefrom(b1.getBilldatefrom());
                                                    b1.setBilldateto(b1.getBilldateto());
                                                    b1.setBillfixprice(tvPrice.getText().toString().trim());

                                                    dbRef2.child(phone).setValue(b1);

                                                }
                                                case "4": {
                                                    b1.setBillno(String.valueOf(billno + 1));
                                                    b1.setBilldatefrom(b1.getBilldatefrom());
                                                    b1.setBilldateto(b1.getBilldateto());
                                                    b1.setBillfixprice(tvPrice.getText().toString().trim());

                                                    dbRef2.child(phone).setValue(b1);

                                                }
                                            }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }

                     }).setNegativeButton("No", null);

                              AlertDialog alert = builder.create();
                              alert.show();

            }


         });
    }
}


