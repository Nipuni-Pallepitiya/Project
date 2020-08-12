package com.example.finalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalone.R;

public class MainActivity extends AppCompatActivity {
    public static final String LOGIN = "com.example.ourproject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //redirect to the login page using Intent
    public void displayLogin(View view){
        Intent intent = new Intent(this,MLogin.class);
        Button btn = (Button) findViewById(R.id.button);
        startActivity(intent);

    }
    //redirect to the Register page using Intent
    public void displaySignUp(View view){
        Intent intent = new Intent(this,MRegister.class);
        Button btn1 = (Button) findViewById(R.id.button2);
        startActivity(intent);
    }
}