package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MProfile2 extends AppCompatActivity {
    TextView phoneNo,nameTextView,phoneTextView,emailTextView;
    Button btnDelete,btnLogout;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_profile2);

        phoneNo = findViewById(R.id.textView126);
        nameTextView = findViewById(R.id.textView118);
        phoneTextView = findViewById(R.id.textView122);
        emailTextView = findViewById(R.id.textView120);
        btnDelete = findViewById(R.id.button21);
        btnLogout = findViewById(R.id.button22);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");

        Intent intent2 = getIntent();
        String name = intent2.getStringExtra("FULL");
        final String phoneNumber = intent2.getStringExtra("PHONE");
        String emailAddress = intent2.getStringExtra("EMAIL");

        phoneNo.setText(phoneNumber);
        nameTextView.setText(name);
        phoneTextView.setText(phoneNumber);
        emailTextView.setText(emailAddress);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(phoneNumber)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(phoneNumber);
                            dbRef.removeValue();

                            Toast.makeText(MProfile2.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MProfile2.this,MRegister.class));

                        }else{
                            Toast.makeText(MProfile2.this, "Can not delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}