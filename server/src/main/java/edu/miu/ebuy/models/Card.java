package edu.miu.ebuy.models;


import edu.miu.ebuy.models.lookup.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String expireDate;

    @ManyToOne(optional = false)
    @JoinColumn(name ="typeId")
    private CardType typeId;
}
