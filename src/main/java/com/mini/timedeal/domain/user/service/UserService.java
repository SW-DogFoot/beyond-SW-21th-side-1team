package com.mini.timedeal.domain.user.service;

import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.promotion.mapper.PromotionMapper;
import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.domain.promotion.storage.PromotionRepository;
import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.mapper.UserProductMapper;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.model.UserProduct;
import com.mini.timedeal.domain.user.storage.UserProductRepository;

import java.time.LocalDateTime;
import java.util.List;

public class UserService {

    private final UserMapper userMapper;
    private final UserProductMapper userProductMapper;
    private final PromotionMapper promotionMapper;

    public UserService(UserMapper userMapper, UserProductMapper userProductMapper, PromotionMapper promotionMapper) {
        AppContext context = AppContext.getInstance();
        this.userMapper = userMapper;
        this.userProductMapper = userProductMapper;
        this.promotionMapper = promotionMapper;
    }

    /*
    * 로그인
    * */
    public User login(String username, String password) {

        return userMapper.findByUsernameAndPassword(username, password);
    }

    /*
    * 프로모션 상품 주문
    * */
    public UserProduct order(Long promotionId, User currentUser) {

        // 프로모션 조회/검증
        Promotion promotion = promotionMapper.findById(promotionId);
        if (promotion == null) {
            throw new IllegalArgumentException("존재하지 않는 프로모션입니다.");
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(promotion.getStartTime())) {
            throw new IllegalStateException("아직 시작되지 않은 프로모션입니다.");
        }
        if (now.isAfter(promotion.getEndTime())) {
            throw new IllegalStateException("이미 종료된 프로모션입니다.");
        }

        // 재고 차감
        promotionMapper.decreaseStock(promotionId);

        // 구매내역 저장
        UserProduct userProduct = new UserProduct(
                null,
                currentUser.getId(),
                promotion.getProductId(),
                now
        );
        userProductMapper.saveUserProducts(userProduct);
        System.out.println("구매가 완료되었습니다.");

        return userProduct;
    }
}
