package edu.miu.ebuy.controllers;


import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.models.Promotion;
import edu.miu.ebuy.services.impl.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@BaseResponse
@RequestMapping(value = "/api/promotions")
public class PromotionController {
    @Autowired
    PromotionService promotionService;

    @GetMapping()
    public List<Promotion> getAll() {
        return promotionService.getAll();
    }



    @GetMapping("/{promotionId}")
    public Promotion get(@PathVariable int promotionId) {
        return promotionService.get(promotionId);
    }

    @PostMapping
    public Promotion add(@RequestBody Promotion promotion)  {
        return promotionService.create(promotion);
    }

    @PutMapping("/{promotionId}")
    public Promotion update(@RequestBody Promotion promotion) {
        return promotionService.update(promotion);
    }

    @DeleteMapping("/{promotionId}")
    public void delete(@PathVariable int promotionId) {
        promotionService.delete(promotionId);
    }
}
