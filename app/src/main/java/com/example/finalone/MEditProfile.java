package com.example.finalone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MEditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_edit_profile);
    }
    //display update message using AlertDialog
    public void  showAlertDialog(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Update Details");
        alert.setMessage("Do you really want to update details?");

        //if customer click yes
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MEditProfile.this, "Update Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        //if customer click no
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MEditProfile.this,"",Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }

}