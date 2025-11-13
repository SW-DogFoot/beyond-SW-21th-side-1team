package com.mini.timedeal.domain;

import com.mini.timedeal.config.AppConfig;
import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.product.service.ProductService;
import com.mini.timedeal.domain.promotion.service.PromotionService;
import com.mini.timedeal.domain.user.service.UserProductService;
import com.mini.timedeal.domain.user.service.UserService;
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
    @DisplayName("컨텍스트 저장소 초기화 테스트")
    public void initRepositoryTest() {
        AppContext context = AppContext.getInstance();
        config.initRepository();

        ProductService productService1 = context.getBean(ProductService.class);
        ProductService productService2 = context.getBean(ProductService.class);
        Assertions.assertEquals(productService1, productService2);

        PromotionService promotionService1 = context.getBean(PromotionService.class);
        PromotionService promotionService2 = context.getBean(PromotionService.class);
        Assertions.assertEquals(promotionService1, promotionService2);

        UserService userService1 = context.getBean(UserService.class);
        UserService userService2 = context.getBean(UserService.class);
        Assertions.assertEquals(userService1, userService2);

        UserProductService userProductService1 = context.getBean(UserProductService.class);
        UserProductService userProductService2 = context.getBean(UserProductService.class);
        Assertions.assertEquals(userProductService1, userProductService2);
    }

    @Test
    @DisplayName("컨텍스트 MyBatis 초기화 테스트")
    public void initMyBatisTest() {
        AppContext context = AppContext.getInstance();
        config.initMyBatis();

        ProductService productService1 = context.getBean(ProductService.class);
        ProductService productService2 = context.getBean(ProductService.class);
        Assertions.assertEquals(productService1, productService2);

        PromotionService promotionService1 = context.getBean(PromotionService.class);
        PromotionService promotionService2 = context.getBean(PromotionService.class);
        Assertions.assertEquals(promotionService1, promotionService2);

        UserService userService1 = context.getBean(UserService.class);
        UserService userService2 = context.getBean(UserService.class);
        Assertions.assertEquals(userService1, userService2);

        UserProductService userProductService1 = context.getBean(UserProductService.class);
        UserProductService userProductService2 = context.getBean(UserProductService.class);
        Assertions.assertEquals(userProductService1, userProductService2);
    }
}
