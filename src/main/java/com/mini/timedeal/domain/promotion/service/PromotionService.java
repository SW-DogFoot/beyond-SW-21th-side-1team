package com.mini.timedeal.domain.promotion.service;

import com.mini.timedeal.domain.Product;
import com.mini.timedeal.domain.promotion.mapper.PromotionMapper;
import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.domain.promotion.dto.PromotionDTO;
import com.mini.timedeal.enums.PromotionStatus;
import com.mini.timedeal.support.utils.Calculator;

import java.time.LocalDateTime;
import java.util.List;

public final class PromotionService {
    private final PromotionMapper promotionMapper;

    public PromotionService(PromotionMapper promotionMapper) {
        this.promotionMapper = promotionMapper;
    }

    /**
     *  프로모션 등록
     */
    public void registerPromotion(long productId, int discountRate, int quantity, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if(endDateTime.isBefore(startDateTime) || startDateTime.isAfter(endDateTime)) {
            return;
        }

        if(discountRate <= 0) {
            return;
        }

        Promotion promotion = new Promotion(productId, discountRate, quantity, startDateTime, endDateTime);
        promotionMapper.insertPromotion(promotion);
    }

    /**
     *  프로모션 등록 해제
     *  진행중인 프로모션은 등록 해제 못함
     */
    public void deregisterPromotion(long promotionId) {
        Promotion promotion = promotionMapper.findById(promotionId);
        if(promotion == null){
            return;
        }

        if(!promotion.isActive()) {
            promotionMapper.deletePromotion(promotionId);
        }
    }

    /**
     *  프로모션 업데이트
     *  시작 날짜와 종료 날짜를 기반으로 상태값을 변경한다
     */
    public void updatePromotion() {
        List<Promotion> promotions = promotionMapper.findAll();
        LocalDateTime now = LocalDateTime.now().plusSeconds(1);

        for (Promotion promotion : promotions) {

            if(promotion.getStatus() == PromotionStatus.SCHEDULED) {
                if(now.isAfter(promotion.getStartTime())) {
                    promotion.updateStatus(PromotionStatus.ACTIVE);
                    promotionMapper.updatePromotion(promotion);
                }
            } else if(promotion.getStatus() == PromotionStatus.ACTIVE) {
                if(now.isAfter(promotion.getEndTime())) {
                    promotion.updateStatus(PromotionStatus.ENDED);
                    promotionMapper.updatePromotion(promotion);
                }
            }
        }
    }

    /**
     *  특정 프로모션 조회
     */
    public PromotionDTO getPromotion(long id) {
        Promotion promotion = promotionMapper.findById(id);
        if(promotion == null){
            return null;
        }

        Product product = new Product();

        int discountedPrice = Calculator.calculateDiscountedPrice(1000, promotion.getDiscountRate());
        int remainingQuantity = promotion.getTotalQuantity() - promotion.getIssuedQuantity();
        String remainingTime = Calculator.calculateRemainingTime(LocalDateTime.now(), promotion.getEndTime());

        return new PromotionDTO(
                promotion.getId(),
                "temp promotion",
                "temp description",
                discountedPrice,
                remainingQuantity,
                remainingTime
        );
    }

    /**
     *  프로모션 전체 조회
     */
    public List<PromotionDTO> getPromotions() {
        List<Promotion> promotions = promotionMapper.findAll();
        LocalDateTime now = LocalDateTime.now();

        return promotions.stream()
                .map(promotion -> {
                    Product product = new Product();
                    return PromotionDTO.from(promotion, product, now);
                })
                .toList();
    }

    /**
     *  프로모션 상태값으로 조회
     */
    public List<PromotionDTO> getPromotionsByStatus(PromotionStatus status) {
        List<Promotion> promotions = promotionMapper.findByStatus(status);
        LocalDateTime now = LocalDateTime.now();

        return promotions.stream()
                .map(promotion -> {
                    Product product = new Product();
                    return PromotionDTO.from(promotion, product, now);
                })
                .toList();
    }
}
