package com.mini.timedeal.domain.user.service;

import com.mini.timedeal.domain.user.dto.UserProductDTO;
import com.mini.timedeal.domain.user.mapper.UserProductMapper;
import com.mini.timedeal.domain.user.model.UserProduct;

import java.util.List;

public class UserProductService {

    private final UserProductMapper userProductMapper;

    public UserProductService(UserProductMapper userProductMapper) {
        this.userProductMapper = userProductMapper;
    }

    /*
    * 유저가 구매한 상품들 불러오기
    * */
    public List<UserProductDTO> userProductList(Long userId) {

        return userProductMapper.getUserProducts(userId);
    }
}
