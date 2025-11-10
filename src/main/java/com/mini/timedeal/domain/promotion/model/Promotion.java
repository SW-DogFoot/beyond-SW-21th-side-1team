package com.mini.timedeal.domain.promotion.model;

import com.mini.timedeal.enums.PromotionStatus;

import java.time.LocalDateTime;

/**
 * 프로모션
 */
public class Promotion {
    private Long id;
    private Long productId;
    private PromotionStatus status;
    private Integer discountRate;
    private Integer totalQuantity;
    private Integer issuedQuantity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // MyBatis를 위한 기본 생성자
    public Promotion() {
    }

    public Promotion(Long productId, Integer discountRate, Integer quantity, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = 0L;
        this.productId = productId;
        this.status = PromotionStatus.SCHEDULED;
        this.discountRate = discountRate;
        this.totalQuantity = quantity;
        this.issuedQuantity = 0;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public PromotionStatus getStatus() {
        return status;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public Integer getIssuedQuantity() {
        return issuedQuantity;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void updateStatus(PromotionStatus status) {
        this.status = status;
    }

    public boolean isActive() {
        return status == PromotionStatus.ACTIVE;
    }
}
