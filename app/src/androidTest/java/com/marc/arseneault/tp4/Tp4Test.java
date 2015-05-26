package com.marc.arseneault.tp4;

import android.test.AndroidTestCase;

import junit.framework.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Product;
import model.repository.ProductRepository;


/**
 * v1.0
 */
public class Tp4Test extends AndroidTestCase{

    ProductRepository repository;
    List<ProductItem> items;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repository = new ProductRepository(getContext());
        repository.deleteAll();

        items = new ArrayList<ProductItem>();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repository.deleteAll();
        repository = null;

        items.clear();
    }

    public void testTfo2Items() {
        Product product = new Product("Avocat", "123456", 12.54, true);
        ProductItem p = new ProductItem(1, product);
        items.add(p);

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(2, product));
        Assert.assertEquals("Test deux pour un 2 items failed", 12.54, activity.getTotalPrice(items));
    }

    public void testTfo1Item() {
        Product product = new Product("Avocat", "123456", 12.54, true);

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(1, product));
        Assert.assertEquals("Test deux pour un 1 items failed", 12.54, activity.getTotalPrice(items));
    }

    public void testTfo3Items() {
        Product product = new Product("Avocat", "123456", 12.54, true);

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(3, product));
        Assert.assertEquals("Test deux pour un 2 items failed", 12.54 * 2, activity.getTotalPrice(items));
    }

    public void testRabais10pourcent() {
        Product product = new Product("Avocat", "123456", 12.54, false);
        //TODO Add percent to Product when implemented

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(1, product));
        Assert.assertEquals("Test deux pour un 2 items failed", 12.54 * 0.90, activity.getTotalPrice(items));
    }

    public void testRabais100xRandomPourcent() {

        Random rand = new Random(123456);

        for (int i = 0; i < 100; i++)
        {
            int pourcent = rand.nextInt(100);
            double truePourcent = 1.0 - (pourcent / 100.0);

            Product product = new Product("Avocat", "123456", 12.54, false);
            //TODO Add truePourcent to Product when implemented

            TP4Activity activity = new TP4Activity();
            activity.adapter.add(new ProductItem(1, product));
            Assert.assertEquals("Test deux pour un 2 items failed", 12.54 * truePourcent, activity.getTotalPrice(items));
        }
    }

}