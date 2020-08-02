package edu.miu.ebuy.controllers;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@BaseResponse
@RestController
@RequestMapping(value="/test")
public class TestController {

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) throws Exception {

        if(id == 0){ // general exception
            throw new Exception("Invalid user id");
        }
        else if(id == -1) { //business exception

            throw new HttpException(HttpStatus.BAD_REQUEST, "Invalid credentials", Errors.INVALID_CREDENTIALS_ERROR);

        }
        return new User();
    }

    @GetMapping()
    public boolean get() throws Exception {

        return true;
    }

    @GetMapping(value = "/string")
    public ResponseEntity<?> get2() throws Exception {

        return ResponseEntity.ok("String");
    }

}
