package com.mini.timedeal.domain.user.storage;

import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements UserMapper {

    private final Map<Long, User> users = new HashMap<>();

    public UserRepository() {
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {

        User name = users.get(username);
        User pw = users.get(password);

        for(Map.Entry<Long, User> entry : users.entrySet()) {
            if(name.equals(entry.getValue()) && pw.equals(pw)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
