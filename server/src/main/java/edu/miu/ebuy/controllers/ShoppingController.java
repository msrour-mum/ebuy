package edu.miu.ebuy.controllers;


import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.models.Order;
import edu.miu.ebuy.models.dto.Checkout;
import edu.miu.ebuy.services.interfaces.IShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BaseResponse
@RequestMapping(value = "/api")
public class ShoppingController {

    @Autowired
    IShoppingService  iShoppingService;

    @PostMapping("/checkout")
    public boolean checkout(Checkout checkout) throws ApplicationException {
            return  iShoppingService.checkout(checkout);

    }

    private boolean validateCard(String cardNo,String expireDate, int ccv,int typeId)
    {
        return iShoppingService.validateCard(cardNo,expireDate,  ccv, typeId);
    }
}
