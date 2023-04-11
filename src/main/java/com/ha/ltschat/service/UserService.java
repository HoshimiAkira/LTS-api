package com.ha.ltschat.service;

import com.ha.ltschat.mapper.UserMapper;
import com.ha.ltschat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers() {
        return userMapper.getUsers();
    }
    public User findByUuid(String uuid){return userMapper.findByUuid(uuid);}
}
