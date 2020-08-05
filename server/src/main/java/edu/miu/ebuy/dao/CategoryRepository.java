package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByIsDeleted(boolean isDeleted);
    @Modifying
    @Query(value = "UPDATE Category SET isDeleted=1 WHERE id=?1",nativeQuery = true)
    public void updateItemDelete( @Param("id") Integer id);

}
