package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.Promotion;
import edu.miu.ebuy.models.dto.ProductDto;
import edu.miu.ebuy.models.dto.ProductSearchItem;

import java.util.List;

public interface IProductService {

    List<Product> getAll();
    List<Product> getActive();
    Product get(int productId);
    Product create(Product product, String imageUrl);
    Product update(Product product);
    Product update(Product product, String imageUrl);
    void  delete(int productId);
    List<ProductDto> getPendingProduct();
    List<Product> getAdminList();
    List<Product> getAdminList(int userId);
    void approveProduct(int productId, int statusId);
    void published(int productId, boolean isPublished);
    Product getServiceProduct();
    List<Product> getProductByPrice(double fromPrice, double toPrice);
    List<Product> search(ProductSearchItem searchItem);
    List<Product> getAllProducts(List<Integer> productIds);
    void ftp(int userId , String[] lines) ;
    Product addPromotion(Promotion promotion, int productId);
    void updatePromotion(int productId, Promotion promotion);
    void deletePromotion(int productId, int promotionId);
}
