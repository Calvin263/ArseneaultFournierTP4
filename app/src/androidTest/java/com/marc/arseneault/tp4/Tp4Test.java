package com.marc.arseneault.tp4;

import android.test.AndroidTestCase;

import junit.framework.Assert;


import java.util.Random;

import model.Product;
import model.repository.ProductRepository;


/**
 * v1.0
 */
public class Tp4Test extends AndroidTestCase{

    ProductRepository repository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repository = new ProductRepository(getContext());
        repository.deleteAll();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repository.deleteAll();
        repository = null;
    }

    public void testTfo2Items() {
        Product product = new Product("Avocat", "123456", 12.54/*, true*/);//TODO Uncomment this when TFO is implemented

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(2, product));
        Assert.assertEquals("Test deux pour un 2 items failed", 12.54, activity.getTotalPrice());
    }

    public void testTfo1Item() {
        Product product = new Product("Avocat", "123456", 12.54/*, true*/);//TODO Uncomment this when TFO is implemented

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(1, product));
        Assert.assertEquals("Test deux pour un 1 items failed", 12.54, activity.getTotalPrice());
    }

    public void testTfo3Items() {
        Product product = new Product("Avocat", "123456", 12.54/*, true*/);//TODO Uncomment this when TFO is implemented

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(3, product));
        Assert.assertEquals("Test deux pour un 2 items failed", 12.54 * 2, activity.getTotalPrice());
    }

    public void testRabais10pourcent() {
        Product product = new Product("Avocat", "123456", 12.54/*, false*/);//TODO Uncomment this when TFO is implemented
        //TODO Add percent to Product when implemented

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(1, product));
        Assert.assertEquals("Test deux pour un 2 items failed", 12.54 * 0.90, activity.getTotalPrice());
    }

    public void testRabais100xRandomPourcent() {

        Random rand = new Random(123456);

        for (int i = 0; i < 100; i++)
        {
            int pourcent = rand.nextInt(100);
            double truePourcent = 1.0 - (pourcent / 100.0);

            Product product = new Product("Avocat", "123456", 12.54/*, false*/);//TODO Uncomment this when TFO is implemented
            //TODO Add truePourcent to Product when implemented

            TP4Activity activity = new TP4Activity();
            activity.adapter.add(new ProductItem(1, product));
            Assert.assertEquals("Test deux pour un 2 items failed", 12.54 * truePourcent, activity.getTotalPrice());
        }
    }

}
