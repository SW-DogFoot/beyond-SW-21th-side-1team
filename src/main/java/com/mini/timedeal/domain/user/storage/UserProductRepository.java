package com.mini.timedeal.domain.user.storage;

import com.mini.timedeal.domain.user.mapper.UserProductMapper;
import com.mini.timedeal.domain.user.model.UserProduct;

import java.util.ArrayList;
import java.util.List;

public class UserProductRepository implements UserProductMapper {

    private final List<UserProduct> userProducts = new ArrayList<>();

    public UserProductRepository() {
    }

    @Override
    public void saveUserProducts(UserProduct userProduct) {

        userProducts.add(userProduct);
    }

    @Override
    public List<UserProduct> getUserProducts(Long userId) {

        List<UserProduct> productList = new ArrayList<>();

        for (UserProduct up : userProducts) {
            if (up.getUserId().equals(userId)) {
                productList.add(up);
            }
        }

        return productList;
    }
}
