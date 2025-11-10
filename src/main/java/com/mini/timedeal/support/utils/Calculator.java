package com.mini.timedeal.support.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class Calculator {

    public static int calculateDiscountedPrice(int originalPrice, int discountRate) {
        return originalPrice * (100 - discountRate) / 100;
    }

    public static String calculateRemainingTime(LocalDateTime now, LocalDateTime endTime) {
        Duration duration = Duration.between(now, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        return String.format("%d:%d:%d", hours, minutes, seconds);
    }
}
