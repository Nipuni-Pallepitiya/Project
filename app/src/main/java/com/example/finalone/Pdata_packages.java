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

public class Pdata_packages extends AppCompatActivity {

    TextView phoneno;
    Button btn1,btn2,btn3,btn4;
    ImageButton img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_data_packages);

        phoneno=findViewById(R.id.ptvPhoneDataPack);
        btn1 = findViewById(R.id.pbtnViewDataPack1);
        btn2 = findViewById(R.id.pbtnViewDataPack2);
        btn3 = findViewById(R.id.pbtnViewDataPack3);
        btn4 = findViewById(R.id.pbtnViewDataPack4);
        img = findViewById(R.id.pimageButton4);

        Intent i1 = getIntent();
        final String phone = i1.getStringExtra("phone");
        phoneno.setText(phone);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("DataPackages").child("1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String dataid = (dataSnapshot.child("dataid").getValue().toString());
                            String dataname = (dataSnapshot.child("dataname").getValue().toString());
                            String dataany = (dataSnapshot.child("dataanytime").getValue().toString());
                            String datanight = (dataSnapshot.child("datanighttime").getValue().toString());
                            String dataval = (dataSnapshot.child("datavalidity").getValue().toString());
                            String dataprice = (dataSnapshot.child("dataprice").getValue().toString());


                            Intent i2 = new Intent(getBaseContext(), Pdata_package_details.class);
                            i2.putExtra("id", dataid);
                            i2.putExtra("name", dataname);
                            i2.putExtra("any", dataany);
                            i2.putExtra("night", datanight);
                            i2.putExtra("val", dataval);
                            i2.putExtra("price", dataprice);
                            i2.putExtra("phone", phone);
                            startActivity(i2);

                        } else
                            Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
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
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("DataPackages").child("2");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String dataid = (dataSnapshot.child("dataid").getValue().toString());
                            String dataname = (dataSnapshot.child("dataname").getValue().toString());
                            String dataany = (dataSnapshot.child("dataanytime").getValue().toString());
                            String datanight = (dataSnapshot.child("datanighttime").getValue().toString());
                            String dataval = (dataSnapshot.child("datavalidity").getValue().toString());
                            String dataprice = (dataSnapshot.child("dataprice").getValue().toString());



                            Intent i2 = new Intent(getBaseContext(), Pdata_package_details.class);
                            i2.putExtra("id", dataid);
                            i2.putExtra("name", dataname);
                            i2.putExtra("any", dataany);
                            i2.putExtra("night", datanight);
                            i2.putExtra("val", dataval);
                            i2.putExtra("price", dataprice);
                            i2.putExtra("phone", phone);

                            startActivity(i2);

                        } else
                            Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
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
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("DataPackages").child("3");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String dataid = (dataSnapshot.child("dataid").getValue().toString());
                            String dataname = (dataSnapshot.child("dataname").getValue().toString());
                            String dataany = (dataSnapshot.child("dataanytime").getValue().toString());
                            String datanight = (dataSnapshot.child("datanighttime").getValue().toString());
                            String dataval = (dataSnapshot.child("datavalidity").getValue().toString());
                            String dataprice = (dataSnapshot.child("dataprice").getValue().toString());


                            Intent i2 = new Intent(getBaseContext(), Pdata_package_details.class);
                            i2.putExtra("id", dataid);
                            i2.putExtra("name", dataname);
                            i2.putExtra("any", dataany);
                            i2.putExtra("night", datanight);
                            i2.putExtra("val", dataval);
                            i2.putExtra("price", dataprice);
                            i2.putExtra("phone", phone);

                            startActivity(i2);

                        } else
                            Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
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
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("DataPackages").child("4");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String dataid = (dataSnapshot.child("dataid").getValue().toString());
                            String dataname = (dataSnapshot.child("dataname").getValue().toString());
                            String dataany = (dataSnapshot.child("dataanytime").getValue().toString());
                            String datanight = (dataSnapshot.child("datanighttime").getValue().toString());
                            String dataval = (dataSnapshot.child("datavalidity").getValue().toString());
                            String dataprice = (dataSnapshot.child("dataprice").getValue().toString());


                            Intent i2 = new Intent(getBaseContext(), Pdata_package_details.class);
                            i2.putExtra("id", dataid);
                            i2.putExtra("name", dataname);
                            i2.putExtra("any", dataany);
                            i2.putExtra("night", datanight);
                            i2.putExtra("val", dataval);
                            i2.putExtra("price", dataprice);
                            i2.putExtra("phone", phone);

                            startActivity(i2);

                        } else
                            Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}