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
    public List<User> getStudentsByName(String name){
        return userMapper.getStudentsByName(name);
    }
    public List<User> getTeachersByName(String name){
        return userMapper.getTeachersByName(name);
    }
    public User findByUuid(String uuid){return userMapper.findByUuid(uuid);}
    public List<User> getStudentsByCourseUuid(String uuid){return userMapper.getStudentsByCourseUuid(uuid);}
    public List<User> getTeachersByCourseUuid(String uuid){return userMapper.getTeachersByCourseUuid(uuid);}
    public int getTeacherNum(String cUuid){
        return userMapper.getTeacherNum(cUuid);
    }
    public String deleteStudentByUuid(String cUuid,String sUuid){
        String message=null;

            int num=userMapper.deleteStudentByUuid(cUuid,sUuid);
            if(num<=0){
                message="Delete fail! Nothing be deleted.";
            }else{
                message="Delete success";
            }


        return message;
    }
    public String deleteTeacherByUuid(String cUuid,String tUuid){
        String message=null;

            int num=userMapper.deleteTeacherByUuid(cUuid,tUuid);
            if(num<=0){
                message="Delete fail! Nothing be deleted.";
            }else{
                message="Delete success";
            }


        return message;
    }
    public String addStudents(List<String> uuidList,String courseUuid){
        String message=null;

            userMapper.addStudents(uuidList,courseUuid);
            message="Add success";


        return message;
    }
    public String addTeachers(List<String> uuidList,String courseUuid){
        String message=null;

            userMapper.addTeachers(uuidList,courseUuid);
            message="Add success";


        return message;
    }

    public void deleteStudentsInCourse(String uuid){
        userMapper.deleteStudentsInCourse(uuid);
    }
    public void deleteTeachersInCourse(String uuid){
        userMapper.deleteTeachersInCourse(uuid);
    }

    public String updatePassword(String uuid,String password){
        String message=null;
        int num=userMapper.updatePassword(uuid,password);
        if(num<=0){
            message="Update fail! Nothing be updated.";
        }else{
            message="Update success";
        }

        return message;
    }
    public String updateIcon(String uuid,String icon){
        String message=null;
        int num=userMapper.updateIcon(uuid,icon);
        if(num<=0){
            message="Update fail! Nothing be updated.";
        }else{
            message="Update success";
        }
        return message;
    }
}
