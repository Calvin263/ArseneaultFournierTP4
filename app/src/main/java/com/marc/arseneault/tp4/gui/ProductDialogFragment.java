package com.marc.arseneault.tp4.gui;

/**
 * Created by 1333297 on 2015-04-29.
 */

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

import com.marc.arseneault.tp4.R;
import com.marc.arseneault.tp4.TP4Activity;
import com.marc.arseneault.tp4.model.Product;

/**
 * Created by 1333297 on 2015-04-29.
 */
public class ProductDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // mise en place de l'interface a partir du XML
        final View v = inflater.inflate(R.layout.add_product, container, false);

        // recuperation de chaque element d'interface
        final Button button = (Button) v.findViewById(R.id.add);
        final EditText nom = (EditText) v.findViewById(R.id.nomProduit);
        final EditText prix = (EditText) v.findViewById(R.id.prixProduit);
        final EditText code = (EditText) v.findViewById(R.id.UPCProduit);
        final CheckBox tfo = (CheckBox) v.findViewById(R.id.checkBoxTfo);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i("Dialog", "code " + code.getText() + " ," + nom.getText() + " ($ " + prix.getText() + " )");

                //
                Product product = new Product(nom.getText().toString(), code.getText().toString(), Double.parseDouble(prix.getText().toString()), tfo.isChecked());
                if (product.getPrice() < 0 || product.getPrice() > Integer.MAX_VALUE)
                    throw new InvalidParameterException("Price");
                else if (product.getUPC().isEmpty())
                    throw new InvalidParameterException("UPC");
                else if (product.getName().isEmpty() || product.getName().length() <= 0 || product.getName().length() > 50)
                    throw new InvalidParameterException("Nom");

                TP4Activity.repoServ.SaveProduct(product);
                //

                ProductDialogFragment.this.dismiss();
            }
        });

        return v;
    }

    /*public static DialogFragment newInstance() {
        return new ProductDialogFragment();
    }*/

}