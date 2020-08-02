package edu.miu.ebuy.services.interfaces;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Product ;

import java.util.List;

public interface IProductService {
    Product create(Product product);
    Product update(int productId, Product product)throws ApplicationException;
    void delete(int productId);
    List<Product> getProductByPrice(double fromPrice,double toPrice);
    List<Product> getAll();
    Product get(int productId);
    Product approveProduct(Product product);
    Product rejectProduct(Product product);
    Product getServiceProduct();
}
