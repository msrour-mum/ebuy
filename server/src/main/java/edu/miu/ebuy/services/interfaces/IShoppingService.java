package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.models.dto.Checkout;

public interface IShoppingService {

    public boolean checkout(Checkout checkout) throws ApplicationException;
    public boolean validateCard(String cardNo,String expireDate, int ccv, int typeId);
}
