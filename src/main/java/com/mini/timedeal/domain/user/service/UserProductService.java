package com.mini.timedeal.domain.user.service;

import com.mini.timedeal.domain.user.mapper.UserProductMapper;
import com.mini.timedeal.domain.user.model.UserProduct;

public class UserProductService {

    private final UserProductMapper userProductMapper;

    public UserProductService(UserProductMapper userProductMapper) {
        this.userProductMapper = userProductMapper;
    }

    /*
    * 유저가 구매한 상품들 불러오기
    * */
    public UserProduct getUserProducts(Long userId) {

        userProductMapper.getUserProducts(userId);
        return null;
    }
}
