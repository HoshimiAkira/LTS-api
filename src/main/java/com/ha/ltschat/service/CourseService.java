package com.ha.ltschat.service;


import com.ha.ltschat.mapper.CourseMapper;
import com.ha.ltschat.model.Course;
import com.ha.ltschat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    UserService userService;

    public List<Course> getCoursesByTeacherUuid(String uuid){
        return courseMapper.getCoursesByTeacherUuid(uuid);
    }
    public List<User> getStudentsByCourseUuid(String uuid){
        return userService.getStudentsByCourseUuid(uuid);
    }
}
