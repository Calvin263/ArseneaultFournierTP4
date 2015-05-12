package com.marc.arseneault.tp4;

/**
 * Created by 1333297 on 2015-04-21.
 */

public class Product {

    //member variables
    String m_Name;
    String m_UPC;
    double m_Price;
    Long m_id;

    //getters and setters

    public Long getId() { return m_id; }

    public void setId(Long id) { this.m_id = id;}

    public String getName() {
        return m_Name;
    }

    public void setName(String m_Name) {
        this.m_Name = m_Name;
    }

    public String getUPC() {
        return m_UPC;
    }

    public void setUPC(String m_UPC) {
        this.m_UPC = m_UPC;
    }

    public double getPrice() {
        return m_Price;
    }

    public void setPrice(double m_Price) {
        this.m_Price = m_Price;
    }

    //constructor
    public Product (String pName, String pUPC, double pPrice)
    {
        this.m_Name = pName;
        this.m_UPC = pUPC;
        this.m_Price = pPrice;
    }

    public Product ()
    {

    }
}