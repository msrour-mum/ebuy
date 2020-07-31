package edu.miu.ebuy.models.lookup;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class CardType {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

}