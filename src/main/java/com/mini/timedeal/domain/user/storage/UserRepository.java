package com.mini.timedeal.domain.user.storage;

import com.mini.timedeal.domain.promotion.mapper.PromotionMapper;
import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.domain.promotion.storage.PromotionRepository;
import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.mapper.UserProductMapper;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.model.UserProduct;
import com.mini.timedeal.enums.UserRole;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserRepository implements UserMapper {

    private final Map<String, User> users = new HashMap<>();
    private final PromotionRepository pr =  new PromotionRepository();
    private final UserProductRepository ur = new UserProductRepository();

    private User currentUser = new User();
    private Long id = 0L;

    public UserRepository() {
        users.put("qwer", new User(1L, "qwer", "1234", UserRole.USER));
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {

        currentUser = users.get(username);

        if (currentUser != null && currentUser.getPassword().equals(password)) {
            return currentUser;
        }
        return null;
    }

    @Override
    public UserProduct orderPromotion(Long promotionId) {

        // 프로모션 조회/검증
        Promotion promotion = pr.findById(promotionId);
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

        // 구매내역 저장
        UserProduct userProduct = new UserProduct(
                id++,
                currentUser.getId(),
                promotion.getProductId(),
                now
        );
        ur.saveUserProducts(userProduct);

        return userProduct;
    }
}
