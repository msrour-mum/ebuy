package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
}
