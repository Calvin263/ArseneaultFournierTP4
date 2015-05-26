package service;


import com.marc.arseneault.tp4.ProductDialogFragment;
import com.marc.arseneault.tp4.ProductItem;

import java.util.List;

import model.Product;
import model.Transaction;
import model.repository.ProductRepository;

public interface RepositoryServiceInterface {

    public ProductRepository GetProductRepository();

    public double GetTotalPrice(List<ProductItem> a);

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
