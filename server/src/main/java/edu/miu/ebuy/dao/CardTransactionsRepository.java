package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.MerchantCardTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTransactionsRepository extends JpaRepository<MerchantCardTransactions, Long> {
}
