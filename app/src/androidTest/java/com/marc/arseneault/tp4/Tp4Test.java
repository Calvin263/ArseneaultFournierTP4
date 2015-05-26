package com.marc.arseneault.tp4;

import android.test.AndroidTestCase;

import junit.framework.Assert;


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

}
