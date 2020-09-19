package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalone.Model.Customer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MLogin extends AppCompatActivity {
    public static EditText phone;
    EditText password;
    Button login;
    ProgressDialog progressDialog;
    DatabaseReference dbRef;

    private String parentID = "Customer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_login);

        phone = findViewById(R.id.editTextTextPersonName3);
        password = findViewById(R.id.editTextTextPersonName4);
        login = findViewById(R.id.button4);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phn = phone.getText().toString();
                String pwd = password.getText().toString();
                if(TextUtils.isEmpty(phn) || TextUtils.isEmpty(pwd))
                    Toast.makeText(MLogin.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(phn))
                    Toast.makeText(MLogin.this, "phone can not be blank", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(pwd))
                    Toast.makeText(MLogin.this, "Password can not be blank", Toast.LENGTH_SHORT).show();
                else{
                    validateDetails(phn,pwd);
                }
            }
        });

    }

    private void validateDetails(final String phn, final String pwd) {
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentID).child(phn).exists()){
                    Customer cusData = snapshot.child(parentID).child(phn).getValue(Customer.class);
                    if(cusData.getPhoneNo().equals(phn)){
                        if(cusData.getPassword().equals(pwd)){
                            Toast.makeText(MLogin.this, "Login successful", Toast.LENGTH_SHORT).show();

                            EditText editText = findViewById(R.id.editTextTextPersonName3);
                            String phone = editText.getText().toString();
                            Intent intent = new Intent(MLogin.this,MSelect.class);
                            intent.putExtra("EXTRA_MESSAGE",phone);
                            startActivity(intent);
                            clearControls();
                        }else{
                            Toast.makeText(MLogin.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(MLogin.this, "Phone incorrect", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(MLogin.this, "Can not login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void clearControls() {
        phone.setText("");
        password.setText("");
    }

    //redirect to the register page using intent
    public void displaySignUp(View view) {
        Intent intent = new Intent(this, MRegister.class);
        Button btn1 = (Button) findViewById(R.id.button3);
        startActivity(intent);
    }

    //use Toast to display success or if they didn't enter phone number and password ask to they enter again
   /* public void displayToast(View v) {
        phone = findViewById(R.id.editTextTextPersonName3);
        password = findViewById(R.id.editTextTextPersonName4);
        if (!phone.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            Toast.makeText(MLogin.this, "Welcome to MYDCS_APP", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MSelect.class);
            Button btn = (Button) findViewById(R.id.button4);
            startActivity(intent);

        } else
            Toast.makeText(MLogin.this, "Please enter phone number and password", Toast.LENGTH_SHORT).show();

    }*/
}


