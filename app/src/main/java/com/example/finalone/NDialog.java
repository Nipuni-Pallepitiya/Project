package com.example.finalone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class NDialog extends AppCompatDialogFragment{
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Finish")
                .setMessage("Leave this page")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                    }
                });
        return builder.create();
    }


}
