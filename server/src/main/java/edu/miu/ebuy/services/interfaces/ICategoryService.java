package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.models.Category;

import java.util.List;

public interface ICategoryService {

    Category get(int productId);
    List<Category> getAll();
    List<Category> getActive();
    Category create(Category category);
    Category update(Category category);
    void  delete(int productId);

}
