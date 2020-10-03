package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pfixed_packages extends AppCompatActivity {

    TextView phoneno;
    Button btn1,btn2,btn3,btn4;
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_fixed_packages);

        phoneno=findViewById(R.id.ptvPhoneFixPack);
        btn1 = findViewById(R.id.pbtnViewFixPack1);
        btn2 = findViewById(R.id.pbtnViewFixPack2);
        btn3 = findViewById(R.id.pbtnViewFixPack3);
        btn4 = findViewById(R.id.pbtnViewFixPack4);
        img = findViewById(R.id.pimageButton2);

        Intent i1=getIntent();
        final String phone = i1.getStringExtra("phone");
        phoneno.setText(phone);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("FixedPackages").child("1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            String fixid = (dataSnapshot.child("fixid").getValue().toString());
                            String fixname = (dataSnapshot.child("fixname").getValue().toString());
                            String fixany = (dataSnapshot.child("fixanytime").getValue().toString());
                            String fixnight = (dataSnapshot.child("fixnighttime").getValue().toString());
                            String fixvoice = (dataSnapshot.child("fixvoice").getValue().toString());
                            String fixsms = (dataSnapshot.child("fixsms").getValue().toString());
                            String fixval = (dataSnapshot.child("fixvalidity").getValue().toString());
                            String fixprice =(dataSnapshot.child("fixprice").getValue().toString());



                            Intent i2 = new Intent(getBaseContext(),Pfixed_package_details.class);
                            i2.putExtra("id",fixid);
                            i2.putExtra("name",fixname);
                            i2.putExtra("any",fixany);
                            i2.putExtra("night",fixnight);
                            i2.putExtra("voice",fixvoice);
                            i2.putExtra("sms",fixsms);
                            i2.putExtra("val",fixval);
                            i2.putExtra("price",fixprice);
                            i2.putExtra("phone",phone);

                            startActivity(i2);

                        }else
                            Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("FixedPackages").child("2");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            String fixid = (dataSnapshot.child("fixid").getValue().toString());
                            String fixname = (dataSnapshot.child("fixname").getValue().toString());
                            String fixany = (dataSnapshot.child("fixanytime").getValue().toString());
                            String fixnight = (dataSnapshot.child("fixnighttime").getValue().toString());
                            String fixvoice = (dataSnapshot.child("fixvoice").getValue().toString());
                            String fixsms = (dataSnapshot.child("fixsms").getValue().toString());
                            String fixval = (dataSnapshot.child("fixvalidity").getValue().toString());
                            String fixprice =(dataSnapshot.child("fixprice").getValue().toString());



                            Intent i2 = new Intent(getBaseContext(),Pfixed_package_details.class);
                            i2.putExtra("id",fixid);
                            i2.putExtra("name",fixname);
                            i2.putExtra("any",fixany);
                            i2.putExtra("night",fixnight);
                            i2.putExtra("voice",fixvoice);
                            i2.putExtra("sms",fixsms);
                            i2.putExtra("val",fixval);
                            i2.putExtra("price",fixprice);
                            i2.putExtra("phone",phone);

                            startActivity(i2);

                        }else
                            Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("FixedPackages").child("3");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            String fixid = (dataSnapshot.child("fixid").getValue().toString());
                            String fixname = (dataSnapshot.child("fixname").getValue().toString());
                            String fixany = (dataSnapshot.child("fixanytime").getValue().toString());
                            String fixnight = (dataSnapshot.child("fixnighttime").getValue().toString());
                            String fixvoice = (dataSnapshot.child("fixvoice").getValue().toString());
                            String fixsms = (dataSnapshot.child("fixsms").getValue().toString());
                            String fixval = (dataSnapshot.child("fixvalidity").getValue().toString());
                            String fixprice =(dataSnapshot.child("fixprice").getValue().toString());

                            Intent i2 = new Intent(getBaseContext(),Pfixed_package_details.class);
                            i2.putExtra("id",fixid);
                            i2.putExtra("name",fixname);
                            i2.putExtra("any",fixany);
                            i2.putExtra("night",fixnight);
                            i2.putExtra("voice",fixvoice);
                            i2.putExtra("sms",fixsms);
                            i2.putExtra("val",fixval);
                            i2.putExtra("price",fixprice);
                            i2.putExtra("phone",phone);

                            startActivity(i2);

                        }else
                            Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("FixedPackages").child("4");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            String fixid = (dataSnapshot.child("fixid").getValue().toString());
                            String fixname = (dataSnapshot.child("fixname").getValue().toString());
                            String fixany = (dataSnapshot.child("fixanytime").getValue().toString());
                            String fixnight = (dataSnapshot.child("fixnighttime").getValue().toString());
                            String fixvoice = (dataSnapshot.child("fixvoice").getValue().toString());
                            String fixsms = (dataSnapshot.child("fixsms").getValue().toString());
                            String fixval = (dataSnapshot.child("fixvalidity").getValue().toString());
                            String fixprice =(dataSnapshot.child("fixprice").getValue().toString());



                            Intent i2 = new Intent(getBaseContext(),Pfixed_package_details.class);
                            i2.putExtra("id",fixid);
                            i2.putExtra("name",fixname);
                            i2.putExtra("any",fixany);
                            i2.putExtra("night",fixnight);
                            i2.putExtra("voice",fixvoice);
                            i2.putExtra("sms",fixsms);
                            i2.putExtra("val",fixval);
                            i2.putExtra("price",fixprice);
                            i2.putExtra("phone",phone);
                            startActivity(i2);


                        }else
                            Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pfixed_packages.this,MProfile.class);
                startActivity(i);
            }
        });
    }
}