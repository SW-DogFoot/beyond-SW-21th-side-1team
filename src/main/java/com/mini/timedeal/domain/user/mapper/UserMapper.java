package com.mini.timedeal.domain.user.mapper;

import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.model.UserProduct;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {
    User findByUsernameAndPassword(Map<String, String> user);
    void addUser(User user);
}
