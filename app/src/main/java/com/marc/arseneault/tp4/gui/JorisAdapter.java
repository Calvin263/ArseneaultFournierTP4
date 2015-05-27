package com.marc.arseneault.tp4.gui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.marc.arseneault.tp4.R;

import java.util.List;

public class JorisAdapter extends ArrayAdapter<ProductItem> {

    public JorisAdapter(Context context, List<ProductItem> objects) {
        super(context, R.layout.list_item, objects);
    }

    /**
     * Methode qui produit la vue a inserer a cette position dans la liste
     * La meme pour chaque element.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // transformer le fichier XML en objets Java : gonfler
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View row = inflater.inflate(R.layout.list_item, parent, false);

        // le bouton plus se souvient qu'il correspond à cet item
        // seul moyen de distinguer tous les boutons +
        ProductItem item = getItem(position);
        row.findViewById(R.id.plusButton).setTag(item);
        row.findViewById(R.id.minusButton).setTag(item);

        // remplir le layout avec les bonnes valeurs
        TextView prodTV = (TextView) row.findViewById(R.id.item_produit);
        prodTV.setText(item.getProduct().getName());

        TextView qtyTV = (TextView) row.findViewById(R.id.item_quantite);
        qtyTV.setText(String.valueOf(item.getQuantity()));

        // attache l'item a la ligne au complet si on veut réagir à un clic partout
        row.setTag(item);
        return row;
    }

}

