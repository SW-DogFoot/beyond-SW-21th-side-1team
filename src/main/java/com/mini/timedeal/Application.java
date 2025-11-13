package com.mini.timedeal;

import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.prodcut.dto.ProductDTO;
import com.mini.timedeal.domain.prodcut.service.ProductService;
import com.mini.timedeal.domain.promotion.scheduler.PromotionScheduler;
import com.mini.timedeal.config.AppConfig;
import com.mini.timedeal.domain.promotion.service.PromotionService;
import com.mini.timedeal.domain.user.service.UserProductService;
import com.mini.timedeal.domain.user.service.UserService;
import com.mini.timedeal.view.LoginView;

import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        appConfig.initRepository();

        dummy();

        PromotionScheduler scheduler = new PromotionScheduler();
        scheduler.start();

        LoginView loginView = new LoginView();
        loginView.login();

        scheduler.shutdown();
    }

    private static void dummy() {
        AppContext context = AppContext.getInstance();

        // service
        UserService userService = context.getBean(UserService.class);
        PromotionService promotionService = context.getBean(PromotionService.class);
        ProductService productService = context.getBean(ProductService.class);
        UserProductService userProductService = context.getBean(UserProductService.class);

        // user

        // product
        productService.addProduct(new ProductDTO(1L, "admin", "computer", "computer desc", 5000));
        productService.addProduct(new ProductDTO(1L, "admin", "book", "book desc", 1000));
        productService.addProduct(new ProductDTO(1L, "admin", "apple", "apple desc", 500));

        // promotion
        promotionService.registerPromotion(1, 10, 100, LocalDateTime.now().plusSeconds(10), LocalDateTime.now().plusSeconds(30));
        promotionService.registerPromotion(2, 20, 200, LocalDateTime.now().plusSeconds(20), LocalDateTime.now().plusSeconds(40));
        promotionService.registerPromotion(3, 30, 300, LocalDateTime.now().plusSeconds(30), LocalDateTime.now().plusSeconds(50));

        // user product
    }
}
