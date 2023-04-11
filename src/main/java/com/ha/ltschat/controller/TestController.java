package com.ha.ltschat.controller;


import com.ha.ltschat.model.User;
import com.ha.ltschat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.ha.ltschat.util.Md5Util.encode;
import static com.ha.ltschat.util.UuidUtil.generateUuid;

@RestController
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping(value="test",method= RequestMethod.GET)
    //@GetMapping
    //?value1=&value2=
    //@RequestParam -> must (value="")
    //post postman
    //bean entity
    //json @RequestBody
    //* ** ?
    public String test(String value1){
        return "Test success!?" + value1;
    }

    @PutMapping("upload")
    public String up(String nickname, MultipartFile photo, HttpServletRequest request) throws IOException{
    //photo.getOriginalFilename
    //photo.getContentType
    //String path = request.getServletContext().getRealPath("/upload/")
        return "Up test";
    }
    public void save(MultipartFile photo,String path) throws IOException{
        File dir=new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
        File file=new File(path+photo.getOriginalFilename());
        photo.transferTo(file);
    }
    //preHandle,postHandle,afterCompletion
    //{id} @PathVariable
    @GetMapping("generate")
    public String generate(){
        return "\'"+generateUuid(10)+"\'";
    }
    @GetMapping("password")
    public String password(){
        return encode("123456");
    }
    @GetMapping("allusers")
    public List<User> allusers(){
        return userService.getUsers();
    }
}
