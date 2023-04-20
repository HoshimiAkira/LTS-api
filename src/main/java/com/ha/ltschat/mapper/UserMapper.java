package com.ha.ltschat.mapper;

import com.ha.ltschat.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserMapper {
    List<User> getUsers();
    List<User> getStudentsByName(@Param("name") String name);
    User findByUuid(@Param("uuid") String uuid);
    List<User> getStudentsByCourseUuid(@Param("uuid") String uuid);
    List<User> getTeachersByCourseUuid(@Param("uuid") String uuid);
    int deleteStudentByUuid(@Param("cUuid") String cUuid,@Param("sUuid") String sUuid);
    void addStudents(@Param("uuidList") List<String> uuidList, @Param("courseUuid") String courseUuid);
}
