package edu.miu.ebuy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class CardTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Card card;

    @Column(name ="balance" , nullable = false)
    private double balance;

    @Column(nullable = false)
    private String description;

    @Column(name ="TransactionDate", nullable = false, columnDefinition = "DATETIME default now()")
    private Date TransactionDate;
}
