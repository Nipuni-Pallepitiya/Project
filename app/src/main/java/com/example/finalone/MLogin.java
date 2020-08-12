package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MLogin extends AppCompatActivity {
    EditText phone;
    EditText password;
    Button login;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_login);
    }
    //redirect to the register page using intent
    public void displaySignUp(View view) {
        Intent intent = new Intent(this, MRegister.class);
        Button btn1 = (Button) findViewById(R.id.button3);
        startActivity(intent);
    }

    //use Toast to display success or if they didn't enter phone number and password ask to they enter again
    public void displayToast(View v) {
        phone = findViewById(R.id.editTextTextPersonName3);
        password = findViewById(R.id.editTextTextPersonName4);
        if (!phone.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            Toast.makeText(MLogin.this, "Welcome to MYDCS_APP", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MSelect.class);
            Button btn = (Button) findViewById(R.id.button4);
            startActivity(intent);

        } else
            Toast.makeText(MLogin.this, "Please enter phone number and password", Toast.LENGTH_SHORT).show();

    }
}


