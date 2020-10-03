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
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_add__data);

        phoneno= findViewById(R.id.ptvPhoneAddData);
        btn1 = findViewById(R.id.pbtnAddPackage);
        btn2 = findViewById(R.id.pbtnAddData);
        btn3 = findViewById(R.id.pbtnViewMyPacks);
        imageButton = findViewById(R.id.pimageButton1);

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

                checkfix();
                /* i2 = new Intent(getBaseContext(), Pdata_packages.class);
                i2.putExtra("phone", phone);
                startActivity(i2);*/
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("FixData").child(phone);
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String packname = (dataSnapshot.child("fixname").getValue().toString());
                            String packactive = (dataSnapshot.child("fixdatefrom").getValue().toString());
                            String packvalid = (dataSnapshot.child("fixdateto").getValue().toString());

                            Intent i2 = new Intent(getBaseContext(), Pview_my_packages.class);
                            i2.putExtra("name", packname);
                            i2.putExtra("from", packactive);
                            i2.putExtra("to", packvalid);
                            i2.putExtra("phone", phone);

                            startActivity(i2);

                        } else
                            Toast.makeText(getApplicationContext(), "No Activated Fixed Package", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }


        });

    }

    public void checkfix() {

        DatabaseReference readRef1 = FirebaseDatabase.getInstance().getReference().child("FixData").child(phoneno.getText().toString());
        readRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    String contact=phoneno.getText().toString();
                    Intent i2 = new Intent(getBaseContext(), Pdata_packages.class);
                    i2.putExtra("phone", contact);
                    startActivity(i2);
                }
                else{
                    Toast.makeText(getApplicationContext(),"No activated fixed package",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}