package edu.miu.ebuy.models.dto;

import edu.miu.ebuy.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
public class UserDetailsDto {

    private Integer id;
    private String name;
    private String email;
    private Role role;
    private Boolean isActive;
    private String imageUrl;
    //private String address;
    //private String phone;
}
