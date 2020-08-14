package com.example.finalone;

import android.app.AlertDialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class PDialog extends AppCompatDialogFragment {
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Activating Package");
        builder.setMessage("Do you really want to activate this package?");
        builder.setNegativeButton("No",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity().getApplicationContext(),"Activating...",Toast.LENGTH_LONG).show();

            }
        });
        return builder.create();
    }
}
