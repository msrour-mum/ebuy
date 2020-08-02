package edu.miu.ebuy.models.dto;

import edu.miu.ebuy.models.Orders;
import edu.miu.ebuy.models.Payment;

public class Checkout {

    private Orders orders;
    private Payment payment;

    public Orders getOrders() {
        return orders;
    }

    public Checkout setOrders(Orders orders) {
        this.orders = orders;
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
