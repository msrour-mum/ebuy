package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.models.Order;
import edu.miu.ebuy.models.Promotion;

import java.util.List;

public interface IPromotionService {
    List<Promotion> getAll();
    List<Promotion> getByVendor(int vendorId);
    Promotion get(int promotionId);
    Promotion create(Promotion promotion);
    Promotion update(Promotion promotion);
    void  delete(int promotionId);
}
