package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalone.Model.ShareData;
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


public class NShare_Main extends AppCompatActivity {

    Button btnshared;
    EditText txtPhnTo,txtAmt,txtDate;
    TextView txtPhnFrom;
    DatabaseReference dbRef,dbRef3;
    ShareData std;
    TextView tv,tvphnfrom;
    long maxid=0;
    public static final String EXTRAMESSAGE="message";
    public static final String EXTRAMESSAGE2="message2";
    String phonenumer;
    String amthve;
    Double amth,amtg;

  /* // public static final String EXTRA_MESSAGE = "com.example.finalone.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.finalone.MESSAGE2";
    public static final String EXTRA_MESSAGE3 = "com.example.finalone.MESSAGE3";
    public static final String EXTRA_MESSAGE4 = "com.example.finalone.MESSAGE4";
    public static final String EXTRA_MESSAGE5 = "com.example.finalone.MESSAGE5";


    Button btn;
    TextView dataid;
    TextView phnfrom;
    TextView phnTo;
    TextView amt;
    TextView date;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_share__main);
        final Intent intent = getIntent();
        final String phone = intent.getStringExtra("EXTRA_MESSAGE");
        phonenumer=intent.getStringExtra("EXTRA_MESSAGE");
        tv = findViewById(R.id.NtextView17);
        txtPhnFrom=findViewById(R.id.nPhnFrom);
        txtPhnFrom.setText(phone);
        tv.setText(phone);


        std=new ShareData();

        btnshared=findViewById(R.id.nbutton3);
        txtPhnTo=findViewById(R.id.NPhnTo);
        txtPhnFrom=findViewById(R.id.nPhnFrom);
        txtAmt=findViewById(R.id.NAmtData);
        txtDate=findViewById(R.id.NDate);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NShare_Main.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        txtDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        btnshared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateShareData();
            }
        });




        /*btnshared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validatephone();
                dbRef= FirebaseDatabase.getInstance().getReference().child("ShareData");
                dbRef.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            maxid=(snapshot.getChildrenCount());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                try{

                    if(TextUtils.isEmpty(txtPhnTo.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter phnto",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtPhnFrom.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter phnfrom",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtAmt.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter amt",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter date",Toast.LENGTH_SHORT).show();

                    else
                    {

                        if (!txtPhnTo.getText().toString().matches("[0-9]{10}"))
                            Toast.makeText(getApplicationContext(), "Enter correct format", Toast.LENGTH_SHORT).show();
                        else if (!txtPhnFrom.getText().toString().matches("[0-9]{10}"))
                            Toast.makeText(getApplicationContext(), "Enter correct format", Toast.LENGTH_SHORT).show();

                        else {

                            int x = Integer.parseInt(txtAmt.getText().toString());
                            if (x > 1024)
                                Toast.makeText(getApplicationContext(), "Enter correct Amount", Toast.LENGTH_SHORT).show();

                            else {

                                std.setId(String.valueOf(maxid + 1));
                                std.setPhnTo(txtPhnTo.getText().toString().trim());
                                std.setPhnFrom(txtPhnFrom.getText().toString().trim());
                                std.setAmt(txtAmt.getText().toString().trim());
                                std.setDate(txtDate.getText().toString().trim());
                                dbRef.child(String.valueOf(maxid + 1)).setValue(std);

                                Toast.makeText(getApplicationContext(), "ISaved", Toast.LENGTH_SHORT).show();

                                clearControls();

                                Intent intent = new Intent(ShareDataForm.this, SharedataDisplay.class);
                                String message = String.valueOf(maxid + 1);
                                intent.putExtra(EXTRAMESSAGE, message);
                                startActivity(intent);
                            }
                        }



                    }



                }catch(NumberFormatException e)
                {
                    Toast.makeText(getApplicationContext(),"Invaid",Toast.LENGTH_SHORT).show();
                }






            }
            });
            }

            public void clearControls() {

                txtPhnTo.setText("");
                txtPhnFrom.setText("");
                txtAmt.setText("");
                txtDate.setText("");

            }
            private void Validatephone() {
                final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();
                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!(snapshot.child("ShareData").child(txtPhnFrom.toString()).exists())) {
                            std.setId(txtPhnFrom.getText().toString().trim());
                            std.setPhnTo(txtPhnTo.getText().toString().trim());
                            std.setPhnFrom(txtPhnFrom.getText().toString().trim());
                            std.setAmt(txtAmt.getText().toString().trim());
                            std.setDate(txtDate.getText().toString().trim());
                            dbRef.child(txtPhnFrom.getText().toString().trim()).setValue(std);

                            Toast.makeText(getApplicationContext(), "ISaved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ShareDataForm.this, SharedataDisplay.class);
                            String message = txtPhnFrom.getText().toString().trim();
                            intent.putExtra(EXTRAMESSAGE, message);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "Exists", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
    }

    private void CreateShareData() {

        final DatabaseReference FixRef;
        FixRef=FirebaseDatabase.getInstance().getReference();
        FixRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("FixData").child(tv.getText().toString()).exists())
                {
                    ShareData std=new ShareData();

                    std.setId(txtPhnFrom.getText().toString()+txtPhnTo.getText().toString());
                    std.setPhnTo(txtPhnTo.getText().toString());
                    std.setPhnFrom(txtPhnFrom.getText().toString());
                    std.setAmt(txtAmt.getText().toString());
                    std.setDate(txtDate.getText().toString());
                    std.setPhnFromto(txtPhnFrom.getText().toString()+txtPhnTo.getText().toString());

                    String id=std.getId();
                    String phnto=std.getPhnTo();
                    String phnFrom=std.getPhnFrom();
                    String amt=std.getAmt();
                    String date=std.getDate();
                    String phnFromto=std.getPhnFromto();




                    if(TextUtils.isEmpty(txtPhnTo.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter phnto",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtPhnFrom.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter phnfrom",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtAmt.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter amt",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter date",Toast.LENGTH_SHORT).show();
                    else {
                        if (!Phone(txtPhnTo.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Enter correct format", Toast.LENGTH_SHORT).show();
                        else if (!Phone(txtPhnFrom.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Enter correct format", Toast.LENGTH_SHORT).show();
                        else {
                            int x = Integer.parseInt(txtAmt.getText().toString());
                            if (x > 1024)
                                Toast.makeText(getApplicationContext(), "Enter correct Amount", Toast.LENGTH_SHORT).show();
                            else {
                                if (txtPhnTo.getText().toString().equals(txtPhnFrom.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Cant do transaction", Toast.LENGTH_SHORT).show();
                               /* else if (!isValiddate(txtDate.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Date invalid", Toast.LENGTH_SHORT).show();*/
                                else
                                    Validatephnone(id, phnto, phnFrom, amt, date,phnFromto);
                            }
                        }
                    }



                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please activate fix data package", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    private void Validatephnone(final String id, final String phnto, final String phnFrom, final String amt, final String date,final String phnFromto) {

        final DatabaseReference RootRef;
        RootRef=FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("ShareData").child(phnFromto.toString()).exists()))
                {

                    HashMap<String,Object> userdataMap=new HashMap<>();
                    userdataMap.put("phnFrom",phnFrom);
                    userdataMap.put("phnTo",phnto);
                    userdataMap.put("id",phnFromto);
                    userdataMap.put("date",date);
                    userdataMap.put("amt",amt);
                    userdataMap.put("phnFromto",phnFromto);


                    RootRef.child("ShareData").child(phnFromto.toString()).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(NShare_Main.this, "Successfull", Toast.LENGTH_SHORT).show();

                                ShareData s1=new ShareData();
                                s1.setAmt(amt);
                                s1.setPhnTo(phnto);
                                s1.setPhnFrom(phnFrom);
                                s1.setDate(date);
                                s1.setId(phnFromto);
                                s1.setPhnFromto(phnFromto);

                                Intent intent = new Intent(NShare_Main.this, NShareDisplay.class);
                                String message = txtPhnFrom.getText().toString().trim();
                                String message2=txtPhnFrom.getText().toString().trim()+txtPhnTo.getText().toString();
                                intent.putExtra(EXTRAMESSAGE, message);
                                intent.putExtra(EXTRAMESSAGE2,message2);
                                startActivity(intent);

                            }

                        }
                    });




                }
                else
                {
                    Toast.makeText(NShare_Main.this, "Enter another phone number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public boolean Phone(String s1) {
        if (s1.matches("[0-9]{10}")) {
            return true;
        } else {
            return false;
        }
    }






   /* public boolean isValiddate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date testdate = null;
        try {
            testdate = sdf.parse(date);
        } catch (ParseException e) {
            String errorMessage = "the date is invalid";
            return false;

        }
        if(!sdf.format(testdate).equals(date))
        {
            String errorMessage = "the date is invalid";
            return false;
        }
        return true;
    }*/
}