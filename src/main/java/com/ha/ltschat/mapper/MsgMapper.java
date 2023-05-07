package com.ha.ltschat.mapper;

import com.ha.ltschat.model.Msg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MsgMapper {
    void addMsg(@Param("title") String title,@Param("uuid") String uuid,@Param("previous") String previous,@Param("receive") String receive,
                @Param("send") String send,@Param("urgency") int urgency,
                @Param("major") String major,@Param("content") String content,@Param("date") String date,
                @Param("status") String status,@Param("course") String course
    );
    void addMsgIcon(@Param("uuid") String uuid,@Param("icon") String icon);
    List<Msg> getUnreadMsgs(@Param("uuid") String uuid);
    List<Msg> getNotice(@Param("uuid") String uuid);
    List<Msg> getReadMsgs(@Param("uuid") String uuid);
    List<Msg> getSentMsgs(@Param("uuid") String uuid);
    void reply(@Param("uuid") String uuid);
    Msg getMsgByUuid(@Param("uuid") String uuid);
    int checkUnreadNum(@Param("uuid") String uuid);
    int endMsg(@Param("uuid") String uuid);
    int deleteMsg(@Param("uuid") String uuid);
    void cleanPrevious(@Param("uuid") String uuid);
    void saveMsg(@Param("uuid") String uuid,@Param("previous") String previous,@Param("answer") String answer,@Param("course") String course);
    List<Msg> getQuestions(@Param("uuid") String uuid);
    List<Msg> getAnswers(@Param("uuid") String uuid);
    List<Msg> getQuestion(@Param("uuid") String uuid);
    void deleteOld();
}
