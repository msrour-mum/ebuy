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


    public long getId() {
        return id;
    }

    public Payment setId(long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Payment setUser(User user) {
        this.user = user;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Payment setOrder(Order order) {
        this.order = order;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Payment setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public int getCvv() {
        return cvv;
    }

    public Payment setCvv(int cvv) {
        this.cvv = cvv;
        return this;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public Payment setExpireDate(String expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Payment setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Payment setCardType(CardType cardType) {
        this.cardType = cardType;
        return this;
    }
}
