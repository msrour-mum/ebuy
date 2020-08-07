package edu.miu.ebuy.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private Role role;

    @Column(nullable = false)
    private String password;

    @Column(name = "isActive",nullable = false, columnDefinition = "BIT(1) default 1")
    private Boolean isActive;

    private String address;
    private String phone;
    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cardId", referencedColumnName = "id")
    private UserCard card;

    public User(String name,String email, Role role, String pass, boolean isActive,String phone) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = pass;
        this.isActive = isActive;
        this.phone = phone;
    }

    public User(int id) {
        this.id = id;
    }

    public User(String name, String email, Role role, String password, Boolean isActive,  String phone, String address, String imageUrl) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.isActive = isActive;
        this.address = address;
        this.phone = phone;
        this.imageUrl = imageUrl;

    }


    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public User setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public User setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public UserCard getCard() {
        return card;
    }

    public User setCard(UserCard card) {
        this.card = card;
        return this;
    }
}
