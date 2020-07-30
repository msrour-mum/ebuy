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
}
