package com.mini.timedeal.domain.user.service;

import com.mini.timedeal.domain.promotion.mapper.PromotionMapper;
import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.model.UserProduct;

import java.util.List;

public class UserService {

    private final UserMapper userMapper;
    private final PromotionMapper promotionMapper;

    public UserService(UserMapper userMapper,  PromotionMapper promotionMapper) {
        this.userMapper = userMapper;
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
    public List<UserProduct> order(Long promotionId) {

        // 유저 조회

        // 프로모션 아이디 확인

        // 프로모션 수정 (감소)

        return userMapper.orderPromotion(promotionId);
    }
}
