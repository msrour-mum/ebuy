package edu.miu.ebuy.controllers;


import edu.miu.ebuy.common.http.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BaseResponse
@RequestMapping(value = "/api")
public class ShoppingController {
}
