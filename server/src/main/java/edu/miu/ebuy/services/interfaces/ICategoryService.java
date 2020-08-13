package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoryService {

    public List<Category> getAll();
    public Category create(Category category);
    public Category update(int CategoryId, Category category) throws ApplicationException;
    public ResponseEntity<Void> delete(int id) throws ApplicationException;
    public Category get(int id) throws ApplicationException;

}
