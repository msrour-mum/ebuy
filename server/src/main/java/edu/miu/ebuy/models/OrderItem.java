package edu.miu.ebuy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private Product product;

    @Column(name ="quantity" , nullable = false)
    private int quantity;

    @Column(name ="itemTotal" , nullable = false)
    private double itemTotal;


    public OrderItem(Product product, int quantity, double itemTotal) {

        this.product = product;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    public long getId() {
        return id;
    }

    public OrderItem setId(long id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public OrderItem setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderItem setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public OrderItem setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
        return this;
    }
}
