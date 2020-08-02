package edu.miu.ebuy.services.interfaces;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Product ;

import java.util.List;

public interface IProductService {
    public Product create(Product product);
    public Product update(int productId, Product product)throws ApplicationException;
    public void delete(int productId);
    public List<Product> getProductByPrice(double fromPrice,double toPrice);
    public List<Product> getAll();
    public Product get(int productId);
    public Product approveProduct(Product product);
    public Product rejectProduct(Product product);
}
