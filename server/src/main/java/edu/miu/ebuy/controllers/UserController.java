package edu.miu.ebuy.controllers;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.security.Context;
import edu.miu.ebuy.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@BaseResponse
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    IUserService userService;


    @PostMapping("/signup")
    public User add(@RequestBody User user) throws ApplicationException {
        try {
            return userService.create(user);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationException("User already exist!", Errors.NOT_UNIQUE_USER_NAME_ERROR);
        }
    }

    @PutMapping()
    public User update(@RequestBody User user) {
        try {
            return userService.create(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping()
    public List<User> getAll() {
        try {
            return userService.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        try {
            return userService.get(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") int id) {
        try {
            userService.delete(id);
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }
}

