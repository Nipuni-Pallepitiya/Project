package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import static com.example.finalone.MLogin.phone;


public class MSelect extends AppCompatActivity {
    public static final String EXTRAMESSAGE="message";
    private ImageButton imageButton;
    TextView textview;



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_m_select);

            final Intent intent = getIntent();

            final String phone = intent.getStringExtra("EXTRA_MESSAGE");
            textview = findViewById(R.id.textView6);
            textview.setText(phone);







            imageButton = findViewById(R.id.imageButton);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(phone);
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChildren()){
                                String cusName =  (snapshot.child("name").getValue().toString());
                                String cusPhone = (snapshot.child("phoneNo").getValue().toString());
                                String cusEmail = (snapshot.child("email").getValue().toString());

                                Intent intent2 = new Intent(getBaseContext(),MProfile.class);
                                intent2.putExtra("name",cusName);
                                intent2.putExtra("phoneNo",cusPhone);
                                intent2.putExtra("email",cusEmail);
                                startActivity(intent2);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });







    }
    public void Add_Data(View view){
        Intent intent = new Intent(this,PAdd_Data.class);
        Button btn = (Button) findViewById(R.id.button9);
        startActivity(intent);

    }
    public void share_Data(View view){
        Intent intent = new Intent(this,NShare_Data.class);
        Button btn = (Button) findViewById(R.id.button10);
        String phone = textview.getText().toString();
        intent.putExtra("EXTRA_MESSAGE",phone);
        startActivity(intent);

    }
    //redirect to the bill using intent
    public void bill(View view){
        Intent intent = new Intent(this,MBill.class);
        Button btn = (Button) findViewById(R.id.button11);
        startActivity(intent);
    }
    //redirect to the profile using intent



}

