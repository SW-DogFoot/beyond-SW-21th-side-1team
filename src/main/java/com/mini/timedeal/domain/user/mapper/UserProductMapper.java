package com.mini.timedeal.domain.user.mapper;

import com.mini.timedeal.domain.user.model.UserProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProductMapper {
    List<UserProduct> getUserProducts(Long userId);
}
