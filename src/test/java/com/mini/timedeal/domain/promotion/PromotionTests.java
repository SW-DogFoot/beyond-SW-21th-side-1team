package com.mini.timedeal.domain.promotion;

import com.mini.timedeal.config.AppConfig;
import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.prodcut.dto.ProductDTO;
import com.mini.timedeal.domain.prodcut.model.Product;
import com.mini.timedeal.domain.prodcut.service.ProductService;
import com.mini.timedeal.domain.prodcut.storage.ProductRepository;
import com.mini.timedeal.domain.promotion.dto.PromotionDTO;
import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.domain.promotion.scheduler.PromotionScheduler;
import com.mini.timedeal.enums.PromotionStatus;
import com.mini.timedeal.view.PromotionView;
import com.mini.timedeal.domain.promotion.service.PromotionService;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

public class PromotionTests {
    private static AppConfig config = new AppConfig();

    @BeforeEach
    public void init() {
        config.initRepository();

        ProductRepository productRepository = AppContext.getInstance().getBean(ProductRepository.class);
        productRepository.insertProduct(new Product(1L, "name1", "desc1", 1000));
        productRepository.insertProduct(new Product(2L, "name2", "desc2", 1000));

        PromotionService promotionService = AppContext.getInstance().getBean(PromotionService.class);
        promotionService.registerPromotion(1L, 10, 100, LocalDateTime.now().plusSeconds(5), LocalDateTime.now().plusSeconds(15));
        promotionService.registerPromotion(2L, 20, 200, LocalDateTime.now().plusSeconds(10), LocalDateTime.now().plusSeconds(20));
    }

    @Test
    @DisplayName("프로모션 전체 조회 테스트")
    public void promotionAllSelectTest() {
        PromotionView view = new PromotionView();
        view.showPromotions();
    }

    @Test
    @DisplayName("프로모션 등록 테스트")
    public void promotionRegisterTest() {
        PromotionService service = AppContext.getInstance().getBean(PromotionService.class);
        Assertions.assertNotEquals(service.getPromotion(1), null);
        Assertions.assertNotEquals(service.getPromotion(2), null);

        PromotionView view = new PromotionView();
        view.showPromotions();
    }

    @Test
    @DisplayName("프로모션 해제 테스트")
    public void promotionDeregisterTest() {
        PromotionService service = AppContext.getInstance().getBean(PromotionService.class);

        Long promotionId = 1L;
        Assertions.assertNotEquals(service.getPromotion(promotionId), null);

        System.out.println("Before");
        PromotionView view = new PromotionView();
        view.showPromotions();

        service.deregisterPromotion(promotionId);

        System.out.println("After");
        view.showPromotions();

        Assertions.assertEquals(service.getPromotion(promotionId), null);
    }

    @Test
    @DisplayName("프로모션 업데이트 테스트")
    public void promotionUpdateTest() {
        try {
            while (true) {
                PromotionService service = AppContext.getInstance().getBean(PromotionService.class);

                PromotionDTO promotion = service.getPromotion(1);
                Assertions.assertNotEquals(promotion, null);

                Thread.sleep(1000);

                service.updatePromotion();

                System.out.println("Active Promotion");
                PromotionView view = new PromotionView();
                view.showPromotions(PromotionStatus.ACTIVE);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("프로모션 업데이트 테스트")
    public void promotionSchedulerTest() {

        PromotionService service = AppContext.getInstance().getBean(PromotionService.class);
        PromotionScheduler scheduler = new PromotionScheduler();
        try {
            scheduler.start();

            while (service.getPromotion(2) != null) {
                Thread.sleep(1000);
                service.updatePromotion();
                PromotionView view = new PromotionView();
                view.showPromotions(PromotionStatus.ACTIVE);
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            scheduler.shutdown();
        }
    }
}
