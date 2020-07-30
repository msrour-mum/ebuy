package edu.miu.ebuy.models;

import com.sun.istack.Nullable;
import edu.miu.ebuy.models.lookup.CardType;
import edu.miu.ebuy.models.lookup.ProductStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name ="categoryId")
    private Category category;

    @Column(name ="cost" , nullable = false)
    private double cost;

    @Column(name ="price" , nullable = false)
    private double price;

    @ManyToOne(optional = false)
    @JoinColumn(name ="statusId")

    private ProductStatus productStatus;

    @Column(name = "isPublished",nullable = false, columnDefinition = "BIT(1) default 1")
    private boolean isPublished;

    @Column(name = "isService",nullable = false, columnDefinition = "BIT(1) default 0")
    private boolean isService;

    private String imageUrl;


}
