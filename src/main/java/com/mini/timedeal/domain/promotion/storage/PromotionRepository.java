package com.mini.timedeal.domain.promotion.storage;

import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.enums.PromotionStatus;
import com.mini.timedeal.domain.promotion.mapper.PromotionMapper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PromotionRepository implements PromotionMapper {
    private Long primaryKey = 1L;
    private final Map<Long, Promotion> promotions = new HashMap<>();

    public PromotionRepository() {
        promotions.put(1L, new Promotion(1L,10, 100, LocalDateTime.now().plusMinutes(1), LocalDateTime.now().plusMinutes(10)));
    }

    @Override
    public Promotion findById(Long id) {
        return promotions.get(id);
    }

    @Override
    public List<Promotion> findAll() {
        return promotions.values().stream().toList();
    }

    @Override
    public List<Promotion> findByStatus(PromotionStatus status) {
        return promotions.values().stream()
                .filter(promotion -> promotion.getStatus() == status)
                .toList();
    }

    @Override
    public void insertPromotion(Promotion promotion) {
        Long id = primaryKey++;
        promotion.setId(id);
        promotions.put(id, promotion);
    }

    @Override
    public void updatePromotion(Promotion promotion) {
        promotions.put(promotion.getId(), promotion);
    }

    @Override
    public void deletePromotion(Long id) {
        promotions.remove(id);
    }

    @Override
    public void decreaseStock(Long promotionId) {
        Promotion promotion = findById(promotionId);
        if (promotion == null) {
            throw new IllegalArgumentException("존재하지 않는 프로모션입니다.");
        }

        int currentQuantity = promotion.getTotalQuantity();
        if (currentQuantity <= 0) {
            throw new IllegalStateException("재고가 부족합니다.");
        }

        promotion.setTotalQuantity(currentQuantity - 1);
    }
}
