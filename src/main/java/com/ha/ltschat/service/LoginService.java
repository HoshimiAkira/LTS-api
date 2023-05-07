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
    public String register(String uuid,String userName,String password,int type,String major,String icon){
        String message=null;
        userService.addUser(uuid,userName,password,type,major,icon);
        message="Sign up success,your id is "+uuid;
        return message;
    }
}
