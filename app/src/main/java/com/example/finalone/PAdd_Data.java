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

public class PAdd_Data extends AppCompatActivity {

    TextView phoneno;
    Button btn1,btn2,btn3;
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_add__data);

        phoneno= findViewById(R.id.ptvPhoneAddData);
        btn1 = findViewById(R.id.pbtnAddPackage);
        btn2 = findViewById(R.id.pbtnAddData);
        btn3 = findViewById(R.id.pbtnViewMyPacks);
        img = findViewById(R.id.pimageButton1);

        Intent i1 = getIntent();
        final String phone = i1.getStringExtra("phone");
        phoneno.setText(phone);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getBaseContext(), Pfixed_packages.class);
                i2.putExtra("phone", phone);
                startActivity(i2);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getBaseContext(), Pdata_packages.class);
                i2.putExtra("phone", phone);
                startActivity(i2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i2 = new Intent(getBaseContext(), Pview_my_packages.class);
                i2.putExtra("phone", phone);
                startActivity(i2);

            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = getIntent();
                String phone = i1.getStringExtra("phone");


                DatabaseReference readRef1 = FirebaseDatabase.getInstance().getReference().child("FixData").child(phone);
                readRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String fixname = (dataSnapshot.child("fixname").getValue().toString());


                            Intent i1 = getIntent();
                            String phone = i1.getStringExtra("phone");

                            Intent i2 = new Intent(getBaseContext(), Pview_my_packages.class);
                            i2.putExtra("fixname", fixname);
                            i2.putExtra("phone", phone);
                            startActivity(i2);

                        } else
                            Toast.makeText(getApplicationContext(), "No Activated packages", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                DatabaseReference readRef2 = FirebaseDatabase.getInstance().getReference().child("CusData").child("1").child(phone);
                readRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String dataname1 = (dataSnapshot.child("dataname").getValue().toString());

                            Intent i1 = getIntent();
                            String phone = i1.getStringExtra("phone");

                            Intent i2 = new Intent(getBaseContext(), Pview_my_packages.class);
                            i2.putExtra("dname1", dataname1);
                            i2.putExtra("phone", phone);
                            startActivity(i2);

                        } else{}

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                DatabaseReference readRef3 = FirebaseDatabase.getInstance().getReference().child("CusData").child("2").child(phone);
                readRef3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String dataname2 = (dataSnapshot.child("dataname").getValue().toString());

                            Intent i1 = getIntent();
                            String phone = i1.getStringExtra("phone");

                            Intent i2 = new Intent(getBaseContext(), Pview_my_packages.class);
                            i2.putExtra("dname2", dataname2);
                            i2.putExtra("phone", phone);
                            startActivity(i2);
                        }else{}



                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                DatabaseReference readRef4 = FirebaseDatabase.getInstance().getReference().child("CusData").child("3").child(phone);
                readRef4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String dataname3 = (dataSnapshot.child("dataname").getValue().toString());


                            Intent i1 = getIntent();
                            String phone = i1.getStringExtra("phone");

                            Intent i2 = new Intent(getBaseContext(), Pview_my_packages.class);
                            i2.putExtra("dname3", dataname3);

                            i2.putExtra("phone", phone);
                            startActivity(i2);

                        } else{}

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                DatabaseReference readRef5 = FirebaseDatabase.getInstance().getReference().child("CusData").child("4").child(phone);
                readRef5.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String dataname4 = (dataSnapshot.child("dataname").getValue().toString());


                            Intent i1 = getIntent();
                            String phone = i1.getStringExtra("phone");

                            Intent i2 = new Intent(getBaseContext(), Pview_my_packages.class);
                            i2.putExtra("dname4", dataname4);

                            i2.putExtra("phone", phone);
                            startActivity(i2);

                        } else{}

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}