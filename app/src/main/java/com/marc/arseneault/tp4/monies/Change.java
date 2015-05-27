package com.marc.arseneault.tp4.monies;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 1333297 on 2015-05-27.
 */
public class Change implements IChange {
    List<Money> items = new LinkedList<Money>();

    @Override
    public int numberOfItemsFor(Money m) {
        int result = 0;
        for (Money i : items)
            if (i.equals(m))
                result++;
        return result;
    }

    @Override
    public void addItems(Money m, int number) {
        for (int i = 0 ; i < number; i++){
            items.add(m);
        }
    }

    @Override
    public double totalValue() {
        int value = 0;
        for (int i = 0; i < totalNumberOfItems(); i++)
        {
            value += items.get(i).centValue;
        }
        return value/100.0;
    }

    @Override
    public int totalNumberOfItems() {
        return items.size();
    }
}
