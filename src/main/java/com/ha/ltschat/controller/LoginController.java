package com.ha.ltschat.controller;

import com.ha.ltschat.model.User;
import com.ha.ltschat.service.LoginService;
import com.ha.ltschat.service.UserService;
import com.ha.ltschat.util.Md5Util;
import com.ha.ltschat.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


import com.ha.ltschat.util.JwtUtil;
@RestController
public class LoginController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    Md5Util md5Util;
    @Autowired
    UuidUtil uuidUtil;

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam("uuid") String uuid, @RequestParam("password") String password) {
        password= md5Util.encode(password);
        User user = userService.findByUuid(uuid);
        if (user == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Non-existent user!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } else {
            user = loginService.login(uuid, password);
            if (user == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Wrong password!");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            } else {
                String type = "student";
                if (user.getType() == 1) {
                    type = "teacher";
                }
                Map<String, Object> response = new HashMap<>();
                response.put("token", jwtUtil.generateJwtToken(user.getUserName(), type));
                response.put("username", user.getUserName());
                response.put("type", type);
                response.put("major", user.getMajor());
                response.put("icon",user.getIcon());
                response.put("uuid", user.getUuid());
                return ResponseEntity.ok(response);
            }
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestParam("userName") String userName, @RequestParam("password") String password,
                                                        @RequestParam("major") String major,@RequestParam("inviteCode") String inviteCode) {
        String message=null;
        try {
            password = md5Util.encode(password);
            String uuid = uuidUtil.generateUuid(10);
            int type = 0;
            if (inviteCode.equals("aaa")) {
                major = "Teacher";
                type = 1;
            }
            String icon = "default.png";
            message=loginService.register(uuid, userName, password, type, major, icon);
        }catch (Exception e){
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
