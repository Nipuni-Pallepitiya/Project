package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalone.Model.Customer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MEditProfile extends AppCompatActivity {
    private TextView nameTextView,phoneTextView,emailTextView;


    TextView phone;
    Button btnShow;
    Button update;
    ImageButton imageButton1;
    DatabaseReference dbRef;

    Customer cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_edit_profile);

        phone = findViewById(R.id.textView32);
        nameTextView = findViewById(R.id.editTextTextPersonName5);
        phoneTextView = findViewById(R.id.editTextTextPersonName7);
        emailTextView = findViewById(R.id.editTextTextPersonName22);
        btnShow = findViewById(R.id.button13);
        update = findViewById(R.id.button14);
        imageButton1 =  findViewById(R.id.imageButton5);
        cus = new Customer();

       Intent intent2 = getIntent();
        final String phone1 = intent2.getStringExtra("EXTRA_MESSAGE");
        phone.setText(phone1);



       btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(phone1);
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){
                            nameTextView.setText(snapshot.child("name").getValue().toString());
                           phoneTextView.setText(snapshot.child("phoneNo").getValue().toString());
                            emailTextView.setText(snapshot.child("email").getValue().toString());
                            phone.setText(snapshot.child("phoneNo").getValue().toString());


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Customer");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(phone1)) {

                             if (!Patterns.EMAIL_ADDRESS.matcher(emailTextView.getText().toString()).matches())
                                Toast.makeText(MEditProfile.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
                            else {
                                cus.setName(nameTextView.getText().toString().trim());
                                cus.setPhoneNo(phoneTextView.getText().toString().trim());
                                cus.setEmail(emailTextView.getText().toString().trim());
                                cus.setPhoneNo(phone.getText().toString().trim());

                                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(phone1);
                                dbRef.setValue(cus);
                                clearControls();

                                Toast.makeText(MEditProfile.this, "updated successfully", Toast.LENGTH_SHORT).show();
                                imageButton1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(phone1);
                                        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.hasChildren()) {
                                                    String cusName = (snapshot.child("name").getValue().toString());
                                                    String cusPhone = (snapshot.child("phoneNo").getValue().toString());
                                                    String cusEmail = (snapshot.child("email").getValue().toString());

                                                    Intent intent2 = new Intent(getBaseContext(), MProfile.class);
                                                    intent2.putExtra("FULL", cusName);
                                                    intent2.putExtra("PHONE", cusPhone);
                                                    intent2.putExtra("EMAIL", cusEmail);
                                                    startActivity(intent2);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                });


                            }
                        }
                        else{
                            Toast.makeText(MEditProfile.this, "can not update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });




    }



    private void clearControls() {
        nameTextView.setText("");
        phoneTextView.setText("");
        emailTextView.setText("");
    }


    /*public void update(View view){

        update = findViewById(R.id.button14);
        if(isNameChanged() || isPhoneChanged() || isEmailChanged()){
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Data can not be update", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isNameChanged(){
        reference = FirebaseDatabase.getInstance().getReference().child("Customer").child(String.valueOf(phone));
        if(!_USERNAME.equals(nameTextView.getText().toString())){
            reference.child(_USERNAME).child("name").setValue(nameTextView.getText().toString());
            return true;
        }else{
            return false;
        }

    }
    private boolean isPhoneChanged(){
        reference = FirebaseDatabase.getInstance().getReference("Customer");
        if(!_PHONE.equals(phoneTextView.getText().toString())){
            reference.child(_PHONE).child("phoneNo").setValue(phoneTextView.getText().toString());
            return true;
        }else{
            return false;
        }

    }
    private boolean isEmailChanged(){
        if(!_EMAIL.equals(emailTextView.getText().toString())){
            reference.child(_EMAIL).child("email").setValue(emailTextView.getText().toString());
            return true;
        }else{
            return false;
        }
    }*/


}