package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.common.enums.ProductStatusEnum;
import edu.miu.ebuy.common.events.publishers.ProductApprovedEvent;
import edu.miu.ebuy.common.events.publishers.ProductRejectedEvent;
import edu.miu.ebuy.common.http.ResponseResult;
import edu.miu.ebuy.common.http.ResponseStatus;
import edu.miu.ebuy.dao.ProductRepository;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.Promotion;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.models.dto.ProductDto;
import edu.miu.ebuy.models.dto.ProductSearchItem;
import edu.miu.ebuy.models.lookup.ProductStatus;
import edu.miu.ebuy.security.Context;
import edu.miu.ebuy.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Name;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;


    @Override
    public List<Product> getAll() {

        return (List<Product>) productRepository.findByIsDeleted(false);

//        List<Product> productList=  productRepository.findByIsDeleted(false);
//        List<ProductDto> lst = new ArrayList<>();
//        for (Product product : productList)
//        {
//            ProductDto productDto =ProductDto.read(product);
//
//            lst.add(productDto);
//        }
//        return  lst;
    }

    @Override
    public List<Product> getActive() {
        return (List<Product>) productRepository.findByIsDeletedAndIsServiceAndIsPublishedAndProductStatus_Id(false,false,true,2);
//        List<Product> productList=  productRepository.findByIsDeletedAndIsServiceAndIsPublishedAndProductStatus_Id(false,false,true,2);
//        List<ProductDto> lst = new ArrayList<>();
//        for (Product product : productList)
//        {
//            ProductDto productDto =ProductDto.read(product);
//
//            lst.add(productDto);
//        }
//        return  lst;
    }


    @Override
    public List<Product> getAdminList() {
        return (List<Product>) productRepository.findByIsDeletedAndIsServiceAndProductStatus_Id(false,false,2);
//        List<Product> productList=  productRepository.findByIsDeletedAndIsServiceAndProductStatus_Id(false,false,2);
//        List<ProductDto> lst = new ArrayList<>();
//        for (Product product : productList)
//        {
//            ProductDto productDto =ProductDto.read(product);
//
//            lst.add(productDto);
//        }
//        return  lst;
    }

    @Override
    public List<Product> getAdminList(int userId) {
        return (List<Product>) productRepository.findByIsDeletedAndIsServiceAndProductStatus_IdAndUser_Id(false,false,2,userId);
//        List<Product> productList=  productRepository.findByIsDeletedAndIsServiceAndUser_IdAndProductStatus_Id(false,false,userId,2);
//        List<ProductDto> lst = new ArrayList<>();
//        for (Product product : productList)
//        {
//            ProductDto productDto =ProductDto.read(product);
//
//            lst.add(productDto);
//        }
//        return  lst;
    }


    @Override
    public Product get(int productId) {
       return  productRepository.getOne(productId);

//        ProductDto productDto =ProductDto.read(productRepository.getOne(productId));
//        return  productDto;
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

        product.getPromotions().add(new Promotion("test", 20,
                LocalDate.now(), LocalDate.now().plusMonths(1), product));


        return productRepository.save(product);
    }



    @Override
    public Product update(Product product) {
        product.setProductStatus(new ProductStatus(product.getProductStatus().getId(),""));
        product.setUser(new User(Context.getUserId()));
        product.setImageUrl(product.getImageUrl());

        product.getPromotions().add(new Promotion("test", 20,
                LocalDate.now(), LocalDate.now().plusMonths(1), product));
        return productRepository.save(product);
    }





    @Override
    public void  delete(int productId) {

        productRepository.updateItemDelete(productId);
    }

    @Override
    public void approveProduct(int productId, int statusId) {

        productRepository.updateStatus(productId,statusId);

        Product product = productRepository.getOne(productId);
        User vendor = userService.get(product.getUser().getId());
        if (statusId == ProductStatusEnum.Approved.id) {
            eventPublisher.publishEvent(new ProductApprovedEvent(vendor, product));
        } else if (statusId == ProductStatusEnum.Rejected.id) {
            eventPublisher.publishEvent(new ProductRejectedEvent(vendor, product));
        }
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
    public List<Product> getProductByPrice(double fromPrice, double toPrice) {
        return productRepository.getProductByPrice(fromPrice,toPrice);

//        List<Product> productList= productRepository.getProductByPrice(fromPrice,toPrice);
//        List<ProductDto> lst = new ArrayList<>();
//        for (Product product : productList)
//        {
//            ProductDto productDto =ProductDto.read(product);
//
//            lst.add(productDto);
//        }
//        return  lst;
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
    public List<Product> search(ProductSearchItem searchItem) {

        searchItem.fitParameters();
        List<Product> productList= productRepository.search(searchItem.getProductName(),searchItem.getVendorName(),searchItem.getPriceFrom(),searchItem.getPriceTo());
        return  productList;
//        List<ProductDto> lst = new ArrayList<>();
//        for (Product product : productList)
//        {
//            ProductDto productDto =ProductDto.read(product);
//            lst.add(productDto);
//        }
//        return  lst;
    }

    @Override
    public List<Product> getAllProducts(List<Integer> productIds) {
        return productRepository.findByIdIn(productIds);
    }


    @Override
    public void ftp(int userId , String[] lines) {

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                String[] words = line.split(",");
                if(words.length < 3) return;
                Product product = new Product(
                        words[0],
                        words[4],
                        words[3],
                        new User(userId),
                        new Category(Integer.parseInt(words[5])),
                        Double.parseDouble(words[1]),
                        Double.parseDouble(words[2]),
                        new ProductStatus(1,""),
                        true,
                        false,
                        "/uploads/0/p.jpg"
                );
                productRepository.save(product);
            }



    }

    @Override
    public Product addPromotion(Promotion promotion, int productId) {

        Product product = productRepository.getOne(productId);
        product.getPromotions().add(new Promotion(promotion.getName(), promotion.getDiscount(),
               promotion.getFromDate(), promotion.getToDate(), product));
        return productRepository.save(product);
    }

    @Override
    public void updatePromotion(int productId, Promotion promotion) {

        Product product = productRepository.getOne(productId);

       Promotion promotionToEdit = product.getPromotions()
                .stream()
                .filter(p-> p.getId() == promotion.getId())
                .findFirst()
                .orElse(null);

        promotionToEdit.setDiscount(promotion.getDiscount());
        promotionToEdit.setFromDate(promotion.getFromDate());
        promotionToEdit.setToDate(promotion.getToDate());
        promotionToEdit.setName(promotion.getName());

        productRepository.save(product);


    }

    @Override
   public void deletePromotion(int productId, int promotionId) {
        Product product = productRepository.getOne(productId);

        Promotion promotionToDelete = product.getPromotions()
                .stream()
                .filter(p-> p.getId() == promotionId)
                .findFirst()
                .orElse(null);

        if(promotionToDelete != null) {
            product.getPromotions().remove(promotionToDelete);
            productRepository.save(product);
        }
    }

}
