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
    private String holderName;

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

        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.ccv = ccv;
        this.expireDate = expireDate;
        this.cardType = cardType;
    }

    public int getId() {
        return id;
    }

    public UserCard setId(int id) {
        this.id = id;
        return this;
    }

    public String getHolderName() {
        return holderName;
    }

    public UserCard setHolderName(String holderName) {
        holderName = holderName;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public UserCard setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public int getCcv() {
        return ccv;
    }

    public UserCard setCcv(int ccv) {
        this.ccv = ccv;
        return this;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public UserCard setExpireDate(String expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public CardType getCardType() {
        return cardType;
    }

    public UserCard setCardType(CardType cardType) {
        this.cardType = cardType;
        return this;
    }
}
