package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.ProductRepository;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.models.dto.ProductDto;
import edu.miu.ebuy.models.dto.ProductSearchItem;
import edu.miu.ebuy.models.lookup.ProductStatus;
import edu.miu.ebuy.security.Context;
import edu.miu.ebuy.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductDto> getAll() {

        //return (List<Product>) productRepository.findByIsDeleted(false);

        List<Product> productList=  productRepository.findByIsDeleted(false);
        List<ProductDto> lst = new ArrayList<>();
        for (Product product : productList)
        {
            ProductDto productDto =ProductDto.read(product);

            lst.add(productDto);
        }
        return  lst;
    }

    @Override
    public List<ProductDto> getActive() {
        //return (List<Product>) productRepository.findByIsDeletedAndIsServiceAndIsPublishedAndProductStatus_Id(false,false,true,2);
        List<Product> productList=  productRepository.findByIsDeletedAndIsServiceAndIsPublishedAndProductStatus_Id(false,false,true,2);
        List<ProductDto> lst = new ArrayList<>();
        for (Product product : productList)
        {
            ProductDto productDto =ProductDto.read(product);

            lst.add(productDto);
        }
        return  lst;
    }


    @Override
    public List<ProductDto> getAdminList() {
        //return (List<Product>) productRepository.findByIsDeletedAndIsServiceAndIsPublishedAndProductStatus_Id(false,false,true,2);
        List<Product> productList=  productRepository.findByIsDeletedAndIsServiceAndProductStatus_Id(false,false,2);
        List<ProductDto> lst = new ArrayList<>();
        for (Product product : productList)
        {
            ProductDto productDto =ProductDto.read(product);

            lst.add(productDto);
        }
        return  lst;
    }

    @Override
    public List<ProductDto> getAdminList(int userId) {
        //return (List<Product>) productRepository.findByIsDeletedAndIsServiceAndIsPublishedAndProductStatus_Id(false,false,true,2);
        List<Product> productList=  productRepository.findByIsDeletedAndIsServiceAndUser_IdAndProductStatus_Id(false,false,userId,2);
        List<ProductDto> lst = new ArrayList<>();
        for (Product product : productList)
        {
            ProductDto productDto =ProductDto.read(product);

            lst.add(productDto);
        }
        return  lst;
    }


    @Override
    public ProductDto get(int productId) {
       //return  productRepository.getOne(productId);

        ProductDto productDto =ProductDto.read(productRepository.getOne(productId));
        return  productDto;
    }


    @Override
    public Product create(Product product, String imageUrl) {

       // product.setDeleted(false);
        product.setProductStatus(new ProductStatus(1,""));
        product.setUser(new User(Context.getUserId()));
        product.setImageUrl(imageUrl);
        product.setPublished(true);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product, String imageUrl) {
        product.setProductStatus(new ProductStatus(product.getProductStatus().getId(),""));
        product.setUser(new User(Context.getUserId()));
        product.setImageUrl(imageUrl);


        return productRepository.save(product);
    }



    @Override
    public Product update(Product product) {
        product.setProductStatus(new ProductStatus(product.getProductStatus().getId(),""));
        product.setUser(new User(Context.getUserId()));
        product.setImageUrl(product.getImageUrl());
        return productRepository.save(product);
    }





    @Override
    public void  delete(int productId) {

        productRepository.updateItemDelete(productId);
    }

    @Override
    public void approveProduct(int productId, int statusId) {
        productRepository.updateStatus(productId,statusId);
    }

    @Override
    public void published(int productId, boolean isPublished) {

        productRepository.updateItemPublished(productId,isPublished);
    }


    @Override
    public Product getServiceProduct() {
        return productRepository.getProductByIsService(true);
    }

    @Override
    public List<ProductDto> getProductByPrice(double fromPrice, double toPrice) {
        //return productRepository.getProductByPrice(fromPrice,toPrice);

        List<Product> productList= productRepository.getProductByPrice(fromPrice,toPrice);
        List<ProductDto> lst = new ArrayList<>();
        for (Product product : productList)
        {
            ProductDto productDto =ProductDto.read(product);

            lst.add(productDto);
        }
        return  lst;
    }

    @Override
    public List<ProductDto> getPendingProduct() {
        List<Product> productList=  productRepository.findByProductStatus_Id(1);
        List<ProductDto> lst = new ArrayList<>();
        for (Product product : productList)
        {
            ProductDto productDto =ProductDto.read(product);

            lst.add(productDto);
        }
        return  lst;
    }

    @Override
    public List<ProductDto> search(ProductSearchItem searchItem) {

        searchItem.fitParameters();
        List<Product> productList= productRepository.search(searchItem.getProductName(),searchItem.getVendorName(),searchItem.getPriceFrom(),searchItem.getPriceTo());

        List<ProductDto> lst = new ArrayList<>();
        for (Product product : productList)
        {
            ProductDto productDto =ProductDto.read(product);
            lst.add(productDto);
        }
        return  lst;
    }

    @Override
    public List<Product> getAllProducts(List<Integer> productIds) {
        return productRepository.findByIdIn(productIds);
    }

}
