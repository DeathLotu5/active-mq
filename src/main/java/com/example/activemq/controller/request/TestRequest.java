package com.example.activemq.controller.request;

import java.time.LocalDateTime;

public class TestRequest {

    private String name;

    private String email;

    private String topicName;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    private LocalDateTime now = LocalDateTime.now();


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getNow() {
        return now;
    }
}
