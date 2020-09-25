package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.finalone.Model.Cash;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CcashDetails extends AppCompatActivity {
    //assign variables
    TextView etDate,tvTime;
    DatePickerDialog.OnDateSetListener setListener;
    Button btnDate,btnTime,btnok,btnShow,btnEdit;
    int t1Hour,t1Minute;
    TextView tvPhone,tvPrice,tvBillNo,tvPhoneNo,tvBillFrom,tvBillTo;
    DatabaseReference reff;
    Cash cash;
    EditText tvbranchName,tvbranchNo;
    long cbillNo=0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccash_details);

        etDate = findViewById(R.id.textView52);
        btnDate = findViewById(R.id.button18);
        tvTime = findViewById(R.id.textView57);
        btnTime = findViewById(R.id.button19);
        tvPhone = findViewById(R.id.textView73);
        tvPhoneNo = findViewById(R.id.textView55);
        tvBillFrom = findViewById(R.id.textView25);
        tvBillTo = findViewById(R.id.textView58);
        tvPrice = findViewById(R.id.textView60);
        tvBillNo = findViewById(R.id.textView67);
        btnok = findViewById(R.id.c_button20);
        /*btnShow = findViewById(R.id.show);
        btnEdit = findViewById(R.id.show2);*/

        reff = FirebaseDatabase.getInstance().getReference().child("Cash");
        cash = new Cash();
        tvbranchName = findViewById(R.id.editTextTextPersonName4);
        tvbranchNo = findViewById(R.id.editTextTextPersonName8);

        //retrieve data from Cash table
        final Intent intent3 = getIntent();

        String dateFrom = intent3.getStringExtra("billdateFrom");
        String dateTO = intent3.getStringExtra("billdateTo");
        String price = intent3.getStringExtra("billfixprice");
        final String phone1 = intent3.getStringExtra("phone");
        final String billNo = intent3.getStringExtra("billNo");


        tvBillFrom.setText(dateFrom);
        tvBillTo.setText(dateTO);
        tvPrice.setText(price);
        tvPhoneNo.setText(phone1);
        tvPhone.setText(phone1);
        tvBillNo.setText(billNo);


        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    cbillNo = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //add datepicker
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CcashDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        etDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //add timePicker
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CcashDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                //initialize hour and minute
                                t1Hour = hourOfDay;
                                t1Minute = minute;
                                //store hour and minute in string
                                String time = t1Hour + ":" + t1Minute;
                                //initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    //initialize 12 hours time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    //set selected time on text view
                                    tvTime.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, 12, 0, false);
                //set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //display previous selected time
                timePickerDialog.updateTime(t1Hour, t1Minute);
                //show dialog
                timePickerDialog.show();
            }
        });

        /*btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String branchNo1 = tvbranchNo.getText().toString();
                try {
                    if (tvbranchName.length() == 0 || tvbranchNo.length() == 0 || tvTime.length() == 0 || etDate.length() == 0)
                        Toast.makeText(CcashDetails.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(tvbranchName.getText().toString()))
                        Toast.makeText(CcashDetails.this, "Please enter Branch name", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(tvbranchNo.getText().toString()))
                        Toast.makeText(CcashDetails.this, "Please enter Branch No", Toast.LENGTH_SHORT).show();
                    else if (!branchNo1.matches("[0-9]{10}"))
                        Toast.makeText(CcashDetails.this, "Please enter valid Branch Number", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(etDate.getText().toString()))
                        Toast.makeText(CcashDetails.this, "Please enter Receipt date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(tvTime.getText().toString()))
                        Toast.makeText(CcashDetails.this, "Please enter Recepit time", Toast.LENGTH_SHORT).show();
                    else {
                        if(!etDate.equals("date") || !tvTime.equals("time")) {
                            String phone = tvPhone.getText().toString().trim();
                            String branchName = tvbranchName.getText().toString().trim();
                            String branchNo = tvbranchNo.getText().toString().trim();
                            String date = etDate.getText().toString().trim();
                            String time = tvTime.getText().toString().trim();


                            cash.setPhoneNo(phone);
                            cash.setBranchName(branchName);
                            cash.setBranchNo(branchNo);
                            cash.setDate(date);
                            cash.setTime(time);
                            cash.setCashno(String.valueOf(cbillNo + 1));
                            reff.child(tvPhone.getText().toString().trim()).setValue(cash);

                            Toast.makeText(CcashDetails.this, "saved successfully", Toast.LENGTH_SHORT).show();
                            clearControls();

                            reff = FirebaseDatabase.getInstance().getReference().child("Cash").child(phone);
                            reff.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.hasChildren()) {
                                        String amount = tvPrice.getText().toString();
                                        String branchName = (snapshot.child("branchName").getValue().toString());
                                        String branchNo = (snapshot.child("branchNo").getValue().toString());
                                        String date = (snapshot.child("date").getValue().toString());
                                        String time = (snapshot.child("time").getValue().toString());
                                        String phone = (snapshot.child("phoneNo").getValue().toString());
                                        String billNo = tvBillNo.getText().toString();

                                        Intent intent4 = new Intent(CcashDetails.this, Ccashsuccessful.class);
                                        intent4.putExtra("amount", amount);
                                        intent4.putExtra("branchName", branchName);
                                        intent4.putExtra("branchNo", branchNo);
                                        intent4.putExtra("date", date);
                                        intent4.putExtra("time", time);
                                        intent4.putExtra("phone", phone);
                                        intent4.putExtra("billno",billNo);

                                        startActivity(intent4);

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        }


            }
                } catch (Exception e) {
                    Toast.makeText(CcashDetails.this, "Can not save", Toast.LENGTH_SHORT).show();
                }
            }
    });*/
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });


      /*  btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Cash").child(phone1);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){
                            tvbranchName.setText(snapshot.child("branchName").getValue().toString());
                            tvbranchNo.setText(snapshot.child("branchNo").getValue().toString());
                            tvTime.setText(snapshot.child("time").getValue().toString());
                            etDate.setText(snapshot.child("date").getValue().toString());
                        }
                        else{
                            Toast.makeText(CcashDetails.this, "No source found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Cash").child(phone1);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(phone1)){
                            try{
                                cash.setBranchName(tvbranchName.getText().toString().trim());
                                cash.setBranchNo(tvbranchNo.getText().toString().trim());
                                cash.setTime(tvTime.getText().toString().trim());
                                cash.setDate(etDate.getText().toString().trim());

                                reff = FirebaseDatabase.getInstance().getReference().child("Cash").child(phone1);
                                reff.setValue(phone1);
                                clearControls();

                                Toast.makeText(CcashDetails.this, "Update Suucessfully", Toast.LENGTH_SHORT).show();
                                reff = FirebaseDatabase.getInstance().getReference().child("Cash").child(phone1);
                                reff.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.hasChildren()) {
                                            String amount = tvPrice.getText().toString();
                                            String branchName = (snapshot.child("branchName").getValue().toString());
                                            String branchNo = (snapshot.child("branchNo").getValue().toString());
                                            String date = (snapshot.child("date").getValue().toString());
                                            String time = (snapshot.child("time").getValue().toString());
                                            String phone = (snapshot.child("phoneNo").getValue().toString());

                                            Intent intent4 = new Intent(CcashDetails.this, Ccashsuccessful.class);
                                            intent4.putExtra("amount", amount);
                                            intent4.putExtra("branchName", branchName);
                                            intent4.putExtra("branchNo", branchNo);
                                            intent4.putExtra("date", date);
                                            intent4.putExtra("time", time);
                                            intent4.putExtra("phone", phone);

                                            startActivity(intent4);

                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });



                            }catch (Exception e){
                                Toast.makeText(CcashDetails.this, "Can not update", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });*/
}

    private void CreateAccount() {
        Cash cash = new Cash();
        cash.setBranchName(tvbranchName.getText().toString());
        cash.setBranchNo(tvbranchNo.getText().toString());
        cash.setDate(etDate.getText().toString());
        cash.setTime(tvTime.getText().toString());
        cash.setPhoneNo(tvPhoneNo.getText().toString());

        String branchName = cash.getBranchName();
        String branchNo = cash.getBranchNo();
        String date = cash.getDate();
        String time = cash.getTime();
        String phone = cash.getPhoneNo();

        if (tvbranchName.length() == 0 || tvbranchNo.length() == 0 || tvTime.length() == 0 || etDate.length() == 0)
            Toast.makeText(CcashDetails.this, "Please enter all details", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(tvbranchName.getText().toString()))
            Toast.makeText(CcashDetails.this, "Please enter Branch name", Toast.LENGTH_SHORT).show();

        else if (TextUtils.isEmpty(tvbranchNo.getText().toString()))
            Toast.makeText(CcashDetails.this, "Please enter Branch No", Toast.LENGTH_SHORT).show();
        else if (!branchNo.matches("[0-9]{10}"))
            Toast.makeText(CcashDetails.this, "Please enter valid Branch Number", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etDate.getText().toString()))
            Toast.makeText(CcashDetails.this, "Please enter Receipt date", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(tvTime.getText().toString()))
            Toast.makeText(CcashDetails.this, "Please enter Recepit time", Toast.LENGTH_SHORT).show();
        else{
            ValidatePhone(phone,branchName,branchNo,date,time,cbillNo);
        }



    }

    private void ValidatePhone(final String phone, final String branchName, final String branchNo, final String date, final String time, final long cbillNo) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.child("Cash").child(phone+date+time).exists()){
                    HashMap<String ,Object>cashMap = new HashMap<>();
                    cashMap.put("branchName",branchName);
                    cashMap.put("branchNo",branchNo);
                    cashMap.put("cashNo",cbillNo+1);
                    cashMap.put("date",date);
                    cashMap.put("time",time);
                    cashMap.put("phoneNo",phone);

                    RootRef.child("Cash").child(phone+date+time).updateChildren(cashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(CcashDetails.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                                clearControls();

                                Cash c = new Cash();
                                c.setBranchName(branchName);
                                c.setBranchNo(branchNo);
                                c.setDate(date);
                                c.setTime(time);
                                c.setCashno(String.valueOf(cbillNo));
                                c.setPhoneNo(phone);

                                reff = FirebaseDatabase.getInstance().getReference().child("Cash").child(phone+date+time);
                                reff.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.hasChildren()) {
                                            String amount = tvPrice.getText().toString();
                                            String branchName = (snapshot.child("branchName").getValue().toString());
                                            String branchNo = (snapshot.child("branchNo").getValue().toString());
                                            String date = (snapshot.child("date").getValue().toString());
                                            String time = (snapshot.child("time").getValue().toString());
                                            String phone = (snapshot.child("phoneNo").getValue().toString());
                                            String billNo = (snapshot.child("cashNo").getValue().toString());

                                            Intent intent4 = new Intent(CcashDetails.this, Ccashsuccessful.class);
                                            intent4.putExtra("amount", amount);
                                            intent4.putExtra("branchName", branchName);
                                            intent4.putExtra("branchNo", branchNo);
                                            intent4.putExtra("date", date);
                                            intent4.putExtra("time", time);
                                            intent4.putExtra("phone", phone);
                                            intent4.putExtra("billno",billNo);

                                            startActivity(intent4);

                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                            }
                        }
                    });
                }else{
                    Toast.makeText(CcashDetails.this, "Already saved same Information", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void clearControls() {
        etDate.setText("");
        tvTime.setText("");
        tvbranchName.setText("");
        tvbranchNo.setText("");
    }


    public void cashSuccessful(View view){
       /* Toast.makeText(CcashDetails.this, "Saving bill details", Toast.LENGTH_SHORT).show();
        Intent cashSuccessful = new Intent(this, Ccashsuccessful.class);
        Button cashButton = (Button) findViewById(R.id.c_button20);
        startActivity(cashSuccessful);*/




    }
}