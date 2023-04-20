package com.ha.ltschat.controller;

import com.ha.ltschat.model.Course;
import com.ha.ltschat.model.User;
import com.ha.ltschat.service.CourseService;
import com.ha.ltschat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping("/admin/courses/students")
    public ResponseEntity<List<User>> getStudentsByName(@RequestParam("name") String name) {
        List<User> users=userService.getStudentsByName(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/admin/courses")
    public ResponseEntity<List<Course>> getGroups(@RequestParam("uuid") String uuid) {
        List<Course> courses=courseService.getCoursesByTeacherUuid(uuid);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/admin/courses/{uuid}")
    public ResponseEntity<List<Map<String, Object>>> getGroupInfo(@PathVariable("uuid") String uuid) {
        List<User> students=userService.getStudentsByCourseUuid(uuid);
        List<User> teachers=userService.getTeachersByCourseUuid(uuid);
        Map<String, Object> response = new HashMap<>();
        response.put("students",students);
        response.put("teachers",teachers);
        List<Map<String, Object>> responseList=new ArrayList<>();
        responseList.add(response);
        return ResponseEntity.ok(responseList);
    }
    @PostMapping("/admin/courses/students")
    public ResponseEntity<Map<String, Object>> addStudents(@RequestParam("uuidList") List<String> uuidList,@RequestParam("courseUuid") String courseUuid) {
        String message=userService.addStudents(uuidList,courseUuid);
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/admin/courses/{cUuid}/{sUuid}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable("cUuid") String cUuid,@PathVariable("sUuid") String sUuid) {
        String message=userService.deleteStudentByUuid(cUuid,sUuid);
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

}
