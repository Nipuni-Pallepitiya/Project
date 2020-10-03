package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.finalone.Model.CreditCard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CCredit_card extends AppCompatActivity {
    TextView tvPhone,tvDateFrom,tvDateTo,tvPrice;
    Button btnCash, credit;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_credit_card);

        imageButton = findViewById(R.id.imageButton2);
        tvDateFrom= findViewById(R.id.textView19);
        tvDateTo = findViewById(R.id.textView35);
        tvPrice = findViewById(R.id.textView109);
        tvPhone = findViewById(R.id.textView21);
        btnCash = findViewById(R.id.c_button20);
        credit = findViewById(R.id.btncredit);



        Intent intent3 = getIntent();

        String dateFrom = intent3.getStringExtra("billdateFrom");
        String dateTO = intent3.getStringExtra("billdateTo");
        String price = intent3.getStringExtra("billfixprice");
        final String phone = intent3.getStringExtra("phone");

        tvDateFrom.setText(dateFrom);
        tvDateTo.setText(dateTO);
        tvPrice.setText(price);
        tvPhone.setText(phone);

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

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Bill").child(phone);
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()) {

                            String billNo = (snapshot.child("billno").getValue().toString());
                            String billFrom = (snapshot.child("billdatefrom").getValue().toString());
                            String billTo = (snapshot.child("billdateto").getValue().toString());
                            String Price = (snapshot.child("billfixprice").getValue().toString());
                            String phone = tvPhone.getText().toString();



                            Intent intent3 = new Intent(getBaseContext(), CcashDetails.class);

                            intent3.putExtra("phone", phone);
                            intent3.putExtra("billdateFrom", billFrom);
                            intent3.putExtra("billdateTo", billTo);
                            intent3.putExtra("billfixprice", Price);
                            intent3.putExtra("billNo",billNo);



                            startActivity(intent3);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Bill").child(phone);
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()) {

                            String billNo = (snapshot.child("billno").getValue().toString());
                            String billFrom = tvDateFrom.getText().toString();
                            String billTo = tvDateTo.getText().toString();
                            String Price = (snapshot.child("billfixprice").getValue().toString());
                            String phone = tvPhone.getText().toString();



                            Intent intent3 = new Intent(CCredit_card.this, Ccreditcard2.class);

                            intent3.putExtra("phone", phone);
                            intent3.putExtra("billFrom", billFrom);
                            intent3.putExtra("billTo", billTo);
                            intent3.putExtra("billfixprice", Price);
                            intent3.putExtra("billNo",billNo);



                            startActivity(intent3);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    public void creditCard(View view){
        Intent credit = new Intent(this, Ccreditcard2.class);
        Button creditButton = (Button) findViewById(R.id.btncredit);
        startActivity(credit);
    }

    public void cash(View view){
        Intent cash = new Intent(this, CcashDetails.class);
        Button caButton = (Button) findViewById(R.id.c_button20);
        startActivity(cash);
    }

}