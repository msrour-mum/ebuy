package edu.miu.ebuy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
public class UserSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="creationDate", nullable = false, columnDefinition = "DATETIME default now()")
    private Date creationDate;

    @Column(nullable = false)
    private String searchText;

    @ManyToOne(optional = false)
    private User user;

}
