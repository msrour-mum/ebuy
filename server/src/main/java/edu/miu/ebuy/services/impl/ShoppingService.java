package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.*;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.*;
import edu.miu.ebuy.models.dto.Checkout;
import edu.miu.ebuy.models.dto.OrdersDto;
import edu.miu.ebuy.services.interfaces.IMerchantService;
import edu.miu.ebuy.services.interfaces.IShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShoppingService implements IShoppingService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    IMerchantService merchantService;

    @Autowired
    private ProductService productService;

    @Override
    public boolean checkout(Checkout checkout) throws ApplicationException {
        if (!merchantService.validateCard(checkout.getPayment().getCardNumber(),checkout.getPayment().getExpireDate(),
                checkout.getPayment().getCcv(), checkout.getPayment().getCardType().getId()))
        {
            throw new ApplicationException("Card not valid!", Errors.CARD_NOT_VALID);
            //return false;
        }

        orderRepository.save(checkout.getOrder());
        checkout.getPayment().setOrder(checkout.getOrder());
        paymentRepository.saveAndFlush(checkout.getPayment());
        return true;

    }

    @Override
    public Product subscribeVendor(User user) throws HttpException {
        Product serviceProduct =  productService.getServiceProduct();
        if(!merchantService.validateCard(user.getCard().getCardNumber(),
                user.getCard().getExpireDate(),
                user.getCard().getCcv(),
                user.getCard().getCardType().getId())) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Invalid credit card", Errors.CARD_NOT_VALID);
        } else {
            if(!merchantService.pay(user.getCard().getCardNumber(),
                    user.getCard().getExpireDate(),
                    user.getCard().getCcv(),
                    user.getCard().getCardType().getId(),
                    serviceProduct.getPrice())) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Payment failed, insufficient balance", Errors.CARD_INSUFFICIENT_BALANCE);
            }
        }
        return serviceProduct;
    }

    public void addOrder(OrderItem orderItem, User user, double shipping) {
        Order order = new Order(user,new Date(), orderItem.getItemTotal(), shipping);
        orderRepository.save(order);
    }


    @Override
    public List<OrdersDto> userOrder(int userId){
        List<Order> lst= orderRepository.findByUserId(userId);
        List<OrdersDto> result = new ArrayList<>();
        for (Order order : lst){
            OrdersDto ordersDto = OrdersDto.readFull(order);
            result.add(ordersDto);
        }
        return result;
    }

}
