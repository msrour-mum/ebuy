package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Order;
import edu.miu.ebuy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
