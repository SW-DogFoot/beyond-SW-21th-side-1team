package com.mini.timedeal.domain.user.service;

import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.model.User;

import java.util.Scanner;

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
    public void orderPromotion(Promotion promotion) {

    }
}
