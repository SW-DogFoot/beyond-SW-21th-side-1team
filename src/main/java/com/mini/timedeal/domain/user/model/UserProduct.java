package com.mini.timedeal.domain.user.model;

import java.time.LocalDateTime;

/**
 * 유저가 구매한 상품
 */
public class UserProduct {

    private Long id;
    private Long userId;
    private Long productId;
    private LocalDateTime purchasedAt;

    public UserProduct() {
    }

    public UserProduct(Long userId, Long productId, LocalDateTime purchasedAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.purchasedAt = purchasedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public LocalDateTime getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(LocalDateTime purchasedAt) {
        this.purchasedAt = purchasedAt;
    }
}
