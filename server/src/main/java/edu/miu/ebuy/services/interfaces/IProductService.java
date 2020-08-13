package edu.miu.ebuy.services.interfaces;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Product ;
import edu.miu.ebuy.models.Promotion;
import edu.miu.ebuy.models.dto.ProductDto;
import edu.miu.ebuy.models.dto.ProductSearchItem;

import java.util.List;

public interface IProductService {



    public List<Product> getAll();
    public List<Product> getActive();
    public Product get(int productId);
    public Product create(Product product, String imageUrl);
    public Product update(Product product);
    public Product update(Product product, String imageUrl);
    public void  delete(int productId);
    public List<ProductDto> getPendingProduct();
    public List<Product> getAdminList();
    public List<Product> getAdminList(int userId);
    public void approveProduct(int productId, int statusId);
    public void published(int productId, boolean isPublished);
    public Product getServiceProduct();
    public List<Product> getProductByPrice(double fromPrice, double toPrice);

    public List<Product> search(ProductSearchItem searchItem);
    List<Product> getAllProducts(List<Integer> productIds);
    public void ftp(int userId , String[] lines) ;
    Product addPromotion(Promotion promotion, int productId);
    void updatePromotion(int productId, Promotion promotion);
    void deletePromotion(int productId, int promotionId);
}
