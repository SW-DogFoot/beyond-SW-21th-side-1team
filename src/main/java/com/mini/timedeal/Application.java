package com.mini.timedeal;

import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.product.dto.ProductDTO;
import com.mini.timedeal.domain.product.service.ProductService;
import com.mini.timedeal.domain.promotion.scheduler.PromotionScheduler;
import com.mini.timedeal.config.AppConfig;
import com.mini.timedeal.domain.promotion.service.PromotionService;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.model.UserProduct;
import com.mini.timedeal.domain.user.service.UserProductService;
import com.mini.timedeal.domain.user.service.UserService;
import com.mini.timedeal.enums.UserRole;
import com.mini.timedeal.view.LoginView;

import java.beans.Statement;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        appConfig.initMyBatis();

        //dummy();

        PromotionScheduler scheduler = new PromotionScheduler();
        scheduler.start();

        LoginView loginView = new LoginView();
        loginView.title();
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
        userService.addUser(new User(1L, "user", "user", UserRole.USER));
        userService.addUser(new User(2L, "admin", "admin", UserRole.ADMIN));

        // product
        productService.addProduct(new ProductDTO("laptop", "lightweight laptop", 1200));
        productService.addProduct(new ProductDTO("headphones", "wireless over-ear", 250));
        productService.addProduct(new ProductDTO("keyboard", "mechanical keyboard", 150));
        productService.addProduct(new ProductDTO("monitor", "4K ultra display", 800));
        productService.addProduct(new ProductDTO("smartwatch", "health tracking watch", 300));
        productService.addProduct(new ProductDTO("camera", "compact digital cam", 900));
        productService.addProduct(new ProductDTO("notebook", "soft cover notebook", 15));
        productService.addProduct(new ProductDTO("pen", "smooth gel pen", 5));
        productService.addProduct(new ProductDTO("mug", "ceramic coffee mug", 20));
        productService.addProduct(new ProductDTO("backpack", "waterproof travel bag", 70));

        // promotion
        promotionService.registerPromotion(1, 15, 120, LocalDateTime.now().plusSeconds(10), LocalDateTime.now().plusSeconds(30));
        promotionService.registerPromotion(2, 10, 200, LocalDateTime.now().plusSeconds(20), LocalDateTime.now().plusSeconds(40));
        promotionService.registerPromotion(3, 25, 150, LocalDateTime.now().plusSeconds(30), LocalDateTime.now().plusSeconds(50));
        promotionService.registerPromotion(4, 30, 180, LocalDateTime.now().plusSeconds(40), LocalDateTime.now().plusSeconds(60));
        promotionService.registerPromotion(5, 20, 130, LocalDateTime.now().plusSeconds(50), LocalDateTime.now().plusSeconds(70));
        promotionService.registerPromotion(6, 35, 160, LocalDateTime.now().plusSeconds(60), LocalDateTime.now().plusSeconds(80));
        promotionService.registerPromotion(7, 12, 300, LocalDateTime.now().plusSeconds(70), LocalDateTime.now().plusSeconds(90));
        promotionService.registerPromotion(8, 18, 500, LocalDateTime.now().plusSeconds(80), LocalDateTime.now().plusSeconds(100));
        promotionService.registerPromotion(9, 22, 250, LocalDateTime.now().plusSeconds(90), LocalDateTime.now().plusSeconds(110));
        promotionService.registerPromotion(10, 28, 140, LocalDateTime.now().plusSeconds(100), LocalDateTime.now().plusSeconds(120));

        // user product
    }
}
