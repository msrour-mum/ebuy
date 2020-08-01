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


    public Integer getId() {
        return id;
    }

    public CardType setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CardType setName(String name) {
        this.name = name;
        return this;
    }
}
