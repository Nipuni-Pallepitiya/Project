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

import com.example.finalone.Model.Bill;
import com.example.finalone.Model.CusData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pdata_package_details extends AppCompatActivity {

    TextView tvId, tvName, tvAny, tvNight, tvValidity, tvPrice, tvPhone;
    Button btn1;
    ImageButton img;
    DatabaseReference dbRef1, dbRef2;
    CusData c = new CusData();
    Bill b1 = new Bill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_data_package_details);

        btn1 = findViewById(R.id.pbtnActivateDataPack);
        img = findViewById(R.id.pimageButton5);
        tvId = findViewById(R.id.ptvDataId);
        tvName = findViewById(R.id.ptvDataName);
        tvAny = findViewById(R.id.ptvDataAny);
        tvNight = findViewById(R.id.ptvDataNight);
        tvValidity = findViewById(R.id.ptvDataValidity);
        tvPrice = findViewById(R.id.ptvDataprice);
        tvPhone = findViewById(R.id.ptvPhoneDataDetails);

        Intent i1 = getIntent();
        final String id = i1.getStringExtra("id");
        String name = i1.getStringExtra("name");
        String any = i1.getStringExtra("any");
        String night = i1.getStringExtra("night");
        String validity = i1.getStringExtra("val");
        String price = i1.getStringExtra("price");

        final String phone = i1.getStringExtra("phone");

        tvId.setText(id);
        tvName.setText(name);
        tvAny.setText(any);
        tvNight.setText(night);
        tvValidity.setText(validity);
        tvPrice.setText(price);

        tvPhone.setText(phone);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef1 = FirebaseDatabase.getInstance().getReference().child("CusData");

                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("CusData");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(id).child(phone).exists()) {
                            Toast.makeText(getApplicationContext(), "Package Already Activated", Toast.LENGTH_SHORT).show();

                        } else if (id == "1") {

                            c.setDataname(tvName.getText().toString().trim());
                            c.setDataprice(tvPrice.getText().toString().trim());
                            c.setDatadatefrom(c.getDatadatefrom());
                            c.setDatavalidity(tvValidity.getText().toString().trim());

                            dbRef1.child(id).child(phone).setValue(c);
                            Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();

                        } else if (id == "2") {

                            c.setDataname(tvName.getText().toString().trim());
                            c.setDataprice(tvPrice.getText().toString().trim());
                            c.setDatadatefrom(c.getDatadatefrom());
                            c.setDatavalidity(tvValidity.getText().toString().trim());

                            dbRef1.child(id).child(phone).setValue(c);
                            Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();


                        } else if (id == "3") {

                            c.setDataname(tvName.getText().toString().trim());
                            c.setDataprice(tvPrice.getText().toString().trim());
                            c.setDatadatefrom(c.getDatadatefrom());
                            c.setDatavalidity(tvValidity.getText().toString().trim());

                            dbRef1.child(id).child(phone).setValue(c);
                            Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();




                        } else {

                            c.setDataname(tvName.getText().toString().trim());
                            c.setDataprice(tvPrice.getText().toString().trim());
                            c.setDatadatefrom(c.getDatadatefrom());
                            c.setDatavalidity(tvValidity.getText().toString().trim());

                            dbRef1.child(id).child(phone).setValue(c);
                            Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();



                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }

        });

    }
}