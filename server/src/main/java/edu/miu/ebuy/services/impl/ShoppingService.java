package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.*;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.*;
import edu.miu.ebuy.models.dto.CheckoutDto;
import edu.miu.ebuy.models.dto.OrdersDto;
import edu.miu.ebuy.models.lookup.CardType;
import edu.miu.ebuy.security.Context;
import edu.miu.ebuy.services.interfaces.IMerchantService;
import edu.miu.ebuy.services.interfaces.IShoppingService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShoppingService implements IShoppingService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    UserService userService;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    IMerchantService merchantService;

    @Autowired
    private ProductService productService;

    @Override
    public boolean checkout(CheckoutDto checkout) throws ApplicationException {
//        if (!merchantService.validateCard(checkout.getPayment().getCardNumber(),checkout.getPayment().getExpireDate(),
//                checkout.getPayment().getCcv(), checkout.getPayment().getCardType().getId()))
//        {
//            throw new ApplicationException("Card not valid!", Errors.CARD_NOT_VALID);
//            //return false;
//        }

        User user = userService.get(Context.getUserId());

        if(checkout.getCheckoutOptions().isSaveUpdateMyAddress()) {
            user.setAddress(checkout.getAddress());
        }

        if(checkout.getCheckoutOptions().isSaveUpdateMyCard()) {
            if(user.getCard() == null) {
                //(String holderName, String cardNumber, int ccv, String expireDate, CardType cardType)
                user.setCard(new UserCard(checkout.getCard().getHolderName(),
                        checkout.getCard().getCardNumber(),
                        checkout.getCard().getCcv(),
                        checkout.getCard().getExpireDate(),
                        checkout.getCard().getCardType()));
            } else {
                user.getCard().setCardNumber(checkout.getCard().getCardNumber());
                user.getCard().setCardType(checkout.getCard().getCardType());
                user.getCard().setCcv(checkout.getCard().getCcv());
                user.getCard().setExpireDate(checkout.getCard().getExpireDate());
                user.getCard().setHolderName(checkout.getCard().getHolderName());
            }
        }

        List<Product> products = productService.getAllProducts(checkout
                .getItems()
                .stream()
                .map(p-> p.getProductId())
                .collect(Collectors.toList()));

        Order order = new Order(user, new Date(), checkout.getShipping(), checkout.getAddress());

        products.forEach(p-> {
            int quantity = checkout.getItems().stream().filter(i -> i.getProductId() == p.getId()).findFirst().get().getQuantity();
            addOrderLine(order, p, quantity);
        });

        userService.update(user);
        orderRepository.save(order);
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
        Order order = new Order(user,new Date(), orderItem.getItemTotal(), shipping,user.getAddress());
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

    private void addOrderLine(Order order, Product product, int quantity)
    {
        OrderItem orderItem = order.getItems()
                .stream()
                .filter(ol -> ol.getProduct().getId() == product.getId())
                .findFirst().orElse(null);

        if(orderItem == null) {

            OrderItem orderLine = new OrderItem(product,
                    quantity,
                    product.getPrice());
            order.getItems().add(orderLine);
        }

        double subTotal =  order
                .getItems()
                .stream()
                .mapToDouble(OrderItem::getItemTotal)
                .sum();

        double total = subTotal + order.getShipping();
        order.setTotal(total);
    }
}
