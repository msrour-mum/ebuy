//package edu.miu.ebuy.services.impl;
//
//import edu.miu.ebuy.dao.ProductRepository;
//import edu.miu.ebuy.dao.PromotionRepository;
//import edu.miu.ebuy.models.Order;
//import edu.miu.ebuy.models.Promotion;
//import edu.miu.ebuy.services.interfaces.IPromotionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//public class PromotionService implements IPromotionService {
//
//    @Autowired
//    PromotionRepository promotionRepository;
//
//    @Override
//    public List<Promotion> getAll() {
//        return promotionRepository.findAll();
//    }
//
//    @Override
//    public List<Promotion> getByVendor(int vendorId) {
//        return promotionRepository.findByVendor_Id(vendorId);
//    }
//
//    @Override
//    public Promotion get(int promotionId) {
//        return promotionRepository.getOne(promotionId);
//    }
//
//    @Override
//    public Promotion create(Promotion promotion) {
//        return promotionRepository.save(promotion);
//    }
//
//    @Override
//    public Promotion update(Promotion promotion) {
//        return promotionRepository.save(promotion);
//    }
//
//    @Override
//    public void  delete(int promotionId) {
//
//        promotionRepository.deleteById(promotionId);
//    }
//}
