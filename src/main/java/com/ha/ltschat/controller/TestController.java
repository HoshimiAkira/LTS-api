package com.ha.ltschat.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class TestController {

    @RequestMapping(value="/test",method= RequestMethod.GET)
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

}
