package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Card;
import edu.miu.ebuy.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

    public List< Card> findByCardNumberAndExpireDateAndCcvAndTypeId(String cardNo, String expireDate, int ccv, int typeId);
}


