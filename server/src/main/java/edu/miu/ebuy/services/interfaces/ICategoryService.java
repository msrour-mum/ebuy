package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Category create(Category category);
    Category update(int CategoryId, Category category) throws ApplicationException;
    ResponseEntity<Void> delete(int id) throws ApplicationException;
    Category get(int id) throws ApplicationException;

}
