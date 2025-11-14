package com.mini.timedeal.domain.user.storage;

import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements UserMapper {

    private final Map<String, User> users = new HashMap<>();

    private User currentUser = new User();

    public UserRepository() {
    }

    @Override
    public void addUser(User user) {

        users.put(user.getUsername(), user);
    }

    @Override
    public User findByUsernameAndPassword(Map<String, String> user) {
//
//        currentUser = users.get(username);
//
//        if (currentUser != null && currentUser.getPassword().equals(password)) {
//            return currentUser;
//        }
        return null;
    }
}
