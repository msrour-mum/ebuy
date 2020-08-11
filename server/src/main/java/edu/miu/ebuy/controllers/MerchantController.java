package edu.miu.ebuy.controllers;


import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.UserCard;
import edu.miu.ebuy.services.interfaces.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@BaseResponse
@RequestMapping(value = "api/payment")
public class MerchantController {

    @Autowired
    IMerchantService iMerchantService;


    @PostMapping("/validateCard")
    public boolean validate(@RequestBody UserCard userCard) throws ApplicationException {
        return  iMerchantService.validateCard(userCard.getCardNumber(),userCard.getExpireDate(),userCard.getCcv(),userCard.getCardType().getId());

    }
    @PostMapping("/validateCard/{amount}")
    public boolean pay(@RequestBody UserCard userCard , @PathVariable double  amount) throws ApplicationException {
        return  iMerchantService
                .validateCard(userCard.getCardNumber(),
                        userCard.getExpireDate(),
                        userCard.getCcv(),
                        userCard.getCardType().getId(),amount);

    }
}
