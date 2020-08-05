package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.CategoryRepository;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category get(int productId) {
        return  categoryRepository.getOne(productId);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findByIsDeleted(false);
    }

    @Override
    public List<Category> getActive() {
        return (List<Category>) categoryRepository.findByIsDeleted(false);
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void  delete(int productId) {

        categoryRepository.updateItemDelete(productId);
    }






}
