package edu.miu.ebuy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.common.http.ResponseResult;
import edu.miu.ebuy.common.http.ResponseStatus;
import edu.miu.ebuy.common.storage.IStorageService;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.dto.ProductDto;
import edu.miu.ebuy.models.dto.ProductSearchItem;
import edu.miu.ebuy.security.Context;
import edu.miu.ebuy.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/active")
    public List<Product> getActive() {
        return productService.getActive();
    }

    @GetMapping("/admin")
    public List<Product> getAdminList() {
        return productService.getAdminList();
    }
    @GetMapping("/admin/{vendorId}")
    public List<Product> getAdminList(@PathVariable int vendorId) {
        return productService.getAdminList(vendorId);
    }

    @GetMapping("/pending")
    public List<ProductDto> getPending() {
        return productService.getPendingProduct();
    }

    @GetMapping("/rejected")
    public List<ProductDto> getRejected() {
        //TODO: rejected list
        return null;
    }

    @PostMapping("/search")
    public List<Product> search(@RequestBody ProductSearchItem searchItem) {
        return productService.search(searchItem);
    }

    @GetMapping("/{productId}")
    public Product get(@PathVariable int productId) {
        return productService.get(productId);
    }

    @PostMapping
    public Product add(@RequestParam String productJson, @RequestParam(value ="file", required=false) MultipartFile file) throws ApplicationException, IOException {
        //return productService.create(product);
        Product product = new ObjectMapper().readValue(productJson, Product.class);
        return productService.create(product,
                storageService.uploadMultipartFile(file, Context.getUserIdAsString()));
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


    @PostMapping("/{vendorId}/ftp")
    //public ResponseResult ftpProduct(@PathVariable int vendorId, @RequestParam(value ="file", required=false) MultipartFile file) throws ApplicationException, IOException {
    public void ftpProduct(@PathVariable int vendorId, @RequestParam(value ="file", required=false) MultipartFile file) throws ApplicationException, IOException {

        //File csvFile = storageService.uploadCSVFile(file);
        if (file!=null) {

            File csvFile = storageService.uploadCSVFile(file);
            List<String> lines = new ArrayList<>();
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(csvFile));
                String line = reader.readLine();
                while (line != null) {
                   // System.out.println(line);
                    // read next line
                    line = reader.readLine();
                    lines.add(line);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] arr = new String[lines.size()];
            arr = lines.toArray(arr);

            productService.ftp(vendorId,arr);
        }


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

    @PutMapping("/{productId}/published/{isPublished}")
    public void published(@PathVariable int productId, @PathVariable boolean isPublished) {
        productService.published( productId, isPublished);
    }


    @GetMapping("/service")
    public Product get() {
        return productService.getServiceProduct();
    }
}
