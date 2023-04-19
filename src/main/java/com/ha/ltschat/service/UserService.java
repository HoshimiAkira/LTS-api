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
    public List<User> getStudentsByCourseUuid(String uuid){return userMapper.getStudentsByCourseUuid(uuid);}
    public List<User> getTeachersByCourseUuid(String uuid){return userMapper.getTeachersByCourseUuid(uuid);}
    public String deleteStudentByUuid(String cUuid,String sUuid){
        String message=null;
        try {
            int num=userMapper.deleteStudentByUuid(cUuid,sUuid);
            if(num<=0){
                message="Delete fail! Nothing be deleted.";
            }else{
                message="Delete success";
            }
        }catch(Exception e){
            message="Delete fail! Special error";
            return message;
        }

        return message;
    }
}
