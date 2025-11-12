package com.mini.timedeal.domain.promotion.mapper;

import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.enums.PromotionStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PromotionMapper {
    Promotion findById(Long id);
    List<Promotion> findAll();
    List<Promotion> findByStatus(PromotionStatus status);
    void insertPromotion(Promotion promotion);
    void updatePromotion(Promotion promotion);
    void deletePromotion(Long id);
    void decreaseStock(Long promotionId);
}
