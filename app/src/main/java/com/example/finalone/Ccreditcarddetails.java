package com.example.finalone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ccreditcarddetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccreditcarddetails);
    }

    public void updateCreditCard(View view){
        Intent updateCredit = new Intent(this, Cupdatecreditdcarddetails.class);
        Button updateCreditButton = (Button) findViewById(R.id.c_button23);
        startActivity(updateCredit);
    }

    public void delete(View view){
        AlertDialog.Builder delete = new AlertDialog.Builder(this);
        delete.setMessage("Do you want to delete the credit card details?");
        delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Ccreditcarddetails.this,"Deleting",Toast.LENGTH_SHORT).show();
                Toast.makeText(Ccreditcarddetails.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        delete.create().show();
    }
}