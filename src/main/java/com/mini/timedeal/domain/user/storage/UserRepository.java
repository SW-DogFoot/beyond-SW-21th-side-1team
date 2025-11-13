package com.mini.timedeal.domain.user.storage;

import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.enums.UserRole;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements UserMapper {

    private final Map<String, User> users = new HashMap<>();

    private User currentUser = new User();

    public UserRepository() {
        users.put("qwer", new User(1L, "qwer", "1234", UserRole.USER));
        users.put("asdf", new User(2L, "asdf", "1234", UserRole.ADMIN));
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {

        currentUser = users.get(username);

        if (currentUser != null && currentUser.getPassword().equals(password)) {
            return currentUser;
        }
        return null;
    }
}
