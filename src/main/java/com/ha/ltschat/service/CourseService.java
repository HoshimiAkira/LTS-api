package com.ha.ltschat.service;


import com.ha.ltschat.mapper.CourseMapper;
import com.ha.ltschat.model.Course;
import com.ha.ltschat.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ha.ltschat.util.UuidUtil.generateUuid;

@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    UserService userService;

    public List<Course> getCoursesByTeacherUuid(String uuid){
        return courseMapper.getCoursesByTeacherUuid(uuid);
    }
    public List<Course> getCoursesByStudentUuid(String uuid){
        return courseMapper.getCoursesByStudentUuid(uuid);
    }

    public String deleteCourseByUuid(String uuid){
        String message=null;
            int num=courseMapper.deleteCourseByUuid(uuid);
            if(num<=0){
                message="Delete fail! Nothing be deleted.";
            }else{
                userService.deleteTeachersInCourse(uuid);
                userService.deleteStudentsInCourse(uuid);
                message="Delete success";
            }
        return message;
    }
    public String updateInvite(String invite, String uuid){
        String message=null;
        int num=courseMapper.updateInvite(invite,uuid);
        if(num<=0){
            message="Update fail! Nothing be updated.";
        }else{
            message="Update success";
        }

        return message;
    }
    public String addCourse(String name,String uuid){
        String message=null;
        String cuuid=generateUuid(10);
        courseMapper.addCourse(name,cuuid);
        List<String> teacher=new ArrayList<>();
        teacher.add(uuid);
        userService.addTeachers(teacher,cuuid);
        message="Add success";
        return message;
    }

}
