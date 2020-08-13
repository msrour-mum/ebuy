package edu.miu.ebuy.controllers;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.models.dto.UserDetailsDto;
import edu.miu.ebuy.security.JPAUserDetails;
import edu.miu.ebuy.security.JwtToken;
import edu.miu.ebuy.services.impl.JwtUserDetailsService;
import edu.miu.ebuy.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@BaseResponse
@RequestMapping(value = "api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public Map<Object, Object> login(@RequestBody User user) throws Exception {
        Authentication auth = authenticate(user.getEmail(), user.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final JPAUserDetails jpaUserDetails = (JPAUserDetails) userDetails;

        Map<Object, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", new UserDetailsDto(jpaUserDetails.getId(),
                jpaUserDetails.getName(),
                jpaUserDetails.getUsername(),
                jpaUserDetails.getRole(),
                jpaUserDetails.isEnabled(),
                jpaUserDetails.getImageUrl(),
                jpaUserDetails.getVendor()));
        return model;
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}

