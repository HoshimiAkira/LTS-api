package com.ha.ltschat.model;

public class Msg {
    public String title;
    private String uuid;
    private String previous;
    private String receive;
    private String send;
    private int urgency;
    private String major;
    private String content;
    private String date;
    private String icon;
    private String userName;
    private String course;
    private String status;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "title='" + title + '\'' +
                ", uuid='" + uuid + '\'' +
                ", previous='" + previous + '\'' +
                ", receive='" + receive + '\'' +
                ", send='" + send + '\'' +
                ", urgency=" + urgency +
                ", major='" + major + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", icon='" + icon + '\'' +
                ", userName='" + userName + '\'' +
                ", course='" + course + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
