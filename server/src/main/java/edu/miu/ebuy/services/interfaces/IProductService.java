package edu.miu.ebuy.services.interfaces;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Product ;
import edu.miu.ebuy.models.dto.ProductDto;
import edu.miu.ebuy.models.dto.ProductSearchItem;

import java.util.List;

public interface IProductService {



    public List<ProductDto> getAll();
    public List<ProductDto> getActive();
    public ProductDto get(int productId);
    public Product create(Product product, String imageUrl);
    public Product update(Product product);
    public Product update(Product product, String imageUrl);
    public void  delete(int productId);
    public List<ProductDto> getPendingProduct();

    public void approveProduct(int productId, int statusId);
    public Product getServiceProduct();
    public List<ProductDto> getProductByPrice(double fromPrice, double toPrice);

    public List<ProductDto> search(ProductSearchItem searchItem);
}
