package edu.miu.ebuy.models.dto;

import edu.miu.ebuy.models.Order;
import edu.miu.ebuy.models.Payment;

public class Checkout {

    private Order order;
    private Payment payment;

    public Order getOrder() {
        return order;
    }

    public Checkout setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Payment getPayment() {
        return payment;
    }

    public Checkout setPayment(Payment payment) {
        this.payment = payment;
        return this;
    }
}
