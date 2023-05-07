package com.ha.ltschat.controller;

import com.ha.ltschat.model.Course;
import com.ha.ltschat.model.Msg;
import com.ha.ltschat.service.MsgService;
import com.ha.ltschat.util.DateUtil;
import com.ha.ltschat.util.RichTextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ha.ltschat.util.UuidUtil.generateUuid;

@RestController
public class MsgController {
    @Autowired
    RichTextUtils richTextUtils;
    @Autowired
    MsgService msgService;
    @PostMapping("/msg")
    public ResponseEntity<Map<String, Object>> addMsg(@RequestParam("previous") String previous, @RequestParam("send") String send,
                                                      @RequestParam("receive") String receive,@RequestParam("urgency") int urgency,
                                                      @RequestParam("major") String major,@RequestParam("content") String content,
                                                      @RequestParam("title") String title,@RequestParam("course") String course
                                                      ){
        String message = null;
        try {
            String uuid=generateUuid(10);
            String data=richTextUtils.extractAndSaveImages(content,uuid);
            message=msgService.addMsg(title,uuid,previous,receive,send,urgency,major,data,course);
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
    @GetMapping("/msg/unread")
    public ResponseEntity<List<Msg>> getUnreadMsgs(@RequestParam("uuid") String uuid) {
        List<Msg> msgs=msgService.getUnreadMsgs(uuid);
        return ResponseEntity.ok(msgs);
    }
    @GetMapping("/msg/read")
    public ResponseEntity<List<Msg>> getReadMsgs(@RequestParam("uuid") String uuid) {
        List<Msg> msgs=msgService.getReadMsgs(uuid);
        return ResponseEntity.ok(msgs);
    }
    @GetMapping("/msg/sent")
    public ResponseEntity<List<Msg>> getSentMsgs(@RequestParam("uuid") String uuid) {
        List<Msg> msgs=msgService.getSentMsgs(uuid);
        return ResponseEntity.ok(msgs);
    }
    @GetMapping("/msg/{uuid}")
    public ResponseEntity<Msg> getMsgByUuid(@PathVariable("uuid") String uuid){
        Msg msg= msgService.getMsgByUuid(uuid);
        return ResponseEntity.ok(msg);
    }
    @PutMapping("/msg/{uuid}")
    public ResponseEntity<Map<String, Object>> endMsg(@PathVariable("uuid") String uuid) {
        String message=null;
        try {
            message= msgService.endMsg(uuid);
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
    @DeleteMapping("/msg/{uuid}")
    public ResponseEntity<Map<String, Object>> deleteMsg(@PathVariable("uuid") String uuid) {
        String message=null;
        try {
            message= msgService.deleteMsg(uuid);
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
    @PostMapping("/msg/save")
    public ResponseEntity<Map<String, Object>> saveMsg(@RequestParam("previous") String previous,
                                                       @RequestParam("course") String course,
                                                       @RequestParam("uuid") String answer
    ){
        String message = null;
        try {
            String uuid=generateUuid(10);
            message=msgService.saveMsg(uuid,previous,answer,course);
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
    @GetMapping("/msg/bot/{uuid}")
    public ResponseEntity<List<Msg>> getBotInfo(@PathVariable("uuid") String uuid){
        List<Msg> answers =msgService.getAnswers(uuid);
        return ResponseEntity.ok(answers);
    }
    @GetMapping("/msg/notice/{uuid}")
    public ResponseEntity<List<Msg>> getNotice(@PathVariable("uuid") String uuid){
        List<Msg> notices =msgService.getNotice(uuid);
        return ResponseEntity.ok(notices);
    }
    @GetMapping("/msg/bot/{uuid}/qa")
    public ResponseEntity<List<Msg>> getBotQA(@PathVariable("uuid") String uuid){
        List<Msg> question = msgService.getQuestion(uuid);
        return ResponseEntity.ok(question);
    }
}
