package com.mini.timedeal.domain.promotion.scheduler;

import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.promotion.service.PromotionService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PromotionScheduler {
    private final ScheduledExecutorService scheduler;
    private final PromotionService promotionService;

    public PromotionScheduler() {
        AppContext context = AppContext.getInstance();
        this.promotionService = context.getBean(PromotionService.class);

        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void start() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                promotionService.updatePromotion();
            }
        };
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
