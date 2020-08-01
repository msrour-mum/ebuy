package edu.miu.ebuy.controllers;


import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.models.Order;
import edu.miu.ebuy.models.dto.Checkout;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BaseResponse
@RequestMapping(value = "/api")
public class ShoppingController {


    @PostMapping("/checkout")
    public boolean checkout(Checkout checkout) throws ApplicationException {


    }

    private boolean validateCard(String cardNo,String expireDate, int ccv)
    {
        return true;
    }
}
