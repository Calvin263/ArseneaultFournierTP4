package com.marc.arseneault.tp4.gui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.marc.arseneault.tp4.R;
import com.marc.arseneault.tp4.TP4Activity;
import com.marc.arseneault.tp4.model.monies.CashRegister;
import com.marc.arseneault.tp4.model.monies.Change;
import com.marc.arseneault.tp4.model.monies.IChange;
import com.marc.arseneault.tp4.model.monies.Money;
import com.marc.arseneault.tp4.model.monies.MoneyMachine;
import com.marc.arseneault.tp4.model.monies.exceptions.CashException;

/**
 * Created by 1333297 on 2015-05-27.
 */
public class GiveChangeDialogFragment extends DialogFragment {

    public double m_total;
    public double m_change;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // mise en place de l'interface a partir du XML
        final View v = inflater.inflate(R.layout.give_change, container, false);

        final TextView billet100 = (TextView) v.findViewById(R.id.text100);
        final TextView billet50 = (TextView) v.findViewById(R.id.text50);
        final TextView billet20 = (TextView) v.findViewById(R.id.text20);
        final TextView billet10 = (TextView) v.findViewById(R.id.text10);
        final TextView billet5 = (TextView) v.findViewById(R.id.text5);
        final TextView billet2 = (TextView) v.findViewById(R.id.text2);
        final TextView billet1 = (TextView) v.findViewById(R.id.text1);
        final TextView billet025 = (TextView) v.findViewById(R.id.text025);
        final TextView billet010 = (TextView) v.findViewById(R.id.text010);
        final TextView billet005 = (TextView) v.findViewById(R.id.text005);
        final TextView billet001 = (TextView) v.findViewById(R.id.text001);

        // recuperation de chaque element d'interface
        final Button button = (Button) v.findViewById(R.id.button);
        final TextView textView = (TextView) v.findViewById(R.id.textView);
        final TextView textTotal = (TextView) v.findViewById(R.id.textTotal);

        MoneyMachine machine = new MoneyMachine();
        CashRegister register = new CashRegister();
        IChange change = new Change();
        try {
            change =  machine.computeChange(m_change - m_total, register);
        } catch (CashException e) {

        }
        //bills
        int nb100 = change.numberOfItemsFor(Money.bill100);
        if (nb100 > 0)
            billet100.setText( Money.bill100.prettyPrint + ": " + nb100);
        else
            billet100.setText( Money.bill100.prettyPrint + ": " + 0);

        int nb50 = change.numberOfItemsFor(Money.bill50);
        if (nb50 > 0)
            billet50.setText( Money.bill50.prettyPrint + ": " + nb50);
        else
            billet100.setText( Money.bill100.prettyPrint + ": " + 0);

        int nb20 = change.numberOfItemsFor(Money.bill20);
        if (nb20 > 0)
            billet20.setText( Money.bill20.prettyPrint + ": " + nb20);
        else
            billet20.setText( Money.bill20.prettyPrint + ": " + 0);

        int nb10 = change.numberOfItemsFor(Money.bill10);
        if (nb10 > 0)
            billet10.setText( Money.bill10.prettyPrint + ": " + nb10);
        else
            billet10.setText( Money.bill10.prettyPrint + ": " + 0);

        int nb5 = change.numberOfItemsFor(Money.bill5);
        if (nb5 > 0)
            billet5.setText( Money.bill5.prettyPrint + ": " + nb5);
        else
            billet5.setText( Money.bill5.prettyPrint + ": " + 0);

        //coins
        int nb2 = change.numberOfItemsFor(Money.coin2);
        if (nb2 > 0)
            billet2.setText( Money.coin2.prettyPrint + ": " + nb2);
        else
            billet2.setText( Money.coin2.prettyPrint + ": " + 0);

        int nb1 = change.numberOfItemsFor(Money.coin1);
        if (nb1 > 0)
            billet1.setText( Money.coin1.prettyPrint + ": " + nb1);
        else
            billet1.setText( Money.coin1.prettyPrint + ": " + 0);

        int nb025 = change.numberOfItemsFor(Money.coin25s);
        if (nb025 > 0)
            billet025.setText( Money.coin25s.prettyPrint + ": " + nb025);
        else
            billet025.setText( Money.coin25s.prettyPrint + ": " + 0);

        int nb010 = change.numberOfItemsFor(Money.coin10s);
        if (nb010 > 0)
            billet010.setText( Money.coin10s.prettyPrint + ": " + nb010);
        else
            billet010.setText( Money.coin10s.prettyPrint + ": " + 0);

        int nb005 = change.numberOfItemsFor(Money.coin5s);
        if (nb005 > 0)
            billet005.setText( Money.coin5s.prettyPrint + ": " + nb005);
        else
            billet005.setText( Money.coin5s.prettyPrint + ": " + 0);

        int nb001 = change.numberOfItemsFor(Money.coin1s);
        if (nb001 > 0)
            billet001.setText( Money.coin1s.prettyPrint + ": " + nb001);
        else
            billet001.setText( Money.coin1s.prettyPrint + ": " + 0);

        double totalValue = change.totalValue();
        textTotal.setText("$" + totalValue + "");

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i("Dialog", "Give Change");

                //
                for (ProductItem p :TP4Activity.items)
                {
                    if (p.getProduct().getTfo())
                        Log.i("Dialog", "Facture: " + p.getQuantity() + " " + p.getProduct().getName() + ": $" + (p.getQuantity() - (p.getQuantity() / 2)) * p.getProduct().getPrice() + " (est en 2 pour 1)");
                    else
                        Log.i("Dialog", "Facture: " + p.getQuantity() + " " + p.getProduct().getName() + ": $" + p.getQuantity() * p.getProduct().getPrice());
                }
                Log.i("Facture", "Facture: Sous-Total: $" + Math.round((m_total * 0.8)* 20.0) / 20.0);
                Log.i("Facture", "Facture: Total: $" + m_total);
                GiveChangeDialogFragment.this.dismiss();
            }
        });

        return v;
    }
    public GiveChangeDialogFragment(double pTotal, double pChange) {
        m_total = Math.round(pTotal * 20.0) / 20.0;
        m_change = Math.round(pChange * 20.0) / 20.0;
    }
}