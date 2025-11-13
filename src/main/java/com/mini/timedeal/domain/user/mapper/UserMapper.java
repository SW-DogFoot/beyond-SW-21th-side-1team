package com.mini.timedeal.domain.user.mapper;

import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.model.UserProduct;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findByUsernameAndPassword(String username, String password);
    void addUser(User user);
}
