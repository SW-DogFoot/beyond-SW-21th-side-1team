package com.mini.timedeal.domain.user.service;

import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.model.UserProduct;

public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
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
    public UserProduct order(Long promotionId) {

        return userMapper.orderPromotion(promotionId);
    }
}
