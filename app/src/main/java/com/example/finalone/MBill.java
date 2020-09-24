package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class MBill extends AppCompatActivity {
    TextView tv,dateToBill,dateFromBill,priceBill,phoneno;
    Button btnCredit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_bill);


        dateFromBill = findViewById(R.id.textView17);
        priceBill = findViewById(R.id.textView14);
        dateToBill = findViewById(R.id.textView30);
        phoneno = findViewById(R.id.textView31);
        btnCredit = findViewById(R.id.button12);


        //set value for Textviews


        Intent intent3 = getIntent();

        String dateFrom = intent3.getStringExtra("dateFrom");
        String dateTO = intent3.getStringExtra("dateTo");
        String price = intent3.getStringExtra("price");
        final String phone1 = intent3.getStringExtra("PHONE");


        dateFromBill.setText(dateFrom);
        dateToBill.setText(dateTO);
        priceBill.setText(price);
        phoneno.setText(phone1);



        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Bill").child(phone1);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){


                            String billFrom = (snapshot.child("billdatefrom").getValue().toString());
                            String  billTo = (snapshot.child("billdateto").getValue().toString());
                            String Price = (snapshot.child("billfixprice").getValue().toString());
                            String phone = phoneno.getText().toString();


                            Intent intent3 = new Intent(getBaseContext(),CCredit_card.class);

                            intent3.putExtra("phone",phone);
                            intent3.putExtra("billdateFrom",billFrom);
                            intent3.putExtra("billdateTo",billTo);
                            intent3.putExtra("billfixprice",Price);


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
    //redirect to the my profile page
    public void displayProfile(View view){
        Intent intent = new Intent(this,MProfile.class);
        ImageButton FullName = (ImageButton) findViewById(R.id.imageButton3);
        startActivity(intent);

    }
    //redirect to the Credit card page
   /* public  void MPayButton(View view){
        Toast.makeText(MBill.this, "Processing", Toast.LENGTH_SHORT).show();
        String phone = phoneno.getText().toString();

        Intent intent = new Intent(this,CCredit_card.class);
        intent.putExtra("EXTRA_MESSAGE",phone);
        Button pay = (Button) findViewById(R.id.button12);
        startActivity(intent);






    }*/

}
