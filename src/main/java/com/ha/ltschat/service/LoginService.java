package com.ha.ltschat.service;

import com.ha.ltschat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserService userService;
    public User login(String uuid, String password) {
        User user = userService.findByUuid(uuid);
        if (user == null || !user.getPassword().equals(password)) {

            return null;
        } else {

            return user;
        }
    }
}
