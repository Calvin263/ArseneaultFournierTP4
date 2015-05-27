package com.marc.arseneault.tp4;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.security.InvalidParameterException;

import model.Product;

/**
 * Created by 1333297 on 2015-05-27.
 */
public class TakeChangeDialogFragment extends DialogFragment{

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // mise en place de l'interface a partir du XML
        final View v = inflater.inflate(R.layout.take_change, container, false);

        // recuperation de chaque element d'interface
            final Button button = (Button) v.findViewById(R.id.buttonPayer);
            final EditText billet100 = (EditText) v.findViewById(R.id.edit100);
            final EditText billet50 = (EditText) v.findViewById(R.id.edit50);
            final EditText billet20 = (EditText) v.findViewById(R.id.edit20);
            final EditText billet10 = (EditText) v.findViewById(R.id.edit10);
            final EditText billet5 = (EditText) v.findViewById(R.id.edit5);
            final EditText billet025 = (EditText) v.findViewById(R.id.edit025);
            final EditText billet010 = (EditText) v.findViewById(R.id.edit010);
            final EditText billet005 = (EditText) v.findViewById(R.id.edit005);
            final EditText billet001 = (EditText) v.findViewById(R.id.edit001);

            int nb100 = Integer.parseInt(billet100.getText().toString());
            int nb50 = Integer.parseInt(billet50.getText().toString());
            int nb20 = Integer.parseInt(billet20.getText().toString());
            int nb10 = Integer.parseInt(billet10.getText().toString());
            int nb5 = Integer.parseInt(billet5.getText().toString());
            int nb025 = Integer.parseInt(billet025.getText().toString());
            int nb010 = Integer.parseInt(billet010.getText().toString());
            int nb005 = Integer.parseInt(billet005.getText().toString());
            int nb001 = Integer.parseInt(billet001.getText().toString());

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i("Dialog", "patate");

                //

                //

                TakeChangeDialogFragment.this.dismiss();
            }
        });

        return v;
    }
}
