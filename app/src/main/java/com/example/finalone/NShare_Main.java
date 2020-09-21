package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalone.Model.ShareData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NShare_Main extends AppCompatActivity {

    Button btnshared;
    EditText txtPhnTo,txtAmt,txtDate;
    TextView txtPhnFrom;
    DatabaseReference dbRef;
    ShareData std;
    TextView tv,tvphnfrom;
    long maxid=0;
    public static final String EXTRAMESSAGE="message";

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
        tv = findViewById(R.id.NtextView17);
        txtPhnFrom=findViewById(R.id.nPhnFrom);
        txtPhnFrom.setText(phone);
        tv.setText(phone);

       /* btn=findViewById(R.id.nbutton3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NShare_Main.this, NShareDisplay.class);
                startActivity(intent);

            }
        });*/
        std=new ShareData();

        btnshared=findViewById(R.id.nbutton3);
        txtPhnTo=findViewById(R.id.NPhnTo);
        txtPhnFrom=findViewById(R.id.nPhnFrom);
        txtAmt=findViewById(R.id.NAmtData);
        txtDate=findViewById(R.id.NDate);


        btnshared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child("ShareData");
                dbRef.addValueEventListener(new ValueEventListener() {
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

                                Intent intent = new Intent(NShare_Main.this, NShareDisplay.class);
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


    /*public void OnClickShareData(View v) {

        Intent intent = new Intent(this, NShareDisplay.class);
        Button btn1 = (Button) findViewById(R.id.nbutton3);


        btn= findViewById(R.id.nbutton3);
        //dataid = findViewById(R.id.NshareData);
        phnfrom = findViewById(R.id.nPhnFrom);
        phnTo = findViewById(R.id.NPhnTo);
        amt = findViewById(R.id.NAmtData);
        date = findViewById(R.id.NDate);


        /*EditText editText = (EditText) findViewById(R.id.NshareData);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);*/


        /*EditText editText2 = (EditText) findViewById(R.id.nPhnFrom);
        String message2 = editText2.getText().toString();
        intent.putExtra(EXTRA_MESSAGE2, message2);


        EditText editText3 = (EditText) findViewById(R.id.NPhnTo);
        String message3 = editText3.getText().toString();
        intent.putExtra(EXTRA_MESSAGE3, message3);

        EditText editText4 = (EditText) findViewById(R.id.NAmtData);
        String message4 = editText4.getText().toString();
        intent.putExtra(EXTRA_MESSAGE4, message4);


        EditText editText5 = (EditText) findViewById(R.id.NDate);
        String message5 = editText5.getText().toString();
        intent.putExtra(EXTRA_MESSAGE5, message5);


        if (!dataid.getText().toString().isEmpty() && !phnfrom.getText().toString().isEmpty() && !phnTo.getText().toString().isEmpty() && !amt.getText().toString().isEmpty() && !date.getText().toString().isEmpty()) {
            Toast.makeText(NShare_Main.this, "Processing request", Toast.LENGTH_LONG).show();
            startActivity(intent);

        } else
            Toast.makeText(NShare_Main.this, "Please enter all details", Toast.LENGTH_LONG).show();


    }*/

    public void NBackClick(View v)
    {
        Intent intent = new Intent(this, NShare_Data.class);
        Button btn1 = (Button) findViewById(R.id.NBackMain);
        startActivity(intent);

    }
    public void clearControls()
    {

        txtPhnTo.setText("");
        txtPhnFrom.setText("");
        txtAmt.setText("");
        txtDate.setText("");

    }
}