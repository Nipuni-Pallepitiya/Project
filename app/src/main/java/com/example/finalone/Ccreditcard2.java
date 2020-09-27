package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalone.Model.CreditCard;
import com.example.finalone.Model.CreditPayment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Ccreditcard2 extends AppCompatActivity {

    TextView p1, p2, tota;
    DatePickerDialog.OnDateSetListener setListener;
    Button ok, csave, show, edit, eDate, cshow, cdelete;
    TextView bfrom, bto, bno, edate;
    DatabaseReference dbRef;
    CreditCard c;
    //CreditPayment c1;
    EditText name, no;
    //long billNo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccreditcard2);

        p1 = findViewById(R.id.phone1);
        p2 = findViewById(R.id.phone2);
        tota = findViewById(R.id.amount);
        ok = findViewById(R.id.cok);
        csave = findViewById(R.id.save);
        //show = findViewById(R.id.phone2);
        //edit = findViewById(R.id.phone2);
        bfrom = findViewById(R.id.datet2);
        bto = findViewById(R.id.datet);
        dbRef = FirebaseDatabase.getInstance().getReference().child("CreditCard");
        name = findViewById(R.id.cname);
        no = findViewById(R.id.cno);
        //bno = findViewById(R.id.billno);
        eDate = findViewById(R.id.date);
        edate = findViewById(R.id.exdate);
        cshow = findViewById(R.id.Show);
        cdelete = findViewById(R.id.delete);

        dbRef = FirebaseDatabase.getInstance().getReference().child("CreditCard");
        c = new CreditCard();

        name = findViewById(R.id.cname);
        no = findViewById(R.id.cno);
        bno = findViewById(R.id.billno);
        eDate = findViewById(R.id.date);

        final Intent intent3 = getIntent();

        String dateFrom = intent3.getStringExtra("billFrom");
        String dateTO = intent3.getStringExtra("billTo");
        String price = intent3.getStringExtra("billfixprice");
        final String phone1 = intent3.getStringExtra("phone");
        final String billNo = intent3.getStringExtra("billNo");

        bfrom.setText(dateFrom);
        bto.setText(dateTO);
        tota.setText(price);
        p1.setText(phone1);
        p2.setText(phone1);
        bno.setText(billNo);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Ccreditcard2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        csave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });

        cshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("CreditCard").child(phone1);
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            no.setText(snapshot.child("cardNo").getValue().toString());
                            name.setText(snapshot.child("Name").getValue().toString());
                            edate.setText(snapshot.child("edate").getValue().toString());
                            p1.setText(snapshot.child("phoneNo").getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        cdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("CreditCard");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phone1)) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("CreditCard").child(phone1);
                            dbRef.removeValue();

                            Toast.makeText(Ccreditcard2.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                            clearControls();
                            //startActivity(new Intent(Ccreditcard2.this,MRegister.class));

                        } else {
                            Toast.makeText(Ccreditcard2.this, "Can not delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("CreditCard").child(phone1);
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            String amount = tota.getText().toString();
                            String billNo = bno.getText().toString();
                            String dateFrom = bfrom.getText().toString();
                            String dateTo = bto.getText().toString();
                            String CName = (snapshot.child("Name").getValue().toString());
                            String CNo = (snapshot.child("cardNo").getValue().toString());
                            String date = (snapshot.child("edate").getValue().toString());
                            String phone = (snapshot.child("phoneNo").getValue().toString());

                            Intent intent4 = new Intent(Ccreditcard2.this, Ccreditsuccessful.class);
                            intent4.putExtra("amount", amount);
                            intent4.putExtra("CName", CName);
                            intent4.putExtra("CNo", CNo);
                            intent4.putExtra("date", date);
                            intent4.putExtra("phone", phone);
                            intent4.putExtra("billNo", billNo);
                            intent4.putExtra("dateFrom", dateFrom);
                            intent4.putExtra("dateTo", dateTo);

                            startActivity(intent4);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });
    }

    private void CreateAccount() {
        CreditCard card = new CreditCard();
        card.setName(name.getText().toString());
        card.setCardNo(no.getText().toString());
        card.setEdate(edate.getText().toString());
        card.setPhoneNo(p1.getText().toString());


        String CName = card.getName();
        String CNo = card.getCardNo();
        String date = card.getEdate();
        String phone = card.getPhoneNo();

        //boolean num = creditCardNoValidation(CNo);
        //boolean nam = creditCardNameValidation(CName);
        boolean date1 = isValidMonth(date);

        if (name.length() == 0 || no.length() == 0 || edate.length() == 0) {
            Toast.makeText(Ccreditcard2.this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }
        else if (creditCardNameValidation(CName) == false) {
            Toast.makeText(this, "Enter the Card holder's name in letters", Toast.LENGTH_SHORT).show();
        }
        else if (creditCardNoValidation(CNo) == false) {
            Toast.makeText(this, "Enter the credit card number in the correct format", Toast.LENGTH_SHORT).show();
        }
        else if (validCard(CNo) == false) {
            Toast.makeText(this, "Credit card is invalid", Toast.LENGTH_SHORT).show();
        }
        else {
            ValidatePhone(phone, CName, CNo, date);
        }
    }

    @SuppressLint("SimpleDateFormat")
    public boolean isValidMonth(String ccMonth) {
        return ccMonth.compareTo(new SimpleDateFormat("dd/MM/yy").format(new Date())) >= 1;
    }

    public boolean validCard(String s2){
        int[] ints = new int[s2.length()];
        for (int i = 0; i < s2.length(); i++) {
            ints[i] = Integer.parseInt(s2.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int anInt : ints) {
            sum += anInt;
        }
        if (sum % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean creditCardNoValidation(String s2) {
        if(s2.matches("[0-9]{10,16}")) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean creditCardNameValidation(String s1) {
        if (s1.matches("[A-Z, a-z]{5,20}")) {
            return true;
        } else {
            return false;
        }
    }






    private void ValidatePhone(final String phone, final String CName, final String CNo, final String date) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.child("CreditCard").child(phone).exists()){
                    HashMap<String ,Object> creditMap = new HashMap<>();
                    creditMap.put("Name",CName);
                    creditMap.put("cardNo",CNo);
                    creditMap.put("edate",date);
                    creditMap.put("phoneNo",phone);

                    RootRef.child("CreditCard").child(phone).updateChildren(creditMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Ccreditcard2.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                                clearControls();

                                CreditCard cred = new CreditCard();

                                cred.setCardNo(CNo);
                                cred.setName(CName);
                                cred.setEdate(date);
                                cred.setPhoneNo(phone);
                            }
                        }
                    });


                }else{
                    Toast.makeText(Ccreditcard2.this, "Already saved same Information", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void clearControls(){
        edate.setText("");
        name.setText("");
        no.setText("");
    }
/*
    public void creditSuccessful(View view){
        Toast.makeText(Ccreditcard2.this, "Saving credit card details", Toast.LENGTH_SHORT).show();
        Toast.makeText(Ccreditcard2.this, "Paying", Toast.LENGTH_SHORT).show();
        Intent creditSuccessful = new Intent(this, Ccreditsuccessful.class);
        Button creditSuccessButton = (Button) findViewById(R.id.cok);
        startActivity(creditSuccessful);
    }

 */
}