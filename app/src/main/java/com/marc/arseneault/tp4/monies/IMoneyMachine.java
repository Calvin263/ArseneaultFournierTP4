package com.marc.arseneault.tp4.monies;

import com.marc.arseneault.tp4.monies.exceptions.CashException;

/**
 * Created by 1333297 on 2015-05-27.
 */
public interface IMoneyMachine
{
    public IChange computeChange(double amount, ICashRegister register) throws CashException;
}
