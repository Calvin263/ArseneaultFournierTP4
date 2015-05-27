package com.marc.arseneault.tp4;

import android.test.AndroidTestCase;

import junit.framework.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.marc.arseneault.tp4.gui.ProductItem;
import com.marc.arseneault.tp4.model.Product;
import com.marc.arseneault.tp4.service.RepositoryService;
import com.marc.arseneault.tp4.service.RepositoryServiceInterface;

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
        ProductItem p = new ProductItem(2, product);
        items.add(p);

        Assert.assertEquals("Test deux pour un 2 items failed", 12.54, repoServ.GetTotalPrice(items, 0));//0 pour que les taxes n'influencent pas les calculs
    }

    public void testTfo1Item() {
        Product product = new Product("Avocat", "123456", 12.54, true);

        items.add(new ProductItem(1, product));
        Assert.assertEquals("Test deux pour un 1 items failed", 12.54, repoServ.GetTotalPrice(items, 0));//0 pour que les taxes n'influencent pas les calculs
    }

    public void testTfo3Items() {
        Product product = new Product("Avocat", "123456", 12.54, true);

        items.add(new ProductItem(3, product));
        Assert.assertEquals("Test deux pour un 2 items failed", 12.54 * 2, repoServ.GetTotalPrice(items, 0));//0 pour que les taxes n'influencent pas les calculs
    }

    public void testTaxes()
    {
        Product product = new Product("Avocat", "123456", 12.54, false);

        items.add(new ProductItem(1, product));
        Assert.assertEquals("Test taxes failed", (12.54 * 1.15) * 1.15, repoServ.GetTotalPrice(items, 100000));//10000 pour quil ne puisse se faire atteindre, on veut tester les taxes
    }

    public void testTaxesOver100()
    {
        Product product = new Product("Avocat", "123456", 50, false);

        items.add(new ProductItem(3, product));
        Assert.assertEquals("Test taxes failed", 150.0, repoServ.GetTotalPrice(items, 100));
    }

    public void testTaxesOver100Random()
    {
        Product product = new Product("Avocat", "123456", 50, false);
        Random rand = new Random(123456);

        for(int i = 0; i < 100; i++)
        {
            int count = rand.nextInt(100);
            items.add(new ProductItem(count, product));
            if(count * 50 > 2000)
            {
                double amount = count * 50;
                Assert.assertEquals("testTaxesOver100Random failed when count = " + count, amount, repoServ.GetTotalPrice(items, 2000));
            }
            else
            {
                double amount = ((count * 50)*1.15)*1.15;
                Assert.assertEquals("testTaxesOver100Random failed when count = " + count, (amount), repoServ.GetTotalPrice(items, 2000));
            }
            items.clear();
        }
    }

}