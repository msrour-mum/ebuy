package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    List<Promotion> findByVendor_Id(int vendorId);
}
