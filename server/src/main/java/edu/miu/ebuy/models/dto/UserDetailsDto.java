package edu.miu.ebuy.models.dto;

import edu.miu.ebuy.models.Role;
import edu.miu.ebuy.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsDto {

    private Integer id;
    private String name;
    private String email;
    private Role role;
    private Boolean isActive;
    private String imageUrl;
    private User vendor;
}
