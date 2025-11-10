package com.mini.timedeal.domain;

import java.time.LocalDateTime;

/**
 * 유저가 구매한 상품
 */
public class UserProduct {
    private Long id;
    private Long userId;
    private Long productId;
    private LocalDateTime purchasedAt;
}
