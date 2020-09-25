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

import com.example.finalone.Model.Cash;
import com.example.finalone.Model.CashPayment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class Ccashsuccessful extends AppCompatActivity {

    //assign variables
    TextView tvphone,tvAmount,tvbranchName,tvbranchNo,tvPaidDate,tvTime,tvCurrent,tvBillNo;
    Button btnDate,btnBack,btnSave,btnLogout;
    DatabaseReference reff;
    long pbillNo=0;
    CashPayment cashPayment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccashsuccessful);

        btnLogout = findViewById(R.id.button24);
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
        //data from cashpayment table
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

        /*btnSave.setOnClickListener(new View.OnClickListener() {
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
        });*/

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });



    }

    private void createAccount() {
        CashPayment cashPayment = new CashPayment();
        cashPayment.setcBillNo(tvBillNo.getText().toString());
        cashPayment.setPhone(tvphone.getText().toString());
        cashPayment.setDate(tvCurrent.getText().toString());

        String cBillNo = cashPayment.getcBillNo();
        String cPhone = cashPayment.getPhone();
        String cDate = cashPayment.getDate();

        if(TextUtils.isEmpty(cDate))
            Toast.makeText(this, "Please enter current date", Toast.LENGTH_SHORT).show();
        else{
            ValidatePhone(cBillNo,pbillNo,cPhone,cDate);
        }
    }

    private void ValidatePhone(final String cBillNo, final long pbillNo, final String cPhone, final String cDate) {
        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.child("CashPayment").child(cPhone+cDate).exists()){
                    HashMap<String ,Object> cashPaymentMap = new HashMap<>();
                    cashPaymentMap.put("cBillNo",cBillNo);
                    cashPaymentMap.put("pBillNo",pbillNo+1);
                    cashPaymentMap.put("phone",cPhone);
                    cashPaymentMap.put("date",cDate);

                    rootRef.child("CashPayment").child(cPhone+cDate).updateChildren(cashPaymentMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Ccashsuccessful.this, "Successfully saved", Toast.LENGTH_SHORT).show();
                                clearControls();

                                CashPayment cash = new CashPayment();
                                cash.setcBillNo(cBillNo);
                                cash.setpBillNo(String.valueOf(pbillNo));
                                cash.setDate(cDate);
                                cash.setPhone(cPhone);
                            }
                        }
                    });
                }else{
                    Toast.makeText(Ccashsuccessful.this, "Already saved same information", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Ccashsuccessful.this,MLogin.class));
            }
        });
    }


    private void clearControls() {
        tvCurrent.setText("");
    }
    public void btnFinish(View view){
        startActivity(new Intent(Ccashsuccessful.this,MLogin.class));
    }
}