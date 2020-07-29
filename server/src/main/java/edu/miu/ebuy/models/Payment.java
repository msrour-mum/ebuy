package edu.miu.ebuy.models;

import edu.miu.ebuy.models.lookup.OrderStatus;
import edu.miu.ebuy.models.lookup.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Order order;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private int ccv;

    @Column(nullable = false)
    private int secretCode;

    @Column(nullable = false)
    private String expireDate;

    @Column(name ="TransactionDate", nullable = false, columnDefinition = "DATETIME default now()")
    private Date TransactionDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "typeId", nullable = false)
    private PaymentType paymentType;



}
