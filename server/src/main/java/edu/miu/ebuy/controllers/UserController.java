package edu.miu.ebuy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.ebuy.common.enums.RoleEnum;
import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.common.storage.IStorageService;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.Order;
import edu.miu.ebuy.models.Role;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.security.Context;
import edu.miu.ebuy.services.interfaces.IOrderService;
import edu.miu.ebuy.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@BaseResponse
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    IStorageService storageService;

    @Autowired
    IOrderService orderService;


    @PostMapping("/signup")
    public User add(@RequestBody User user) throws HttpException {
        if (user.getRole().getId() == RoleEnum.REGESTERED.id) //no need to set user card for registered users
            user.setCard(null);
        return userService.signup(user);
    }

    @PutMapping("/{userId}")
    public User update(@RequestParam(value ="file", required=false) MultipartFile file,  @RequestParam String user, @PathVariable String userId) throws HttpException, IOException {

        User userToUpdate = new ObjectMapper().readValue(user, User.class);
        if(file != null) {
            return userService.update(userToUpdate,
                    storageService.uploadMultipartFile(file, userId));
        }
        return userService.update(userToUpdate);
    }

    @PostMapping
    public User create(@RequestParam String userJson, @RequestParam(value ="file", required=false) MultipartFile file) throws ApplicationException, IOException {
        //return productService.create(product);
        User user = new ObjectMapper().readValue(userJson, User.class);
        return userService.add(user,
                storageService.uploadMultipartFile(file, Context.getUserIdAsString()));
    }

    @GetMapping()
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        return userService.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @GetMapping(value="/{userId}/orders")
    public List<Order> getOrders (@PathVariable("userId") int userId)
    {
      return orderService.getAll(userId);
    }

    @GetMapping(value = "/roles")
    public List<Role> get() {
        return userService.getRoles();
    }
}

