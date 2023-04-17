package com.ha.ltschat.controller;

import com.ha.ltschat.model.Course;
import com.ha.ltschat.model.User;
import com.ha.ltschat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users=userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/admin/course")
    public ResponseEntity<List<Course>> getGroups() {
        List<Course> courses=null;
        return ResponseEntity.ok(courses);
    }


}
