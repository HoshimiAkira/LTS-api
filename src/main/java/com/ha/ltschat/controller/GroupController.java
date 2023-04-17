package com.ha.ltschat.controller;

import com.ha.ltschat.model.Course;
import com.ha.ltschat.model.User;
import com.ha.ltschat.service.CourseService;
import com.ha.ltschat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GroupController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users=userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/admin/courses")
    public ResponseEntity<List<Course>> getGroups(@RequestParam("uuid") String uuid) {
        List<Course> courses=courseService.getCoursesByTeacherUuid(uuid);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/admin/courses/{uuid}")
    public ResponseEntity<Map<String, Object>> getGroupInfo(@PathVariable("uuid") String uuid) {
        List<User> students=courseService.getStudentsByCourseUuid(uuid);
        Map<String, Object> response = new HashMap<>();
        response.put("students", students);
        return ResponseEntity.ok(response);
    }

}
