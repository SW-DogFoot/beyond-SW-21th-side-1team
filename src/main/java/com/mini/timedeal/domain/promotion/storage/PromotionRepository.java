package com.mini.timedeal.domain.promotion.storage;

import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.enums.PromotionStatus;
import com.mini.timedeal.domain.promotion.mapper.PromotionMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PromotionRepository implements PromotionMapper {
    private Long primaryKey = 1L;
    private final Map<Long, Promotion> promotions = new HashMap<>();

    public PromotionRepository() {
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
}
