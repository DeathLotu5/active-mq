package com.example.activemq.utils;

import com.example.activemq.controller.request.CredentialMq;
import com.example.activemq.controller.request.TestRequest;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

import javax.jms.*;

public class ActiveMQUtil {
    public static Connection connection;
    public static Session session;

    public static String topicName = "";

    public static String username;
    public static String password;
    public static String mqUri;

    public static void createSession() {
        ConnectionFactory factory = new ActiveMQConnectionFactory(username, password, mqUri);
        try {

            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            System.out.println("Create session success with username is :" + username);

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMassage(TestRequest message) {
        try {
            // Defining the topic will be received a message
            Destination mqTopic = session.createQueue(message.getTopicName());
            MessageProducer producer = session.createProducer(mqTopic);

            // parse Class to Json
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("FullName", message.getName());
            jsonMessage.put("Email", message.getEmail());
            jsonMessage.put("Created Date", message.getNow());

            // Send message
            TextMessage textMessage = session.createTextMessage(jsonMessage.toString());
            producer.send(textMessage);

            session.close();
            System.out.println("Send the message success to Topic's name :" + topicName);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
