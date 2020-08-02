package edu.miu.ebuy.services.interfaces;

public interface IPaymentGatewayService {

   boolean validateCard(String cardNo, String expireDate, int ccv, int typeId);
   boolean validateCard(String cardNo, String expireDate, int ccv, int typeId, double amount);
   boolean pay(String cardNo, String expireDate, int ccv, int typeId, double amount);
}
