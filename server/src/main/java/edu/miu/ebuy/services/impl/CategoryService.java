package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.CategoryRepository;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Category;
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
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(int categoryId, Category new_category) throws ApplicationException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ApplicationException("Category Not Found", 404));
        category.setName(new_category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public ResponseEntity<Void> delete(int id) throws ApplicationException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Category Not Found", 404));
        categoryRepository.delete(category);
        return  ResponseEntity.noContent().build();
    }

    @Override
    public Category get(int id) throws ApplicationException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Category Not Found", 404));
    }
}
