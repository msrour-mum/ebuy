package edu.miu.ebuy.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@RestController
@RequestMapping(value="/test")
public class TestController {

    @GetMapping(value = "/{id}")
    public String get(@PathVariable("id") int id) {
        try {
            return Integer.toString(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
