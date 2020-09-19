package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalone.Model.Customer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MRegister extends AppCompatActivity {

    public static final String FULL_NAME = "com.example.ourproject.FULL_NAME";
    public static final String PHONE_NUMBER = "com.example.ourproject.PHONE_NUMBER";
    public static final String EMAIL = "com.example.ourproject.EMAIL";

    EditText FullName;
    EditText phone;
    EditText password;
    EditText email;
    EditText re_enter;
    Button signup;
    DatabaseReference dbRef;
    Customer cus;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_register);

        FullName = findViewById(R.id.editTextTextPersonName2);
        phone = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextPersonName6);
        password = findViewById(R.id.editTextTextPassword2);
        re_enter = findViewById(R.id.editTextTextPassword3);
        signup = findViewById(R.id.button6);

        cus = new Customer();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
                try{
                    if(FullName.length()==0 || email.length()==0 || phone.length()==0 || password.length()==0 || re_enter.length()==0)
                        Toast.makeText(MRegister.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(FullName.getText().toString()))
                        Toast.makeText(MRegister.this, "Please enter Full Name", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(phone.getText().toString()))
                        Toast.makeText(MRegister.this, "Please enter phone number", Toast.LENGTH_SHORT).show();
                    else if(!phone.getText().toString().matches("[0-9]{10}"))
                        Toast.makeText(MRegister.this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(MRegister.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
                        Toast.makeText(MRegister.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(password.getText().toString()))
                        Toast.makeText(MRegister.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(re_enter.getText().toString()))
                        Toast.makeText(MRegister.this, "Please re enter password", Toast.LENGTH_SHORT).show();
                    /*else if(!phone.getText().toString().matches(String.valueOf(re_enter)))
                        Toast.makeText(MRegister.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();*/
                    else{
                        cus.setName(FullName.getText().toString().trim());
                        cus.setPhoneNo(phone.getText().toString().trim());
                        cus.setEmail(email.getText().toString().trim());
                        cus.setPassword(password.getText().toString().trim());

                        dbRef.child(phone.getText().toString().trim()).setValue(cus);

                        Toast.makeText(MRegister.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        clearControls();
                        startActivity(new Intent(MRegister.this,MLogin.class));
                    }


                }catch (Exception e) {
                    Toast.makeText(MRegister.this, "Can not insert", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void clearControls() {
        FullName.setText("");
        phone.setText("");
        email.setText("");
        password.setText("");
        re_enter.setText("");
    }

    //redirect to the login page
    public void displayLogin(View view) {
        Intent intent = new Intent(this, MLogin.class);
        Button btn = (Button) findViewById(R.id.button5);
        startActivity(intent);
    }
    //redirect to the login page
    /*public void displaySignUp(final View view) {
        FullName = findViewById(R.id.editTextTextPersonName2);
        phone = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextPersonName6);
        password = findViewById(R.id.editTextTextPersonName5);
        re_enter = findViewById(R.id.editTextTextPersonName7);
        signup = findViewById(R.id.button6);

        if (!FullName.getText().toString().isEmpty() &&!phone.getText().toString().isEmpty() && !email.getText().toString().isEmpty()&&!password.getText().toString().isEmpty() && !re_enter.getText().toString().isEmpty()) {
            Toast.makeText(MRegister.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MLogin.class);
            Button btn = (Button) findViewById(R.id.button6);
            startActivity(intent);
        }
        else{
            Toast.makeText(MRegister.this, "Please fill the all details", Toast.LENGTH_SHORT).show();
        }


    }*/

}