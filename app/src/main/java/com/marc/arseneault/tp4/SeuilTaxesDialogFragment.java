package com.marc.arseneault.tp4;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 1333297 on 2015-05-26.
 */
public class SeuilTaxesDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // mise en place de l'interface a partir du XML
        final View v = inflater.inflate(R.layout.seuil_taxes, container, false);

        // recuperation de chaque element d'interface
        final Button button = (Button) v.findViewById(R.id.saveTaxes);
        final EditText seuilTaxes = (EditText) v.findViewById(R.id.seuilTaxes);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i("Dialog", "seuil: " + seuilTaxes.getText() );

                //
                TP4Activity.seuilTaxes =  Double.parseDouble(seuilTaxes.getText().toString());
                //

                SeuilTaxesDialogFragment.this.dismiss();
            }
        });

        return v;
    }

}