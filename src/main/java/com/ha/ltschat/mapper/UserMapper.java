package com.ha.ltschat.mapper;

import com.ha.ltschat.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserMapper {
    List<User> getUsers();
    List<User> getStudentsByName(@Param("name") String name);
    List<User> getTeachersByName(@Param("name") String name);
    User findByUuid(@Param("uuid") String uuid);
    List<User> getStudentsByCourseUuid(@Param("uuid") String uuid);
    List<User> getTeachersByCourseUuid(@Param("uuid") String uuid);
    void addStudents(@Param("uuidList") List<String> uuidList, @Param("courseUuid") String courseUuid);
    void addTeachers(@Param("uuidList") List<String> uuidList, @Param("courseUuid") String courseUuid);
    int deleteStudentByUuid(@Param("cUuid") String cUuid,@Param("sUuid") String sUuid);
    int deleteTeacherByUuid(@Param("cUuid") String cUuid,@Param("tUuid") String tUuid);
    int getTeacherNum(@Param("cUuid") String cUuid);

    int deleteStudentsInCourse(@Param("uuid") String uuid);
    int deleteTeachersInCourse(@Param("uuid") String uuid);

    int updatePassword(@Param("uuid") String uuid,@Param("password") String password);

    int updateIcon(@Param("uuid") String uuid,@Param("icon") String icon);
    User checkTeacher(@Param("uuid")String uuid,@Param("course")String course);
    User checkStudent(@Param("uuid")String uuid,@Param("uuid")String course);
    void addUser(@Param("uuid")String uuid,@Param("userName")String userName,@Param("password")String password,
                 @Param("type")int type,@Param("major")String major,@Param("icon")String icon);
    int updateInfo(@Param("uuid") String uuid,@Param("major") String major,@Param("userName") String userName);
}
