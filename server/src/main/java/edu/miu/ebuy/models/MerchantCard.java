package edu.miu.ebuy.models;


import edu.miu.ebuy.models.lookup.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Merchant_Card")
@Data
@NoArgsConstructor
public class MerchantCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private int ccv;

    @Column(nullable = false)
    private String expireDate;

    @ManyToOne(optional = false)
    @JoinColumn(name ="typeId")
    private CardType cardType;

    @Column(name ="balance" , nullable = false)
    private double balance;


    public MerchantCard(String ownerName, String cardNumber, int ccv, String expireDate, CardType cardType, double balance) {
        this.ownerName = ownerName;
        this.cardNumber = cardNumber;
        this.ccv = ccv;
        this.expireDate = expireDate;
        this.cardType = cardType;
        this.balance = balance;
    }
}
