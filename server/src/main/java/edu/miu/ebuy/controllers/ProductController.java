package edu.miu.ebuy.controllers;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.services.interfaces.ICategoryService;
import edu.miu.ebuy.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@BaseResponse
@RequestMapping(value = "/api/products")
public class ProductController {
    @Autowired
    IProductService productService;

    @PostMapping
    public Product add(@RequestBody Product product) throws ApplicationException {
        return productService.create(product);
    }

    @PutMapping("/{productId}")
    public Product update(@RequestBody Product product, @PathVariable int productId) throws ApplicationException {
       return productService.update(productId,product);
    }

    @GetMapping()
    public List<Product> getAll() {

            return productService.getAll();

    }

    @GetMapping("/{productId}")
    public Product get(@PathVariable int productId) {

            return productService.get(productId);

    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable int productId) {
        productService.delete(productId);
    }
}
