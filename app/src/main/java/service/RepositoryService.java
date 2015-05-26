package service;

import android.content.Context;

import com.marc.arseneault.tp4.ProductItem;

import java.util.List;

import model.repository.ProductRepository;
import model.repository.TransactionRepository;

public class RepositoryService {

    private ProductRepository productRepo;
    private TransactionRepository transactionRepo;

    public RepositoryService(Context c)
    {
        productRepo = new ProductRepository(c);
        transactionRepo = new TransactionRepository(c);
    }

    public double getTotalPrice(List<ProductItem> a)
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
}
