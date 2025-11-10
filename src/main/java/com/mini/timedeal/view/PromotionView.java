package com.mini.timedeal.view;

import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.promotion.dto.PromotionDTO;
import com.mini.timedeal.domain.promotion.service.PromotionService;
import com.mini.timedeal.enums.PromotionStatus;

import java.util.ArrayList;
import java.util.List;

public class PromotionView {
    private final PromotionService promotionService;

    public PromotionView() {
        AppContext context = AppContext.getInstance();
        this.promotionService = context.getBean(PromotionService.class);
    }

    /**
     *  특정 프로모션 보여주기
     */
    public void showPromotion(Long promotionId) {
        List<PromotionDTO> promotions = new ArrayList<>();
        promotions.add(promotionService.getPromotion(promotionId));
        printPromotions(promotions);
    }

    /**
     *  전체 프로모션 보여주기
     */
    public void showPromotions() {
        printPromotions(promotionService.getPromotions());
    }

    /**
     *  특정 프로모션 보여주기
     */
    public void showPromotions(PromotionStatus status) {
        printPromotions(promotionService.getPromotionsByStatus(status));
    }

    private void printPromotions(List<PromotionDTO> promotions) {
        final String headerFormat = "| %-4s | %-25s | %-35s | %-18s | %-20s | %-20s |";
        final String rowFormat = "| %-4d | %-25s | %-35s | %-18d | %-20s | %-20s |";
        final String separator = "+------+---------------------------+-------------------------------------+--------------------+----------------------+----------------------+";

        System.out.println(separator);
        System.out.printf(headerFormat, "No.", "Product Name", "Description", "Discounted Price", "Quantity Remaining", "Time Remaining");
        System.out.println();
        System.out.println(separator);

        for (PromotionDTO promotion : promotions) {
            System.out.printf(rowFormat,
                    promotion.getPromotionId(),
                    truncate(promotion.getProductName(), 25),
                    truncate(promotion.getProductDescription(), 35),
                    promotion.getDiscountedPrice(),
                    promotion.getRemainingQuantity(),
                    promotion.getRemainingTime()
            );
            System.out.println();
        }
        System.out.println(separator);
    }

    private String truncate(String text, int maxLength) {
        if (text == null) return "";
        return text.length() > maxLength ? text.substring(0, maxLength - 3) + "..." : text;
    }
}