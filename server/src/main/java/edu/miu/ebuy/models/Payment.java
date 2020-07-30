package edu.miu.ebuy.models;

import edu.miu.ebuy.models.lookup.CardType;
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
    private int cvv;

    @Column(nullable = false)
    private String expireDate;

    @Column(name ="transactionDate", nullable = false, columnDefinition = "DATETIME default now()")
    private Date transactionDate;

    @ManyToOne(optional = false)
    @JoinColumn(name ="cardTypeId")
    private CardType cardType;

}
