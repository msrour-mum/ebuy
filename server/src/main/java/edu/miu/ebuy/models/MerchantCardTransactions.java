package edu.miu.ebuy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Merchant_CardTransactions")
@Data
@NoArgsConstructor
public class MerchantCardTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name ="cardId")
    private MerchantCard card;

    @Column(name ="amount" , nullable = false)
    private double amount;

    @Column(nullable = false)
    private String description;

    @Column(name ="TransactionDate", nullable = false, columnDefinition = "DATETIME default now()")
    private Date TransactionDate;
}
