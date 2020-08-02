package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.CardRepository;
import edu.miu.ebuy.dao.PaymentRepository;
import edu.miu.ebuy.models.MerchantCard;
import edu.miu.ebuy.services.interfaces.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MerchantService implements IMerchantService {

    @Autowired
    CardRepository cardRepository;



    @Override
    public boolean validateCard(String cardNo, String expireDate, int ccv, int typeId) {
        List<MerchantCard> lst = cardRepository.findByCardNumberAndExpireDateAndCcvAndTypeId(cardNo, expireDate, ccv, typeId);
        if (lst==null || lst.size()==0)
            return false;
        return  true;
    }

    @Override
    public boolean validateCard(String cardNo, String expireDate, int ccv, int typeId, double amount) {
        List<MerchantCard> lst = cardRepository.findByCardNumberAndExpireDateAndCcvAndTypeIdAndBalanceGreaterThan(cardNo, expireDate, ccv, typeId, amount);
        if (lst==null || lst.size()==0)
            return false;
        return  true;
    }

    @Override
    public boolean pay(String cardNo, String expireDate, int ccv, int typeId, double amount) {

        //sub from balance
        //add transaction
        //add payment
        return true;
    }
}
