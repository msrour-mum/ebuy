package edu.miu.ebuy.controllers;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@BaseResponse
@RequestMapping("/api/Categories")
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;

    @PostMapping
    public Category add(@RequestBody Category category) throws ApplicationException {
        try {
            return iCategoryService.create(category);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationException("Category already exists!", Errors.NOT_UNIQUE_USER_NAME_ERROR);
        }
    }

    @PutMapping("/{categoryId}")
    public Category update(@RequestBody Category category, @PathVariable int categoryId) {
        try {
            return iCategoryService.update(categoryId, category);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping()
    public List<Category> getAll() {
        try {
            return iCategoryService.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/{categoryId}")
    public Category get(@PathVariable int categoryId) {
        try {
            return iCategoryService.get(categoryId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @DeleteMapping("/{categoryId}")
    public String delete(@PathVariable int categoryId) {
        try {
            iCategoryService.delete(categoryId);
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }
}
