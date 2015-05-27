package com.marc.arseneault.tp4.service;


import com.marc.arseneault.tp4.gui.ProductItem;

import java.util.List;

import com.marc.arseneault.tp4.model.Product;
import com.marc.arseneault.tp4.model.Transaction;
import com.marc.arseneault.tp4.model.repository.ProductRepository;

public interface RepositoryServiceInterface {

    public ProductRepository GetProductRepository();

    public double GetTotalPrice(List<ProductItem> a, double b);

    public void SaveTransaction(List<ProductItem> a);
    public void SaveProduct(Product a);

    public List<Transaction> GetAllTransactions();
    public List<Product> GetAllProducts();

    public Product GetProductByUPC(String UPC) throws ProductNotFoundException;
    public Product GetProductById(long id) throws ProductNotFoundException;

    public void DeleteTransaction(Transaction a);
    public void DeleteProduct(Product a);

    public void WipeTransactions();
    public void WipeProducts();
}
