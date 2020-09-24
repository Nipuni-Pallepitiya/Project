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
import android.widget.Toast;

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
    Button btnBill;



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_m_select);

            textview = findViewById(R.id.textView6);

            final Intent intent = getIntent();

            final String phone = intent.getStringExtra("EXTRA_MESSAGE");
            textview = findViewById(R.id.textView6);
            textview.setText(phone);


        /*Intent intent1 = new Intent(MSelect.this,MBill.class);
        final String phone1 = intent1.getStringExtra("phone");
        textview.setText(phone1);*/





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

            btnBill = findViewById(R.id.button11);
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Bill").child(phone);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){

                            String billDateFrom = (snapshot.child("billdatefrom").getValue().toString());
                            String  billDateTo = (snapshot.child("billdateto").getValue().toString());
                            String billPrice = (snapshot.child("billfixprice").getValue().toString());
                            String phone1 = textview.getText().toString();

                            Intent intent3 = new Intent(getBaseContext(),MBill.class);

                            intent3.putExtra("dateFrom",billDateFrom);
                            intent3.putExtra("dateTo",billDateTo);
                            intent3.putExtra("price",billPrice);
                            intent3.putExtra("PHONE",phone1);
                            startActivity(intent3);




                        }
                        else{
                            Toast.makeText(MSelect.this, "You have to activate package", Toast.LENGTH_SHORT).show();
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
        String phone = textview.getText().toString();
        intent.putExtra("phone",phone);
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

        Intent intent1 = new Intent(this,MBill.class);
        btnBill = findViewById(R.id.button11);
        String phone1 = textview.getText().toString();
        intent1.putExtra("EXTRA_MESSAGE",phone1);
        startActivity(intent1);






    }
    //redirect to the profile using intent



}

