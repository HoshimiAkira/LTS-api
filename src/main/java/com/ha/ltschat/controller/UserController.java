package com.ha.ltschat.controller;

import com.ha.ltschat.model.User;
import com.ha.ltschat.service.AzureBlobStorageService;
import com.ha.ltschat.service.LoginService;
import com.ha.ltschat.service.UserService;
import com.ha.ltschat.util.Md5Util;
import com.ha.ltschat.util.UuidUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {
    @Autowired
    private Md5Util md5Util;
    @Autowired
    private UserService userService;
    @Autowired
    private UuidUtil uuidUtil;
    @Autowired
    private AzureBlobStorageService azureBlobStorageService;
    @PutMapping("/user/password")
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody Map<String, String> data) {
        String message=null;
        String password = data.get("password");
        String uuid=data.get("uuid");
        String code=md5Util.encode(password);
        try {
            message= userService.updatePassword(uuid,code);
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
    @PostMapping("/user/icon")
    public ResponseEntity<Map<String, Object>> uploadIcon(@RequestParam("icon") MultipartFile file, @RequestParam("uuid") String uuid) {
        String message=null;
        try {
            User user= userService.findByUuid(uuid);
            azureBlobStorageService.deleteFile(user.getIcon());
            byte[] icon = file.getBytes();
            String originalFilename = file.getOriginalFilename();
            String extension = "." + FilenameUtils.getExtension(originalFilename);
            String iconId=uuidUtil.generateUuid(10);
            String newFilename = iconId + extension;
            azureBlobStorageService.uploadFile(newFilename, icon);
            String imageUrl = newFilename;
            message = userService.updateIcon(uuid, imageUrl);
            if(message!="Update success"){
                Map<String, Object> response = new HashMap<>();
                response.put("message", message);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
        catch(Exception e){
            message=e.toString();
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/user/group")
    public ResponseEntity<Map<String, Object>> addGroup(@RequestParam("uuid") String uuid,@RequestParam("course") String course,
                                                        @RequestParam("type") String type,@RequestParam("invite") String invite
    ) {
        String message=null;
        try {
           message=userService.addGroup(uuid,course,type,invite);
            if(message!="Add success"){
                Map<String, Object> response = new HashMap<>();
                response.put("message", message);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
        catch(Exception e){
            message=e.toString();
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/user/edit")
    public ResponseEntity<Map<String, Object>> editInfo(@RequestParam("uuid") String uuid,@RequestParam("major") String major,
                                                        @RequestParam("userName") String userName
    ) {
        String message=null;
        try {
            message=userService.editInfo(uuid,major,userName);
            if(message!="Add success"){
                Map<String, Object> response = new HashMap<>();
                response.put("message", message);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
        catch(Exception e){
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
