package edu.miu.ebuy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name ="discount" , nullable = false)
    private double discount;

    @Column(name ="fromDate", nullable = false, columnDefinition = "DATETIME")
    private Date fromDate;

    @Column(name ="toDate", nullable = false, columnDefinition = "DATETIME")
    private Date toDate;


    @ManyToOne(optional = false)
    @JoinColumn(name ="vendorId")
    private User vendor;

    @ManyToOne(optional = false)
    @JoinColumn(name ="productId")
    private Product product;

    public User getVendor() {
        return vendor;
    }

    public Promotion setVendor(User vendor) {
        this.vendor = vendor;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Promotion setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Promotion setName(String name) {
        this.name = name;
        return this;
    }

    public double getDiscount() {
        return discount;
    }

    public Promotion setDiscount(double discount) {
        this.discount = discount;
        return this;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Promotion setFromDate(Date fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public Date getToDate() {
        return toDate;
    }

    public Promotion setToDate(Date toDate) {
        this.toDate = toDate;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Promotion setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Promotion( String name, double discount, Date fromDate, Date toDate, User vendor, Product product) {

        this.name = name;
        this.discount = discount;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.vendor = vendor;
        this.product = product;
    }
}
