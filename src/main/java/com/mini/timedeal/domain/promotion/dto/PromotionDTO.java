package com.mini.timedeal.domain.promotion.dto;

import com.mini.timedeal.domain.prodcut.model.Product;
import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.support.utils.Calculator;

import java.time.LocalDateTime;

public class PromotionDTO {
    private final Long promotionId;
    private final String productName;
    private final String productDescription;
    private final Integer discountedPrice;
    private final Integer remainingQuantity;
    private final String remainingTime;

    public PromotionDTO(Long promotionId, String productName, String productDescription, Integer discountedPrice, Integer remainingQuantity, String remainingTime) {
        this.promotionId = promotionId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.discountedPrice = discountedPrice;
        this.remainingQuantity = remainingQuantity;
        this.remainingTime = remainingTime;
    }

    public static PromotionDTO from(Promotion promotion, Product product, LocalDateTime now) {
        int discountedPrice = Calculator.calculateDiscountedPrice(product.getPrice(), promotion.getDiscountRate());
        int remainingQuantity = promotion.getTotalQuantity() - promotion.getIssuedQuantity();
        String remainingTime = Calculator.calculateRemainingTime(now, promotion.getEndTime());

        return new PromotionDTO(
                promotion.getId(),
                product.getName(),
                product.getDescription(),
                discountedPrice,
                remainingQuantity,
                remainingTime
        );
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Integer getDiscountedPrice() {
        return discountedPrice;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    @Override
    public String toString() {
        return "PromotionDTO{" +
                "promotionId=" + promotionId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", discountedPrice=" + discountedPrice +
                ", remainingQuantity=" + remainingQuantity +
                ", remainingTime='" + remainingTime + '\'' +
                '}';
    }
}
