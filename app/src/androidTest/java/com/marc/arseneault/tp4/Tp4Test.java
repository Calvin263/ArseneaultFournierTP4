package com.marc.arseneault.tp4;

import android.test.AndroidTestCase;

import junit.framework.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Product;
import service.RepositoryService;
import service.RepositoryServiceInterface;


/**
 * v1.0
 */
public class Tp4Test extends AndroidTestCase{

    RepositoryServiceInterface repoServ;
    List<ProductItem> items;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repoServ = new RepositoryService(getContext());
        repoServ.WipeProducts();
        repoServ.WipeTransactions();

        items = new ArrayList<>();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repoServ.WipeProducts();
        repoServ.WipeTransactions();
        repoServ = null;

        items.clear();
    }

    public void testTfo2Items() {
        Product product = new Product("Avocat", "123456", 12.54, true);
        ProductItem p = new ProductItem(1, product);
        items.add(p);

        Assert.assertEquals("Test deux pour un 2 items failed", 12.54, repoServ.GetTotalPrice(items, 10000));
    }

    public void testTfo1Item() {
        Product product = new Product("Avocat", "123456", 12.54, true);

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(1, product));
        Assert.assertEquals("Test deux pour un 1 items failed", 12.54, repoServ.GetTotalPrice(items, 100000));
    }

    public void testTfo3Items() {
        Product product = new Product("Avocat", "123456", 12.54, true);

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(3, product));
        Assert.assertEquals("Test deux pour un 2 items failed", 12.54 * 2, repoServ.GetTotalPrice(items, 100000));
    }

    public void testRabais10pourcent() {
        Product product = new Product("Avocat", "123456", 12.54, false);
        //TODO Add percent to Product when implemented

        TP4Activity activity = new TP4Activity();
        activity.adapter.add(new ProductItem(1, product));
        Assert.assertEquals("Test deux pour un 2 items failed", 12.54 * 0.90, repoServ.GetTotalPrice(items, 100000));
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
            Assert.assertEquals("Test deux pour un 2 items failed", 12.54 * truePourcent, repoServ.GetTotalPrice(items, 100000));
        }
    }

}