package com.mini.timedeal.domain.user.mapper;

import com.mini.timedeal.domain.user.dto.UserProductDTO;
import com.mini.timedeal.domain.user.model.UserProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProductMapper {
    void saveUserProducts(UserProduct userProduct);
    List<UserProduct> getUserProducts(Long userId);
}
