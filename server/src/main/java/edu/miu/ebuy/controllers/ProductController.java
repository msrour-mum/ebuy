package edu.miu.ebuy.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.common.storage.IStorageService;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.models.dto.ProductDto;
import edu.miu.ebuy.models.dto.ProductSearchItem;
import edu.miu.ebuy.security.Context;
import edu.miu.ebuy.services.interfaces.ICategoryService;
import edu.miu.ebuy.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@BaseResponse
@RequestMapping(value = "/api/products")
public class ProductController {
    @Autowired
    IProductService productService;
    @Autowired
    IStorageService storageService;

    @GetMapping()
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/active")
    public List<ProductDto> getActive() {
        return productService.getActive();
    }

    @GetMapping("/pending")
    public List<ProductDto> getPending() {
        return productService.getPendingProduct();
    }

    @PostMapping("/search")
    public List<ProductDto> search(@RequestBody ProductSearchItem searchItem) {
        return productService.search(searchItem);
    }

    @GetMapping("/{productId}")
    public ProductDto get(@PathVariable int productId) {
        return productService.get(productId);
    }

    @PostMapping
    public Product add(@RequestParam String productJson, @RequestParam(value ="file", required=false) MultipartFile file) throws ApplicationException, IOException {
        //return productService.create(product);
        Product product = new ObjectMapper().readValue(productJson, Product.class);
            Product product1 = productService.create(product,
                    storageService.uploadMultipartFile(file, Context.getUserIdAsString()));
      return product1;
    }

    @PutMapping("/{productId}")
    public Product update(@RequestParam String productJson, @RequestParam(value ="file", required=false) MultipartFile file) throws ApplicationException, IOException {
        Product product = new ObjectMapper().readValue(productJson, Product.class);
        if (file!=null) {

            Product product1 = productService.create(product,
                    storageService.uploadMultipartFile(file, Context.getUserIdAsString()));
            return product1;
        }
        else
            return  productService.update(product);
    }

//    @PutMapping("/{productId}")
//    public Product update(@RequestBody Product product) {
//       return productService.update(product);
//    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable int productId) {
        productService.delete(productId);
    }


    @PutMapping("/{productId}/approve/{statusId}")
    public void approveProduct(@PathVariable int productId, @PathVariable int statusId) {
         productService.approveProduct( productId, statusId);
    }


    @GetMapping("/service")
    public Product get() {
        return productService.getServiceProduct();
    }
}
