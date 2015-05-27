package com.marc.arseneault.tp4.gui;

import com.marc.arseneault.tp4.model.Product;

/**
 * Created by 1333297 on 2015-04-21.
 */

public class ProductItem {

    public Product getProduct() {
        return m_Product;
    }

    public void setProduct(Product m_Product) {
        this.m_Product = m_Product;
    }

    public int getQuantity() {
        return m_Quantity;
    }

    public void setQuantity(int m_Quantity) {
        this.m_Quantity = m_Quantity;
    }

    Product m_Product;
    int m_Quantity;

    @Override
    public String toString() {
        return "ProductItem{" +
                "m_Product=" + m_Product.getName() +
                ", m_Quantity=" + m_Quantity +
                '}';
    }

    public ProductItem (int pQuantity, Product pProduct) {
        this.m_Product = pProduct;
        this.m_Quantity = pQuantity;
    }
}