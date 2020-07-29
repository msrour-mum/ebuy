package edu.miu.ebuy.models;

import edu.miu.ebuy.models.lookup.CardType;
import edu.miu.ebuy.models.lookup.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Card {

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
    private int secretCode;

    @Column(nullable = false)
    private String expireDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "typeId", nullable = false)
    private CardType typeId;
}
