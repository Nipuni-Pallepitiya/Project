package com.example.finalone;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.finalone.Model.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

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
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String conpw = re_enter.getText().toString();

        Customer cus  = new Customer();
        cus.setName(FullName.getText().toString());
        cus.setPhoneNo(phone.getText().toString());
        cus.setEmail(email.getText().toString());
        cus.setPassword(password.getText().toString());

        String name = cus.getName();
        String phone = cus.getPhoneNo();
        String email = cus.getEmail();
        String password = cus.getPassword();
        if(FullName.length()==0 || phone.length()==0 || email.length()==0 || password.length()==0 || re_enter.length()==0)
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
       else if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter Full name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show();
        }
        else if(!phone.matches("[0-9]{10}")){
            Toast.makeText(this, "Please enter valid phone", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()){
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<6){
            Toast.makeText(this, "Please enter password greater than 6 ", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(conpw)){
            Toast.makeText(this, "Please enter password again", Toast.LENGTH_SHORT).show();

        }
        else{
            ValidateCustomer(name,phone,email,password,conpw);
        }
    }

    private void ValidateCustomer(final String name, final String phone, final String email, final String password, final String conpw) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.child("Customer").child(phone).exists()){
                    if(password.equals(conpw)){
                        HashMap<String ,Object> customerDataMap = new HashMap<>();
                        customerDataMap.put("name",name);
                        customerDataMap.put("phoneNo",phone);
                        customerDataMap.put("email",email);
                        customerDataMap.put("password",password);

                        RootRef.child("Customer").child(phone).updateChildren(customerDataMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(MRegister.this, "Successfully registered", Toast.LENGTH_SHORT).show();


                                            Customer c = new Customer();

                                            c.setName(name);
                                            c.setPhoneNo(phone);
                                            c.setEmail(email);
                                            c.setPassword(password);

                                            Intent intent = new Intent(MRegister.this,MLogin.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(MRegister.this, "Something wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
                    else{
                        Toast.makeText(MRegister.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MRegister.this, "This " +phone+ " Already exists ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
    public boolean isValidatePhone(String phone){
        /*if(!phone.isEmpty())
            return true;*/
         if(phone.matches("[0-9]{10}"))
            return true;
        else
            return false;
    }

    public boolean isValidatePassword(String password){
        if(password.length()>6){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isValidateConfirm(String password ,String confirm){
        if(password.equals(confirm))
            return true;
        else
            return false;

    }






}