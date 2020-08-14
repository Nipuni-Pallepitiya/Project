package com.example.finalone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Cupdatecreditdcarddetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupdatecreditdcarddetails);
    }

    public void update(View view){
        AlertDialog.Builder update = new AlertDialog.Builder(this);
        update.setMessage("Do you want to update the changes?");
        update.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Cupdatecreditdcarddetails.this,"Updating",Toast.LENGTH_SHORT).show();
                Toast.makeText(Cupdatecreditdcarddetails.this,"Updated",Toast.LENGTH_SHORT).show();
            }
        });

        update.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        update.create().show();
    }

}