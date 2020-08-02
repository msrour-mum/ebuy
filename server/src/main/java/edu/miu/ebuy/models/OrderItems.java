package edu.miu.ebuy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private Product product;

    @Column(name ="quantity" , nullable = false)
    private int quantity;

    @Column(name ="itemTotal" , nullable = false)
    private double itemTotal;


    public OrderItems(Product product, int quantity, double itemTotal) {

        this.product = product;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    public long getId() {
        return id;
    }

    public OrderItems setId(long id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public OrderItems setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderItems setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public OrderItems setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
        return this;
    }
}
