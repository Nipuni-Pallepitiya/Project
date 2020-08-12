package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_register);

    }
    //redirect to the login page
    public void displayLogin(View view) {
        Intent intent = new Intent(this, MLogin.class);
        Button btn = (Button) findViewById(R.id.button5);
        startActivity(intent);
    }
    //redirect to the login page
    public void displaySignUp(final View view) {
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


    }

}