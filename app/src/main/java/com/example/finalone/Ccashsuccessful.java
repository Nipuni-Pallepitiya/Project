package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Ccashsuccessful extends AppCompatActivity {
    TextView tvphone,tvAmount,tvbranchName,tvbranchNo,tvPaidDate,tvTime,tvCurrent,tvBillNo;
    Button btnDate,btnBack,btnSave;
    DatabaseReference reff;
    long pbillNo=0;
    CashPayment cashPayment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccashsuccessful);

        tvphone = findViewById(R.id.textView104);
        tvAmount = findViewById(R.id.textView96);
        tvbranchName = findViewById(R.id.textView108);
        tvbranchNo = findViewById(R.id.textView114);
        tvPaidDate = findViewById(R.id.textView107);
        tvTime = findViewById(R.id.textView123);
        btnDate = findViewById(R.id.button20);
        tvCurrent = findViewById(R.id.textView124);
        tvBillNo = findViewById(R.id.textView129);
        btnSave = findViewById(R.id.button17);
        reff = FirebaseDatabase.getInstance().getReference().child("CashPayment");
        cashPayment = new CashPayment();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren())
                    pbillNo = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Intent intent3 = getIntent();

        //String billno1 = intent3.getStringExtra("billno");
        final String phoneNo = intent3.getStringExtra("phone");
        String name= intent3.getStringExtra("branchName");
        String branchNo= intent3.getStringExtra("branchNo");
        String date = intent3.getStringExtra("date");
        String time = intent3.getStringExtra("time");
        String amount = intent3.getStringExtra("amount");
        String billNo = intent3.getStringExtra("billno");



        //tvBillFrom.setText(billno1);
        tvphone.setText(phoneNo);
       tvbranchName.setText(name);
        tvbranchNo.setText(branchNo);
        tvTime.setText(time);
        tvAmount.setText(amount);
        tvPaidDate.setText(date);
        tvBillNo.setText(billNo);



        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Ccashsuccessful.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        tvCurrent.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{if(TextUtils.isEmpty(tvCurrent.getText().toString()))
                    Toast.makeText(Ccashsuccessful.this, "Please choose current date", Toast.LENGTH_SHORT).show();
                else{
                    String cBillNo = tvBillNo.getText().toString().trim();
                    String date = tvCurrent.getText().toString().trim();

                    cashPayment.setcBillNo(cBillNo);
                    cashPayment.setDate(date);

                    reff.child(String.valueOf(pbillNo+1)).setValue(cashPayment);
                    Toast.makeText(Ccashsuccessful.this, "saved successfully", Toast.LENGTH_SHORT).show();
                    clearControls();

                }
            }catch (Exception e){
                    Toast.makeText(Ccashsuccessful.this, "Something wrong", Toast.LENGTH_SHORT).show();}
            }
        });



    }

    private void clearControls() {
        tvCurrent.setText("");
    }
}