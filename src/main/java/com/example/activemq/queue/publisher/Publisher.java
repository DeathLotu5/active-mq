package com.example.activemq.queue.publisher;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publisher {

    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        try {
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //        Tạo hoặc chỉ định queue sẽ nhận message
            Destination destination = session.createQueue("demo");

//        Tạo message
            TextMessage textMessage = session.createTextMessage("First message");


//        Tạo producer và gửi message
            MessageProducer producer = session.createProducer(destination);
            producer.send(textMessage);
            System.out.println("Message Published!");

            session.close();
            connection.close();

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
