package com.marc.arseneault.tp4;

import android.test.AndroidTestCase;

import junit.framework.Assert;


import java.util.ArrayList;
import java.util.List;

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

}