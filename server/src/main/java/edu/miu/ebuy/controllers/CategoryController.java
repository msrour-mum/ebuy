package edu.miu.ebuy.controllers;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@BaseResponse
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;


    @GetMapping()
    public List<Category> getAll() {
        return iCategoryService.getAll();
    }

    @GetMapping("/active")
    public List<Category> getActive() {
        return iCategoryService.getActive();
    }

    @GetMapping("/{categoryId}")
    public Category get(@PathVariable int categoryId) {
        return iCategoryService.get(categoryId);
    }

    @PostMapping
    public Category add(@RequestBody Category category)  {
        return iCategoryService.create(category);
    }

    @PutMapping("/{categoryId}")
    public Category update(@RequestBody Category category) {
        return iCategoryService.update(category);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable int categoryId) {
        iCategoryService.delete(categoryId);
    }

}
