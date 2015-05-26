package com.marc.arseneault.tp4;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Product;
import model.Transaction;
import model.repository.CRUD;
import model.repository.ProductRepository;
import model.repository.TransactionRepository;


public class TP4Activity extends ActionBarActivity {

    JorisAdapter adapter;
    List<ProductItem> items = new ArrayList<ProductItem>();
    public static ProductRepository productRepository;
    public static TransactionRepository transactionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp3);
        productRepository = new ProductRepository(getApplicationContext());
        transactionRepository = new TransactionRepository(getApplicationContext());
        //Pour ne pas avoir des transactions de stockés après la fermeture de l'application
        //Normalement, on voudrait les garder
        transactionRepository.deleteAll();

        adapter = new JorisAdapter(TP4Activity.this, items);
        ListView list = (ListView)findViewById(R.id.product_List);
        list.setAdapter(adapter);

        updatePrice();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tp3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_makeProducts) {
            createProducts(productRepository);

            return true;
        }
        else if (id == R.id.action_deleteProducts) {
            productRepository.deleteAll();
        }
        else if (id == R.id.action_makeTransaction) {
            makeTransaction();
        }

        return super.onOptionsItemSelected(item);
    }

    //
    public void makeTransaction() {
        List<Product> products = productRepository.getAll();
        if (products.size() <= 1)
        {
            Toast.makeText(getApplicationContext(), "Pas assez d'éléments dans les produits (< 2)", Toast.LENGTH_SHORT).show();
            return;
        }
        Long[] tabProductID = new Long[products.size()];

        int index = 0;
        for (Product p : products) {
            tabProductID[index] = p.getId();
            index++;
        }

        Random random = new Random();
        List<Product> list = new ArrayList<Product>();
        for (int x = 0; x < (products.size() / 2); x++) {
            Product product;
            do {
                int r = random.nextInt(products.size());
                product = productRepository.getById(tabProductID[r]);
                list.add(product);
            } while (!list.contains(product));
        }

        for (Product product: list)
        {
            ProductItem productItem = new ProductItem(1, product);
            if (items.contains(productItem))
            {

            } else {
                for (int x = 0; x < random.nextInt(25); x++) {
                    if (items.contains(productItem)) {
                        ProductItem p = items.get(items.lastIndexOf(productItem));
                        p.setQuantity(p.getQuantity() +1);
                    } else {
                        items.add(productItem);
                    }
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    public static void createProducts(CRUD<Product> pCRUD) {
        pCRUD.deleteAll();

        //page 1
        pCRUD.save(new Product("Huile de Pacome", "7011043018498", 12.35));
        pCRUD.save(new Product("Langue de Porc", "6797413659379", 8.05));
        pCRUD.save(new Product("Tentacule de Pieuvre", "7651921095861", 22.25));
        pCRUD.save(new Product("Couille de Mouton", "5729286863776", 10.25));
        pCRUD.save(new Product("Patate", "6075631136316", 2.50));
        pCRUD.save(new Product("Seringue", "6116088359160", 2.25));
        pCRUD.save(new Product("Dictionnaire", "3298482123547", 35.95));
        pCRUD.save(new Product("Pied de Porc", "162992447343", 6.55));
        pCRUD.save(new Product("Jesus Juice", "5648382418072", 12.90));
        pCRUD.save(new Product("Crayon HB", "5942916222891", 1.50));

        //page 2
        pCRUD.save(new Product("Cadavre", "8038722591249", 10.95));
        pCRUD.save(new Product("Tableau Noir", "1589477250587", 25.50));
        pCRUD.save(new Product("Tableau Blanc", "7611473873003", 25.55));
        pCRUD.save(new Product("Souris", "5088408786417", 13.25));
        pCRUD.save(new Product("Ibuprofen", "3481608318716", 9.95));
        pCRUD.save(new Product("Drapeau Blanc", "4407073486209", 10.50));
        pCRUD.save(new Product("Dette Étudiante", "3593013272573", 50.50));
        pCRUD.save(new Product("Menottes", "4233891349934", 17.50));
        pCRUD.save(new Product("Matraque", "2657604046187", 23.35));
        pCRUD.save(new Product("Fond de Retraite", "7224672377618", 50.50));
    }

    public void clickAndShow(View v){
        createAndShowDialog();
    }

    public void createAndShowDialog () {
        try {
            ProductDialogFragment newFragment = new ProductDialogFragment();
            newFragment.show(getFragmentManager(), "dialog");
        } catch (InvalidParameterException e) {
            Toast.makeText(getBaseContext(), "Certain paramètres sont invalides", Toast.LENGTH_SHORT).show();
        }
    }

    public void scannerClick(View v) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            Product product = productRepository.getByUPC(scanResult.getContents());
            ProductItem productItem = new ProductItem(1, product);
            if (product == null) {
                Log.i("Main", "Scan result product was not found");
                Toast.makeText(getBaseContext(), "Produit non-existant", Toast.LENGTH_SHORT).show();
            }
            else {
                items.add(productItem);
                Log.i("Main", "Scan result from scanner is  " + scanResult.getContents());
                adapter.notifyDataSetChanged();
            }
        }
        updatePrice();
    }

    //

    private void updatePrice() {
        double totalPrice = getTotalPrice();
        TextView totalPriceText = (TextView)findViewById(R.id.totalPrice);
        totalPriceText.setText("$" + String.format("%10.2f", totalPrice));
        adapter.notifyDataSetChanged();
    }

    public double getTotalPrice(){
        double totalPrice =0.00;
        for (ProductItem productItem : items)
        {
            totalPrice += productItem.getQuantity() * productItem.getProduct().getPrice();
        }
        return totalPrice;
    }

    public void payButton(View v) {
        Transaction transaction = new Transaction(items);
        Log.i("Facture", transaction.toString());
        transactionRepository.save(transaction);

        Toast.makeText(getBaseContext(), "Facture de " + String.format("%10.2f", getTotalPrice()) + "$", Toast.LENGTH_SHORT).show();
    }

    public void plusClick(View v) {
        ProductItem productItem = (ProductItem) v.getTag();
        if (productItem!= null) {
            productItem.setQuantity(productItem.getQuantity() + 1);
            adapter.notifyDataSetChanged();
        }
        updatePrice();
    }

    public void minusClick(View v) {
        ProductItem productItem = (ProductItem) v.getTag();
        if (productItem!= null) {
            productItem.setQuantity(productItem.getQuantity() - 1);
            if (productItem.getQuantity() <= 0)
                items.remove(productItem);
            adapter.notifyDataSetChanged();
        }
        updatePrice();
    }
}