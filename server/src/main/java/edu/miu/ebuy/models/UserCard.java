package edu.miu.ebuy.models;


import edu.miu.ebuy.models.lookup.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String HolderName;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private int ccv;


    @Column(nullable = false)
    private String expireDate;


    @ManyToOne(optional = false)
    @JoinColumn(name ="typeId")
    private CardType cardType;

    public UserCard(String holderName, String cardNumber, int ccv, String expireDate, CardType cardType) {

        HolderName = holderName;
        this.cardNumber = cardNumber;
        this.ccv = ccv;
        this.expireDate = expireDate;
        this.cardType = cardType;
    }
}
