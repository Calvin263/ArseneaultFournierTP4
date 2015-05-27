package com.marc.arseneault.tp4.monies;

import com.marc.arseneault.tp4.monies.exceptions.CashException;

/**
 * Created by 1333297 on 2015-05-27.
 */
public interface IChange {

    public int numberOfItemsFor(Money m);

    public  void addItems(Money m, int number) throws CashException;

    public double totalValue();

    public int totalNumberOfItems();
}
