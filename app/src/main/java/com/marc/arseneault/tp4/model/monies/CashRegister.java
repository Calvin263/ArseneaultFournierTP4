package com.marc.arseneault.tp4.model.monies;

import com.marc.arseneault.tp4.model.monies.exceptions.CashException;
import com.marc.arseneault.tp4.model.monies.exceptions.NotEnoughMoneyException;
import com.marc.arseneault.tp4.model.monies.exceptions.NotEnoughPlaceForMoneyException;

/**
 * Created by 1333297 on 2015-05-27.
 */
public class CashRegister implements ICashRegister {

    int[] items = new int[Money.values().length];
    int[] itemsMax = new int[Money.values().length];

    @Override
    public int numberOfItemsFor(Money m) {
        return items[m.ordinal()];
    }

    @Override
    public void useItems(Money m, int number) {
        if (number > numberOfItemsFor(m))
        {
            try {
                throw new NotEnoughMoneyException();
            } catch (NotEnoughMoneyException e) {
                System.out.println("!=== Erreur: Pas assez d'argent disponible dans la caisse.");
            }
        }

        items[m.ordinal()] -= number;
    }

    public void useItems(IChange pChange) {
        for (int x = 0; x < Money.values().length; x++)
        {
            useItems(Money.values()[x], pChange.numberOfItemsFor(Money.values()[x]));
        }
    }

    @Override
    public void addItems(Money m, int number) throws CashException {
        if (numberOfItemsFor(m) + number > itemsMax[m.ordinal()])
        {
            try {
                throw new NotEnoughPlaceForMoneyException();
            } catch (NotEnoughPlaceForMoneyException e) {
                System.out.println("!=== Erreur: Pas assez de place dans la caisse.");
                throw new CashException();
            }
        }
        else {
            items[m.ordinal()] += number;
        }
    }

    @Override
    public double totalValue() {
        int value = 0;
        for (int i = 0; i < items.length; i++)
        {
            int moneyAmount = items[i];
            int moneyValue = Money.values()[i].centValue;
            int number = 0;

            try {
                number = safeMultiply(moneyAmount, moneyValue);
            }
            catch (ArithmeticException e)
            {
                return Integer.MAX_VALUE;
            }

            try {
                value = safeAdd(value, number);
            }
            catch (ArithmeticException e)
            {
                return Integer.MAX_VALUE;
            }
        }
        return value/100.0;
    }

    //Prevent integer overflow when multiplying
    static final int safeMultiply(int left, int right) throws ArithmeticException {
        if (right > 0 ? left > Integer.MAX_VALUE/right
                || left < Integer.MIN_VALUE/right
                : (right < -1 ? left > Integer.MIN_VALUE/right
                || left < Integer.MAX_VALUE/right
                : right == -1
                && left == Integer.MIN_VALUE) ) {
            throw new ArithmeticException("Integer overflow");
        }
        return left * right;
    }

    //Prevent integer overflow when adding
    static final int safeAdd(int left, int right) throws ArithmeticException {
        if (right > 0 ? left > Integer.MAX_VALUE - right
                : left < Integer.MIN_VALUE - right) {
            throw new ArithmeticException("Integer overflow");
        }
        return left + right;
    }

    @Override
    public int totalNumberOfItems() {
        int count = 0;
        for (int i = 0; i < items.length; i++)
        {
            count += items[i];
        }
        return count;
    }

    @Override
    public int maxCapacityFor(Money m) {
        return itemsMax[m.ordinal()];
    }

    public void fill()
    {
        for (int i = 0; i < items.length; i++)
        {
            items[i] = itemsMax[i];
        }
    }

    //Constructors//

    public CashRegister () {
        for (int x = 0; x < Money.values().length; x++)
        {
            itemsMax[x] = Integer.MAX_VALUE;
        }
        fill();
    }

    public CashRegister (int[] pMaxCapacity) {
        if (pMaxCapacity.length == Money.values().length)
        {
            for (int x = 0; x < pMaxCapacity.length; x++)
            {
                itemsMax[x] = pMaxCapacity[x];
            }
            fill();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
