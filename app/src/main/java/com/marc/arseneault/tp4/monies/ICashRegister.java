package com.marc.arseneault.tp4.monies;

/**
 * Created by 1333297 on 2015-05-27.
 */
public interface ICashRegister extends IChange {

    public int maxCapacityFor(Money m );

    public void useItems(Money m , int number);

    public void useItems(IChange change);

}
