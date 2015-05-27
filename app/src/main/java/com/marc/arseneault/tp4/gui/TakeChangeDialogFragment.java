package com.marc.arseneault.tp4.gui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marc.arseneault.tp4.R;
import com.marc.arseneault.tp4.model.monies.Change;
import com.marc.arseneault.tp4.model.monies.IChange;
import com.marc.arseneault.tp4.model.monies.Money;
import com.marc.arseneault.tp4.model.monies.exceptions.CashException;
import com.marc.arseneault.tp4.model.monies.exceptions.GiveChangeDialogFragment;

import java.security.InvalidParameterException;

/**
 * Created by 1333297 on 2015-05-27.
 */
public class TakeChangeDialogFragment extends DialogFragment {

    public double m_total;
    public double m_change;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // mise en place de l'interface a partir du XML
        final View v = inflater.inflate(R.layout.take_change, container, false);

        // recuperation de chaque element d'interface
        final Button button = (Button) v.findViewById(R.id.buttonPayer);
        final Button update = (Button) v.findViewById(R.id.buttonUpdate);
        final TextView totalChange = (TextView) v.findViewById(R.id.textTotal);
        final TextView totalFacture = (TextView) v.findViewById(R.id.textFacture);

        totalFacture.setText("$ " + m_total);
        m_change = 0;

        final EditText billet100 = (EditText) v.findViewById(R.id.edit100);
        final EditText billet50 = (EditText) v.findViewById(R.id.edit50);
        final EditText billet20 = (EditText) v.findViewById(R.id.edit20);
        final EditText billet10 = (EditText) v.findViewById(R.id.edit10);
        final EditText billet5 = (EditText) v.findViewById(R.id.edit5);
        final EditText billet2 = (EditText) v.findViewById(R.id.edit2);
        final EditText billet1 = (EditText) v.findViewById(R.id.edit1);
        final EditText billet025 = (EditText) v.findViewById(R.id.edit025);
        final EditText billet010 = (EditText) v.findViewById(R.id.edit010);
        final EditText billet005 = (EditText) v.findViewById(R.id.edit005);
        final EditText billet001 = (EditText) v.findViewById(R.id.edit001);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IChange change = new Change();
                int nb100 = 0;
                if (!billet100.getText().toString().equals(""))
                    nb100 = Integer.parseInt(billet100.getText().toString());

                int nb50 = 0;
                if (!billet50.getText().toString().equals(""))
                    nb50 = Integer.parseInt(billet50.getText().toString());

                int nb20 = 0;
                if (!billet20.getText().toString().equals(""))
                    nb20 = Integer.parseInt(billet20.getText().toString());

                int nb10 = 0;
                if (!billet10.getText().toString().equals(""))
                    nb10 = Integer.parseInt(billet10.getText().toString());

                int nb5 = 0;
                if (!billet5.getText().toString().equals(""))
                    nb5 = Integer.parseInt(billet5.getText().toString());

                int nb2 = 0;
                if (!billet2.getText().toString().equals(""))
                    nb2 = Integer.parseInt(billet2.getText().toString());

                int nb1 = 0;
                if (!billet1.getText().toString().equals(""))
                    nb1 = Integer.parseInt(billet1.getText().toString());

                int nb025 = 0;
                if (!billet025.getText().toString().equals(""))
                    nb025 = Integer.parseInt(billet025.getText().toString());

                int nb010 = 0;
                if (!billet010.getText().toString().equals(""))
                    nb010 = Integer.parseInt(billet010.getText().toString());

                int nb005 = 0;
                if (!billet005.getText().toString().equals(""))
                    nb005 = Integer.parseInt(billet005.getText().toString());

                int nb001 = 0;
                if (!billet001.getText().toString().equals(""))
                    nb001 = Integer.parseInt(billet001.getText().toString());

                try {
                    change.addItems(Money.bill100, nb100);
                    change.addItems(Money.bill50, nb50);
                    change.addItems(Money.bill20, nb20);
                    change.addItems(Money.bill10, nb10);
                    change.addItems(Money.bill5, nb5);
                    change.addItems(Money.coin2, nb2);
                    change.addItems(Money.coin1, nb1);
                    change.addItems(Money.coin25s, nb025);
                    change.addItems(Money.coin10s, nb010);
                    change.addItems(Money.coin5s, nb005);
                    change.addItems(Money.coin1s, nb001);

                } catch (CashException e) {

                }

                double value = change.totalValue();
                totalChange.setText("$ " + value);
                m_change = value;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i("Dialog", "TakeChange");

                //
                if (m_change <= m_total) {
                    Toast.makeText(getActivity().getApplicationContext(), "Pas assez d'argent!", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getActivity().getApplicationContext(), "Facture de $" + m_total, Toast.LENGTH_SHORT).show();

                    try {
                        GiveChangeDialogFragment newFragment = new GiveChangeDialogFragment(m_total, m_change);
                        newFragment.show(getFragmentManager(), "dialog");
                    } catch (InvalidParameterException e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Certain paramètres sont invalides", Toast.LENGTH_SHORT).show();
                    }

                    TakeChangeDialogFragment.this.dismiss();
                }
            }
        });

        return v;
    }

    public TakeChangeDialogFragment(double pTotal) {
        m_total = Math.round(pTotal * 20.0) / 20.0;
    }
}
