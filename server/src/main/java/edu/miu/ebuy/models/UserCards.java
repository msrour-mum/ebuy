package edu.miu.ebuy.models;

import edu.miu.ebuy.models.lookup.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UserCards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private User user;

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
