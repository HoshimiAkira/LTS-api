package com.ha.ltschat.controller;

import com.ha.ltschat.config.MyHandler;
import com.ha.ltschat.model.Course;
import com.ha.ltschat.model.User;
import com.ha.ltschat.model.Watch;
import com.ha.ltschat.service.CourseService;
import com.ha.ltschat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class GroupController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private MyHandler myHandler;

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
    @GetMapping("/admin/courses/teachers")
    public ResponseEntity<List<User>> getTeachersByName(@RequestParam("name") String name) {
        List<User> users=userService.getTeachersByName(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/admin/courses")
    public ResponseEntity<List<Course>> getGroups(@RequestParam("uuid") String uuid) {
        User user= userService.findByUuid(uuid);
        List<Course> courses =new ArrayList<>();
        if(user.getType()==1) {
            courses = courseService.getCoursesByTeacherUuid(uuid);
        }else{
            courses = courseService.getCoursesByStudentUuid(uuid);
        }
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
        String message = null;
        try {
             message = userService.addStudents(uuidList, courseUuid);
        }catch(Exception e){
            message=e.toString();
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/admin/courses/teachers")
    public ResponseEntity<Map<String, Object>> addTeachers(@RequestParam("uuidList") List<String> uuidList,@RequestParam("courseUuid") String courseUuid) {
        String message = null;
        try {
            message = userService.addTeachers(uuidList, courseUuid);
        }catch(Exception e){
            message=e.toString();
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/admin/courses/{cUuid}/{uuid}")
    public ResponseEntity<Map<String, Object>> deleteUserInGroup(@PathVariable("cUuid") String cUuid,@PathVariable("uuid") String uuid) {
        String message=null;
        User user = userService.findByUuid(uuid);
        if(user==null){
            message="Delete Fail!No user.";
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            if (user.getType()==0){
                message=userService.deleteStudentByUuid(cUuid,uuid);
            }else{
                int teacherNum=userService.getTeacherNum(cUuid);
                if(teacherNum<=1){
                    message="Not allowed no teacher.";
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", message);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }else {
                    message = userService.deleteTeacherByUuid(cUuid, uuid);
                    if(message!="Delete success"){
                        Map<String, Object> response = new HashMap<>();
                        response.put("message", message);
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                    }
                }
            }
        }catch(Exception e){
            message=e.toString();
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/admin/courses/{uuid}")
    public ResponseEntity<Map<String, Object>> deleteGroup(@PathVariable("uuid") String uuid) {
        String message=null;
        try {
            message=courseService.deleteCourseByUuid(uuid);
            if(message!="Delete success"){
                Map<String, Object> response = new HashMap<>();
                response.put("message", message);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }catch(Exception e){
            message=e.toString();
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/admin/courses/{uuid}/invite")
    public ResponseEntity<Map<String, Object>> updateInvite(@PathVariable("uuid") String uuid, @RequestBody Map<String, String> data) {
        String message=null;
        String invite = data.get("invite");
        try {
            message=courseService.updateInvite(invite,uuid);
            if(message!="Update success"){
                Map<String, Object> response = new HashMap<>();
                response.put("message", message);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }catch(Exception e){
            message=e.toString();
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/admin/courses")
    public ResponseEntity<Map<String, Object>> addCourse(@RequestParam("name") String name,@RequestParam("uuid") String uuid) {
        String message=null;
        try {
            message=courseService.addCourse(name,uuid);
            if(message!="Add success"){
                Map<String, Object> response = new HashMap<>();
                response.put("message", message);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }catch(Exception e){
            message=e.toString();
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }


}
