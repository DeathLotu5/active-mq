package com.example.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        try {

            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("Demo-Topic");
            MessageProducer producer = session.createProducer(destination);

            TextMessage message = session.createTextMessage("Message For Topic!");
            producer.send(message);
            System.out.println("Message publisher to topic!");

            session.close();
            connection.close();

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
