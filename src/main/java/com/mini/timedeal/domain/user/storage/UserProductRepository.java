package com.mini.timedeal.domain.user.storage;

import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.domain.user.mapper.UserProductMapper;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.model.UserProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProductRepository implements UserProductMapper {

    @Override
    public List<UserProduct> getUserProducts(Long userId) {
        return List.of();
    }
}
