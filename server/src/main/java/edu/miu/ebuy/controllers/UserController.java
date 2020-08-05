package edu.miu.ebuy.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.common.storage.IStorageService;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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


    @PostMapping("/signup")
    public User add(@RequestBody User user) throws HttpException {
        if (user.getRole().getId() == 3) //no need to set user card for registered users
            user.setCard(null);
        return userService.create(user);
    }

//    @PutMapping()
//    public User update(@RequestBody User user) throws HttpException {
//        return userService.update(user);
//    }

    @PutMapping("/{userId}")
    public User update(@RequestParam(value ="file", required=false) MultipartFile file,  @RequestParam String user, @PathVariable String userId) throws HttpException, IOException {

        User userToUpdate = new ObjectMapper().readValue(user, User.class);
        if(file != null) {
            return userService.update(userToUpdate,
                    storageService.uploadMultipartFile(file, userId));
        }
        return userService.update(userToUpdate);
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
}

