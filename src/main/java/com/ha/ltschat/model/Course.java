package com.ha.ltschat.model;

public class Course {
    private String uuid;
    private String courseName;
    private String invite;
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    @Override
    public String toString() {
        return "Course{" +
                "uuid='" + uuid + '\'' +
                ", courseName='" + courseName + '\'' +
                ", invite='" + invite + '\'' +
                '}';
    }
}
