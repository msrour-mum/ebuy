package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.*;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.models.Card;
import edu.miu.ebuy.models.dto.Checkout;
import edu.miu.ebuy.services.interfaces.IShoppingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShoppingService implements IShoppingService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;

    @Override
    public boolean checkout(Checkout checkout) throws ApplicationException {
        if (!validateCard(checkout.getPayment().getCardNumber(),checkout.getPayment().getExpireDate(),
                checkout.getPayment().getCvv(), checkout.getPayment().getCardType().getId()))
        {
            throw new ApplicationException("Card not valid!", Errors.CARD_NOT_Valid);
        }

        orderRepository.save(checkout.getOrder());
        checkout.getPayment().setOrder(checkout.getOrder());
        paymentRepository.saveAndFlush(checkout.getPayment());

    }

    @Override
    public boolean validateCard(String cardNo, String expireDate, int ccv, int typeId) {
        List<Card> lst = cardRepository.findByCardNumberAndExpireDateAndccvAndtypeId(cardNo, expireDate, ccv, typeId);
        if (lst==null || lst.size()==0)
            return false;
        return  true;
    }
}
