package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoryService {

    public Category get(int productId);
    public List<Category> getAll();
    public List<Category> getActive();
    public Category create(Category category);
    public Category update(Category category);
    public void  delete(int productId);

}
