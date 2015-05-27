package com.marc.arseneault.tp4.service;

import android.content.Context;

import com.marc.arseneault.tp4.gui.ProductItem;

import java.util.List;

import com.marc.arseneault.tp4.model.Product;
import com.marc.arseneault.tp4.model.Transaction;
import com.marc.arseneault.tp4.model.repository.ProductRepository;
import com.marc.arseneault.tp4.model.repository.TransactionRepository;

public class RepositoryService implements RepositoryServiceInterface {

    private ProductRepository productRepo;
    private TransactionRepository transactionRepo;

    public RepositoryService(Context c)
    {
        productRepo = new ProductRepository(c);
        transactionRepo = new TransactionRepository(c);
    }

    @Override
    public ProductRepository GetProductRepository() {
        return productRepo;
    }

    @Override
    public double GetTotalPrice(List<ProductItem> a, double b)
    {
        double result = 0.0;

        if (!a.isEmpty()) {
            for (ProductItem productItem : a) {
                if (productItem.getProduct().getTfo())
                    result += (productItem.getQuantity() - (productItem.getQuantity() / 2)) * productItem.getProduct().getPrice();
                else
                    result += productItem.getQuantity() * productItem.getProduct().getPrice();
            }
            if (result <= b) {
                result = (result * 1.15) * 1.15;
            }
        }

        return result;
    }

    @Override
    public void SaveTransaction(List<ProductItem> a){
        transactionRepo.save(new Transaction(a));
    }

    @Override
    public void SaveProduct(Product a) {
        productRepo.save(a);
    }

    @Override
    public List<Transaction> GetAllTransactions() {
        return transactionRepo.getAll();
    }

    @Override
    public List<Product> GetAllProducts() {
        return productRepo.getAll();
    }

    @Override
    public Product GetProductByUPC(String UPC) throws ProductNotFoundException, IllegalArgumentException {
        //****VALIDATION****//
        if(UPC.isEmpty() || UPC.length() > 13)
        {
            throw new IllegalArgumentException();
        }
        try
        {
            Integer.parseInt(UPC);
        }
        catch(NumberFormatException e)
        {
            //throw new IllegalArgumentException();
        }

        //****GET****//
        Product product = productRepo.getByUPC(UPC);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        else {
            return product;
        }
    }

    @Override
    public Product GetProductById(long id) throws ProductNotFoundException {
        return productRepo.getById(id);
    }

    @Override
    public void DeleteTransaction(Transaction a) {
        transactionRepo.deleteOne(a);
    }

    @Override
    public void DeleteProduct(Product a) {
        productRepo.deleteOne(a);
    }

    @Override
    public void WipeTransactions() {
        transactionRepo.deleteAll();
    }

    @Override
    public void WipeProducts() {
        transactionRepo.deleteAll();
    }
}
