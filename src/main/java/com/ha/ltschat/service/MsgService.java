package com.ha.ltschat.service;

import com.ha.ltschat.mapper.MsgMapper;
import com.ha.ltschat.model.Msg;
import com.ha.ltschat.util.DateUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ha.ltschat.util.UuidUtil.generateUuid;

@Service
public class MsgService {
    @Autowired
    MsgMapper msgMapper;
    @Autowired
    DateUtil dateUtil;
    public String addMsg(String title,String uuid,String previous,String receive,
               String send,int urgency,String major,String content,String course){
        String message=null;
        if(previous!=null){
            Msg msg=msgMapper.getMsgByUuid(previous);
            if(msg!=null&&!send.equals(msg.getSend()))
            msgMapper.reply(previous);
        }
        String date=dateUtil.getCurrentDateTimeAsString();
        String status="not";
        msgMapper.addMsg(title,uuid,previous,receive,send,urgency,major,content,date,status,course);
        message="Add success";
        return message;
    }
    public List<Msg> getUnreadMsgs(String uuid){
        return msgMapper.getUnreadMsgs(uuid);
    }
    public List<Msg> getNotice(String uuid){
        return msgMapper.getNotice(uuid);
    }
    public List<Msg> getQuestions(String uuid){
        return msgMapper.getQuestions(uuid);
    }
    public List<Msg> getQuestion(String uuid){
        return msgMapper.getQuestion(uuid);
    }
    public List<Msg> getAnswers(String uuid){
        return msgMapper.getAnswers(uuid);
    }
    public List<Msg> getReadMsgs(String uuid){
        return msgMapper.getReadMsgs(uuid);
    }
    public List<Msg> getSentMsgs(String uuid){
        return msgMapper.getSentMsgs(uuid);
    }
    public void addMsgIcon(String uuid,String icon){
        msgMapper.addMsgIcon(uuid,icon);
    }
    public Msg getMsgByUuid(String uuid){
        return msgMapper.getMsgByUuid(uuid);
    }
    public int checkUnreadNum(String uuid){
        return msgMapper.checkUnreadNum(uuid);
    }
    public String endMsg(String uuid){
        String message=null;
        int num= msgMapper.endMsg(uuid);
        if(num<=0){
            message="Update fail! Nothing be updated.";
        }else{
            message="Update success";
        }
        return message;
    }
    public String deleteMsg(String uuid){
        String message=null;
        int num= msgMapper.deleteMsg(uuid);
        msgMapper.cleanPrevious(uuid);
        if(num<=0){
            message="Delete fail! Nothing be updated.";
        }else{
            message="Delete success";
        }
        return message;
    }
    public String saveMsg(String uuid, String previous, String answer,String course){
        String message=null;
        msgMapper.saveMsg(uuid,previous,answer,course);
        message="Add success";
        return message;
    }
    @Scheduled(cron = "0 0 0 9 1 ?")
    public void cleanupOldData() {
        msgMapper.deleteOld();
        System.out.println("Clean old data.");
    }
}
