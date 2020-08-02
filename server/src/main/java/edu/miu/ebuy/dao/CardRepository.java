package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.MerchantCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<MerchantCard, Integer> {

    public List<MerchantCard> findByCardNumberAndExpireDateAndCcvAndTypeId(String cardNo, String expireDate, int ccv, int typeId);
}


