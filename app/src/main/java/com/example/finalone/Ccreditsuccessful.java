package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;

public class Ccreditsuccessful extends AppCompatActivity {

    TextView p1, p2, tota;
    //DatePickerDialog.OnDateSetListener setListener;
    Button logout, cmenu;
    TextView bfrom, bto, no, bno;
    DatabaseReference dbRef;
    CreditPayment c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccreditsuccessful);

        p1 = findViewById(R.id.phone1);
        p2 = findViewById(R.id.phone2);
        tota = findViewById(R.id.amount);
        bfrom = findViewById(R.id.datef);
        bto = findViewById(R.id.datet);
        no = findViewById(R.id.cno);
        bno = findViewById(R.id.billno);

        dbRef = FirebaseDatabase.getInstance().getReference().child("CreditPayment");
        c1 = new CreditPayment();

        cmenu = findViewById(R.id.menu);
        //logout = findViewById(R.id.imageButton12);

        final Intent intent4 = getIntent();

        String dateFrom = intent4.getStringExtra("dateFrom");
        String dateTO = intent4.getStringExtra("dateTo");
        String amount = intent4.getStringExtra("amount");
        final String phone1 = intent4.getStringExtra("phone");
        final String billNo = intent4.getStringExtra("billNo");
        String Cno = intent4.getStringExtra("CNo");

        bfrom.setText(dateFrom);
        bto.setText(dateTO);
        tota.setText(amount);
        p1.setText(phone1);
        p2.setText(phone1);
        no.setText(Cno);
        bno.setText(billNo);

        cmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }

        });

    }
    private void CreateAccount() {
            CreditPayment card = new CreditPayment();
            card.setCno(no.getText().toString());
            card.setBillno(bno.getText().toString());

            String bino = card.getBillno();
            String CNo = card.getCno();

                ValidatePhone(bino, CNo);
        }


        private void ValidatePhone(final String bino, final String CNo) {
            final DatabaseReference RootRef;
            RootRef = FirebaseDatabase.getInstance().getReference();
            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.child("CreditPayment").child(bino).exists()){
                        HashMap<String ,Object> creditMap = new HashMap<>();
                        creditMap.put("Billno",bino);
                        creditMap.put("Cno",CNo);

                        RootRef.child("CreditPayment").child(bino).updateChildren(creditMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Ccreditsuccessful.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                                   // clearControls();

                                    CreditPayment cred = new CreditPayment();

                                    cred.setBillno(bino);
                                    cred.setCno(CNo);

                                    Intent intent4 = new Intent(Ccreditsuccessful.this, MSelect.class);
                                    startActivity(intent4);
                                }
                            }
                        });


                    }else{
                        Toast.makeText(Ccreditsuccessful.this, "Already saved same Information", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
}