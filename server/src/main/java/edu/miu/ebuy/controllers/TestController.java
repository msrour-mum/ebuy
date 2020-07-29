package edu.miu.ebuy.controllers;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@BaseResponse
@RestController
@RequestMapping(value="/test")
public class TestController {

    @GetMapping(value = "/{id}")
    public String get(@PathVariable("id") int id) throws Exception {

        if(id == 0){ // general exception
            throw new Exception("Invalid user id");
        }
        else if(id == -1) { //business exception

            throw new HttpException(HttpStatus.BAD_REQUEST, "Invalid credentials", Errors.INVALID_CREDENTIALS_ERROR);
        }
        return Integer.toString(id);
    }

}
