package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Order;
import edu.miu.ebuy.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
