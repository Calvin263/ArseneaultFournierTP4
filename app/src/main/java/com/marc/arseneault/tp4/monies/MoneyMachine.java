package com.marc.arseneault.tp4.monies;

import com.marc.arseneault.tp4.monies.exceptions.CashException;
import com.marc.arseneault.tp4.monies.exceptions.NegativeMoneyException;
import com.marc.arseneault.tp4.monies.exceptions.NotEnoughMoneyException;

/**
 * Created by 1333297 on 2015-05-27.
 */
public class MoneyMachine implements IMoneyMachine{
    @Override
    public IChange computeChange(double amount, ICashRegister register) throws CashException {
        IChange result = new Change();
        amount = Math.round(amount * 20.0) / 20.0;
        int amountCent = (int) ((amount + 0.001) * 100.00);

        try {

            if (verifs(amount, register))
            {

                double remaining = amountCent;
                while (remaining > 1) {
                    for (int i = 0; i < Money.values().length; i++)
                    {
                        Money m =  Money.values()[i];
                        double coin = m.centValue;
                        while (coin <= remaining)
                        {
                            if(result.numberOfItemsFor(m) < register.numberOfItemsFor(m))
                            {
                                remaining -= coin;
                                result.addItems(m, 1);
                                if (remaining == 0)
                                {
                                    register.useItems(result);
                                    return result;
                                }
                            }
                            else
                                break;
                        }
                    }
                }
                register.useItems(result);
                return result;
            }

        } catch (NegativeMoneyException e) {
            System.out.println("!=== Erreur: Amount is negative.");
            throw new CashException();
        } catch (NotEnoughMoneyException e) {
            System.out.println("!=== Erreur: Pas assez d'argent disponible dans la caisse.");
            throw new CashException();
        }

        return result;
    }

    private boolean verifs(double amount, ICashRegister register) throws NegativeMoneyException, NotEnoughMoneyException {
        if (amount < 0)
        {
            throw new NegativeMoneyException();
        }
        if (amount > register.totalValue())
        {
            throw new NotEnoughMoneyException();
        }
        return true;
    }
}
