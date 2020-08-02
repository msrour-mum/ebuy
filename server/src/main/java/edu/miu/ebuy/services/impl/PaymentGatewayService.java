package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.CardRepository;
import edu.miu.ebuy.dao.PaymentRepository;
import edu.miu.ebuy.models.Card;
import edu.miu.ebuy.models.Payment;
import edu.miu.ebuy.services.interfaces.IPaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentGatewayService implements IPaymentGatewayService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public boolean validateCard(String cardNo, String expireDate, int ccv, int typeId) {
        List<Card> lst = cardRepository.findByCardNumberAndExpireDateAndCcvAndTypeId(cardNo, expireDate, ccv, typeId);
        if (lst==null || lst.size()==0)
            return false;
        return  true;
    }

    @Override
    public boolean validateCard(String cardNo, String expireDate, int ccv, int typeId, double amount) {
//        List<Card> lst = cardRepository.findByCardNumberAndExpireDateAndCcvAndTypeIdAndBalanceGreaterThan(cardNo, expireDate, ccv, typeId, amount);
//        if (lst==null || lst.size()==0)
//            return false;
        return  true;
    }

    @Override
    public boolean pay(String cardNo, String expireDate, int ccv, int typeId, double amount) {
      return true;
    }
}
