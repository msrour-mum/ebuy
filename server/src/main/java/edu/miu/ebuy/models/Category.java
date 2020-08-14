package edu.miu.ebuy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "isDeleted",nullable = false, columnDefinition = "BIT(1) default 0")
    private boolean isDeleted;

    public Category(Integer id, String name) {
        //this.id = id;
        this.name = name;
    }
    public Category(Integer id) {
        this.id = id;

    }

    public Integer getId() {
        return id;
    }

    public Category setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Category setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }
}
