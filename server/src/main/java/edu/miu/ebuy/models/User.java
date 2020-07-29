package edu.miu.ebuy.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name ="roleId")
    private Role role=new Role();

    @Column(nullable = false)
    private String password;

    @Column(name = "isActive",nullable = false, columnDefinition = "BIT(1) default 1")
    private Boolean isActive;

    private String phone;
    private String ImageUrl;

    @Transient
    MultipartFile image;

    public User(String name,String email, Role role, String pass, boolean isActive,String phone) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = pass;
        this.isActive = isActive;
        this.phone = phone;
    }
}
