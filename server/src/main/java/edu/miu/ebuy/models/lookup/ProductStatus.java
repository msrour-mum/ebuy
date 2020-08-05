package edu.miu.ebuy.models.lookup;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class   ProductStatus {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    public ProductStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public ProductStatus setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductStatus setName(String name) {
        this.name = name;
        return this;
    }
}
