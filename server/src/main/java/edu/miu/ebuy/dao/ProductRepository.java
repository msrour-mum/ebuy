package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product  p WHERE p.price >= :fromPrice AND p.price <= :toPrice")
    List<Product> getProductByPrice(double fromPrice, double toPrice);
    Product getProductByIsService(boolean isService);
}
