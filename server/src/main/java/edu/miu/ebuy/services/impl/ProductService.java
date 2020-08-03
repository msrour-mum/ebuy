package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.ProductRepository;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.lookup.ProductStatus;
import edu.miu.ebuy.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Name;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(int productId, Product new_product)throws ApplicationException {
       Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ApplicationException("Product Not Found", 404));
        product.setName(new_product.getName());
        return productRepository.save(product);
    }


    @Override
    public void  delete(int productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }
    @Override
    public Product get(int productId) {
        return  productRepository.getOne(productId);
    }

    @Override
    public Product approveProduct(Product product) {
        ProductStatus productStatus = product.getProductStatus();
        productStatus.setName("Approved");
        return productRepository.save(product);
    }

    @Override
    public Product rejectProduct(Product product) {
        ProductStatus productStatus = product.getProductStatus();
        productStatus.setName("Rejected");
        return productRepository.save(product);
    }

    @Override
    public Product getServiceProduct() {
        return productRepository.getProductByIsService(true);
    }

    @Override
    public List<Product> getProductByPrice(double fromPrice, double toPrice) {
        return productRepository.getProductByPrice(fromPrice,toPrice);
    }

}
