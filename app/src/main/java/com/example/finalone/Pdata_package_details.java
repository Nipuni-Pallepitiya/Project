package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Pdata_package_details extends AppCompatActivity {

    TextView tvId, tvName, tvAny, tvNight, tvValidity, tvPrice, tvPhone;
    Button btn1;
    ImageButton imageButton;
    DatabaseReference dbRef1, dbRef2,dbRef3;
    CusData c = new CusData();
    Bill b1 = new Bill();
    String bno,bamount,bdateto,bdatefrom;
    String ddname,ddprice,dddateto,dddatefrom;
    int amount, dprice;
    int totprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_data_package_details);

        totprice=0;
        amount=0;
        dprice=0;

        btn1 = findViewById(R.id.pbtnActivateDataPack);
        imageButton = findViewById(R.id.pimageButton5);
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

                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(Pdata_package_details.this);
                builder.setMessage("Do you really want to activate this package?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbRef1 = FirebaseDatabase.getInstance().getReference().child("CusData");

                                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("CusData");
                                readRef.addListenerForSingleValueEvent(new ValueEventListener() {


                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.child(id).child(phone).exists()) {

                                            Toast.makeText(getApplicationContext(), "Package Already Activated", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            switch (id) {
                                                case "1":
                                                    String val1 = putDate1();
                                                    c.setDataname(tvName.getText().toString().trim());
                                                    c.setDataprice(tvPrice.getText().toString().trim());
                                                    c.setDatadatefrom(c.getDatadatefrom());
                                                    c.setDatadateto(val1);
                                                    dbRef1.child(id).child(phone).setValue(c);
                                                    Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();
                                                    Toast.makeText(getApplicationContext(), "Bill is Updated", Toast.LENGTH_SHORT).show();
                                                    break;

                                                case "2":
                                                    String val2 = putDate2();
                                                    c.setDataname(tvName.getText().toString().trim());
                                                    c.setDataprice(tvPrice.getText().toString().trim());
                                                    c.setDatadatefrom(c.getDatadatefrom());
                                                    c.setDatadateto(val2);
                                                    dbRef1.child(id).child(phone).setValue(c);
                                                    Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();
                                                    Toast.makeText(getApplicationContext(), "Bill is Updated", Toast.LENGTH_SHORT).show();
                                                    break;

                                                case "3":
                                                    String val3 = putDate3();
                                                    c.setDataname(tvName.getText().toString().trim());
                                                    c.setDataprice(tvPrice.getText().toString().trim());
                                                    c.setDatadatefrom(c.getDatadatefrom());
                                                    c.setDatadateto(val3);
                                                    dbRef1.child(id).child(phone).setValue(c);
                                                    Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();
                                                    Toast.makeText(getApplicationContext(), "Bill is Updated", Toast.LENGTH_SHORT).show();
                                                    break;

                                                case "4":
                                                    String val4 = putDate4();
                                                    c.setDataname(tvName.getText().toString().trim());
                                                    c.setDataprice(tvPrice.getText().toString().trim());
                                                    c.setDatadatefrom(c.getDatadatefrom());
                                                    c.setDatadateto(val4);
                                                    dbRef1.child(id).child(phone).setValue(c);
                                                    Toast.makeText(getApplicationContext(), "Package Activated", Toast.LENGTH_SHORT).show();
                                                    Toast.makeText(getApplicationContext(), "Bill is Updated", Toast.LENGTH_SHORT).show();
                                                    break;
                                            }
                                        }
                                        updateBill();


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }

                                });

                            }

                        }).setNegativeButton("No",null);
                            AlertDialog alert = builder.create();
                            alert.show();
            }

        });


    }

    public void updateBill(){

        dbRef2 = FirebaseDatabase.getInstance().getReference().child("Bill");

        DatabaseReference billRef = FirebaseDatabase.getInstance().getReference().child("Bill").child(tvPhone.getText().toString());
        billRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren())
                {
                    bno=dataSnapshot.child("billno").getValue().toString();
                    bdateto=dataSnapshot.child("billdateto").getValue().toString();
                    bdatefrom=dataSnapshot.child("billdatefrom").getValue().toString();
                    bamount=dataSnapshot.child("billfixprice").getValue().toString();
                    amount = Integer.parseInt(bamount.replaceAll("[^0-9]", ""));
                }
                else{
                    Toast.makeText(getApplicationContext(),"No record",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference updRef=FirebaseDatabase.getInstance().getReference().child("CusData").child(tvId.getText().toString()).child(tvPhone.getText().toString());
        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ddname=dataSnapshot.child("dataname").getValue().toString();
                dddatefrom =dataSnapshot.child("datadatefrom").getValue().toString();
                dddateto=dataSnapshot.child("datadateto").getValue().toString();
                ddprice =dataSnapshot.child("dataprice").getValue().toString();
                dprice=Integer.parseInt(ddprice.replaceAll("[^0-9]", ""));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference inputRef=FirebaseDatabase.getInstance().getReference().child("Bill").child(tvPhone.getText().toString());
        inputRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                b1.setBillno(bno);
                b1.setBilldatefrom(bdatefrom);
                b1.setBilldateto(bdateto);
                totprice =totalAmount(dprice,amount);
                b1.setBillfixprice("Rs."+totprice);

                dbRef2.child(tvPhone.getText().toString()).setValue(b1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public int totalAmount(int dprice,int amount){

        int tot= dprice + amount;
        return tot;
    }

    public String putDate1(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String cdate =dateFormat.format(currentDate);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(currentDate);
        c1.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        Date currentDatePlusOne = c1.getTime();
        String dv= dateFormat.format(currentDatePlusOne);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDate=cal.getTime();
        String dataLast = dateFormat.format(lastDate);

        if(currentDatePlusOne.after(lastDate)){

            Date validdate = lastDate;
            String finalDate = dateFormat.format(validdate);
            return finalDate;
        }
        else{
            Date validdate = currentDatePlusOne;
            String finalDate = dateFormat.format(validdate);
            return finalDate;
        }

    }

    public String putDate2(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String cdate =dateFormat.format(currentDate);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(currentDate);
        c1.add(Calendar.DATE, 3); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        Date currentDatePlusOne = c1.getTime();
        String dv= dateFormat.format(currentDatePlusOne);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDate=cal.getTime();
        String dataLast = dateFormat.format(lastDate);

        if(currentDatePlusOne.after(lastDate)){

            Date validdate = lastDate;
            String finalDate = dateFormat.format(validdate);
            return finalDate;
        }
        else{
            Date validdate = currentDatePlusOne;
            String finalDate = dateFormat.format(validdate);
            return finalDate;
        }

    }

    public String putDate3(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String cdate =dateFormat.format(currentDate);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(currentDate);
        c1.add(Calendar.DATE, 7); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        Date currentDatePlusOne = c1.getTime();
        String dv= dateFormat.format(currentDatePlusOne);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDate=cal.getTime();
        String dataLast = dateFormat.format(lastDate);

        if(currentDatePlusOne.after(lastDate)){

            Date validdate = lastDate;
            String finalDate = dateFormat.format(validdate);
            return finalDate;
        }
        else{
            Date validdate = currentDatePlusOne;
            String finalDate = dateFormat.format(validdate);
            return finalDate;
        }

    }

    public String putDate4(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String cdate =dateFormat.format(currentDate);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(currentDate);
        c1.add(Calendar.DATE, 21); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        Date currentDatePlusOne = c1.getTime();
        String dv= dateFormat.format(currentDatePlusOne);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDate=cal.getTime();
        String dataLast = dateFormat.format(lastDate);

        if(currentDatePlusOne.after(lastDate)){

            Date validdate = lastDate;
            String finalDate = dateFormat.format(validdate);
            return finalDate;
        }
        else{
            Date validdate = currentDatePlusOne;
            String finalDate = dateFormat.format(validdate);
            return finalDate;
        }

    }

}