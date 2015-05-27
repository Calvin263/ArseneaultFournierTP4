package com.marc.arseneault.tp4.monies;

/**
 * Created by 1333297 on 2015-05-27.
 */
public enum Money {
    bill100	(10000, "100$ bill"),
    bill50	(5000, 	"50$ bill"),
    bill20	(2000, 	"20$ bill"),
    bill10	(1000, 	"10$ bill"),
    bill5	(500, 	"5$ bill"),
    coin2	(200, 	"2$ coin"),
    coin1	(100, 	"1$ coin"),
    coin25s	(25, 	"0.25$ coin"),
    //	coin20s	(20, 	"0.20$ coin"),
    coin10s	(10, 	"0.10$ coin"),
    coin5s	(5, 	"0.5$ coin"),
    coin1s	(1, 	"0.1$ coin");

    public final int centValue;

    public final String prettyPrint;

    Money(int c, String pretty){
        this.centValue = c;
        this.prettyPrint = pretty;
    }

    public Double value(){
        return centValue /100.0;
    }

    public String pretty(){
        return this.prettyPrint;
    }
}