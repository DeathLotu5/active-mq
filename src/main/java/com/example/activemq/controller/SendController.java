package com.example.activemq.controller;

import com.example.activemq.controller.request.CredentialMq;
import com.example.activemq.controller.request.TestRequest;
import com.example.activemq.utils.ActiveMQUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@RestController
@RequestMapping("/api/v1")
public class SendController {

//    @Autowired
//    JmsTemplate jmsTemplate;

    @GetMapping("/send")
    public String send(@RequestBody TestRequest message, @RequestParam String username, @RequestParam String password, @RequestParam String uri) {
        ActiveMQUtil.username = username;
        ActiveMQUtil.password = password;
        ActiveMQUtil.mqUri = uri;
        ActiveMQUtil.createSession();

        ActiveMQUtil.sendMassage(message);
        return "Success!";
    }

}
