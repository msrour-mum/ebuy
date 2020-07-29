package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Card;
import edu.miu.ebuy.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
