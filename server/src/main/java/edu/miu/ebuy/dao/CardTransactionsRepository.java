package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.CardTransactions;
import edu.miu.ebuy.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTransactionsRepository extends JpaRepository<CardTransactions, Long> {
}
