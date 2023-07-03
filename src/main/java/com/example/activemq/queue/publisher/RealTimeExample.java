package com.example.activemq.queue.publisher;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

import javax.jms.*;
import java.util.Date;

public class RealTimeExample {

    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        try {
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("demo");
            JSONObject json = new JSONObject();
            json.put("From Date", "03-July-2023");
            json.put("To Date", new Date());
            json.put("Email", "eganpham.99@gmail.com");
            json.put("Query", "SELECT * FROM data");

            TextMessage textMessage = session.createTextMessage(json.toString());
            MessageProducer producer = session.createProducer(destination);
            producer.send(textMessage);
            session.close();
            connection.close();

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
