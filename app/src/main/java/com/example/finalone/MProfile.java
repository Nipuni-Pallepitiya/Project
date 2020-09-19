package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalone.Model.Customer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


public class MProfile extends AppCompatActivity {
    private TextView phoneView;
    private TextView nameTextView, phoneTextView, emailTextView,phoneNo;
    private ImageView nameImageView, emailImageView, phoneImageView;

    private Button btnEdit,btnLogout;

    DatabaseReference dbRef;
    private static final String CUSTOMER = "Customer";
    Customer cus;
    Button btnShow,btnDelete;

    private ImageButton btnImage,btnImage1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_profile);

        phoneNo = findViewById(R.id.textView27);
        nameImageView = findViewById(R.id.imageView28);
        nameTextView = findViewById(R.id.textView37);
        phoneImageView = findViewById(R.id.imageView31);
        phoneTextView = findViewById(R.id.textView22);
        emailImageView = findViewById(R.id.imageView35);
        emailTextView = findViewById(R.id.textView102);
        btnImage = findViewById(R.id.imageButton7);
        btnDelete = findViewById(R.id.button8);
        btnLogout = findViewById(R.id.button14);
        //btnEdit = findViewById(R.id.button7);
        cus = new Customer();



        Intent intent1 = getIntent();


        final String phone = intent1.getStringExtra("EXTRA_MESSAGE");
        TextView textView = findViewById(R.id.textView27);
        textView.setText(phone);

        Intent intent2 = getIntent();
        String name = intent2.getStringExtra("name");
        final String phoneNumber = intent2.getStringExtra("phoneNo");
        String emailAddress = intent2.getStringExtra("email");

        phoneNo.setText(phoneNumber);
        nameTextView.setText(name);
        phoneTextView.setText(phoneNumber);
        emailTextView.setText(emailAddress);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(phoneNumber)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(phoneNumber);
                            dbRef.removeValue();

                            Toast.makeText(MProfile.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MProfile.this,MLogin.class));

                        }else{
                            Toast.makeText(MProfile.this, "Can not delete", Toast.LENGTH_SHORT).show();
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
        phoneTextView.setText("");
        nameTextView.setText("");
        emailTextView.setText("");
    }


    //redirect to the Edit profile page
    public void displayMEdit(View view) {
        TextView textView = findViewById(R.id.textView27);
        String phone = textView.getText().toString();
        Intent intent = new Intent(MProfile.this, MEditProfile.class);
        intent.putExtra("EXTRA_MESSAGE", phone);
        startActivity(intent);



    }
    public void editDetails(View view){
        btnImage1 = findViewById(R.id.imageButton7);
        phoneView= findViewById(R.id.textView27);

        Intent intent3 = getIntent();
        String Full = intent3.getStringExtra("FULL");
        String phoneNo = intent3.getStringExtra("PHONE");
        String emailAdd = intent3.getStringExtra("EMAIL");

        phoneView.setText(phoneNo);
        nameTextView.setText(Full);
        phoneTextView.setText(phoneNo);
        emailTextView.setText(emailAdd);

    }
    public void logout(View view){
        Intent intent = new Intent(this,MLogin.class);
        Button btn = (Button) findViewById(R.id.button14);
        startActivity(intent);
    }


}







