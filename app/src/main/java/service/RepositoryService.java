package service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.marc.arseneault.tp4.ProductItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Product;
import model.Transaction;
import model.repository.ProductRepository;
import model.repository.TransactionRepository;

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
    public double GetTotalPrice(List<ProductItem> a)
    {
        double result = 0.0;

        for (ProductItem productItem : a)
        {
            if (productItem.getProduct().getTfo())
                result += (productItem.getQuantity() - (productItem.getQuantity()/2)) * productItem.getProduct().getPrice();
            else
                result += productItem.getQuantity() * productItem.getProduct().getPrice();
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
    public Product GetProductByUPC(String UPC) throws ProductNotFoundException {
        Product product = productRepo.getByUPC(UPC);
        ProductItem productItem = new ProductItem(1, product);
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
