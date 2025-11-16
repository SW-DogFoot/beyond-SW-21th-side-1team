package com.mini.timedeal.domain.user.dto;

import com.mini.timedeal.domain.product.model.Product;
import com.mini.timedeal.domain.promotion.model.Promotion;

import java.time.LocalDateTime;

public class UserProductDTO {

    private Long promotionId;
    private String productName;
    private int price;
    private String purchasedAt;

    public UserProductDTO(Long promotionId, String productName, int price, String purchasedAt) {
        this.promotionId = promotionId;
        this.productName = productName;
        this.price = price;
        this.purchasedAt = purchasedAt;
    }

    public static UserProductDTO from(Promotion promotion, Product product, LocalDateTime now) {
        String purchasedAt = now.toString();

        return new UserProductDTO(
                promotion.getId(),
                product.getName(),
                product.getPrice(),
                purchasedAt
        );
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public String getPurchasedAt() {
        return purchasedAt;
    }
}
