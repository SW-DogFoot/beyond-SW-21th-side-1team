package com.mini.timedeal.domain;

import com.mini.timedeal.config.AppConfig;
import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.promotion.mapper.PromotionMapper;
import com.mini.timedeal.domain.promotion.service.PromotionService;
import com.mini.timedeal.domain.promotion.storage.PromotionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppContextTests {
    private static AppConfig config = new AppConfig();

    @BeforeAll
    static void set() {

    }

    @Test
    @DisplayName("컨텍스트 테스트")
    public void promotionTest() {
        AppContext context = AppContext.getInstance();
        config.initRepository();

        PromotionService service1 = context.getBean(PromotionService.class);
        PromotionService service2 = context.getBean(PromotionService.class);

        Assertions.assertEquals(service1, service2);
    }
}
