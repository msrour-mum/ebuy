package edu.miu.ebuy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate fromDate;

    @Column(name ="toDate", nullable = false, columnDefinition = "DATETIME")
    private LocalDate toDate;

    @ManyToOne(optional = false)
    @JoinColumn(name ="productId")
    @JsonIgnore
    private Product product;

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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public Promotion setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Promotion setToDate(LocalDate toDate) {
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

    public Promotion( String name, double discount, LocalDate fromDate, LocalDate toDate, Product product) {

        this.name = name;
        this.discount = discount;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.product = product;
    }
}
