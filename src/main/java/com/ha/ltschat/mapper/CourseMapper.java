package com.ha.ltschat.mapper;

import com.ha.ltschat.model.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CourseMapper {
    List<Course> getCoursesByTeacherUuid(@Param("uuid") String uuid);
    int deleteCourseByUuid(@Param("uuid") String uuid);
    int updateInvite(@Param("invite") String invite,@Param("uuid") String uuid);
    void addCourse(@Param("name") String name,@Param("uuid") String uuid);
}
