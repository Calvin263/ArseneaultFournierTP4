package com.marc.arseneault.tp4;

import java.util.List;

/**
 * Created by 1333297 on 2015-04-29.
 */
public class Transaction {

    List<ProductItem> m_ProductItems;
    Long m_id;

    public Long getId() { return m_id; }

    public void setId(Long m_id) { this.m_id = m_id; }

    public List<ProductItem> getList() {
        return m_ProductItems;
    }

    public void setList(List<ProductItem> pProductItems) {
        this.m_ProductItems = pProductItems;
    }


    public Transaction (List<ProductItem> list) {
        setList(list);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "m_ProductItems=" + m_ProductItems.toString() +
                '}';
    }
}