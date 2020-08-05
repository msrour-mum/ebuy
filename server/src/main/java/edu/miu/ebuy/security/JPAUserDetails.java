package edu.miu.ebuy.security;

import edu.miu.ebuy.models.Role;
import edu.miu.ebuy.models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class JPAUserDetails implements UserDetails {

    private int id;
    private String name;
    private String username;
    private String password;
    private boolean isActive;
    private String imageUrl;
    private List<Role> roles;

    public JPAUserDetails(User user) {
        id = user.getId();
        name = user.getName();
        username = user.getEmail();
        password = user.getPassword();
        isActive = user.getIsActive();
        roles = new ArrayList<>();
        roles.add(user.getRole());
        imageUrl = user.getImageUrl();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return this.name;}

    public List<Role> getRoles() { return this.roles;}
    public Role getRole() { return this.roles.get(0);}
}
