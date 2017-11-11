package org.niklas.rabbitmqtutorial;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Publisher {

    public static void main(String[] args)
            throws NoSuchAlgorithmException, KeyManagementException,
            URISyntaxException, IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(Common.URI);
        factory.setConnectionTimeout(300000);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Common.QUEUE_NAME, true, false, false,
                null);

        String message = "Hello world!";

        channel.basicPublish("", Common.QUEUE_NAME, null, message.getBytes());
        System.out.print("Sent message " + message);

        channel.close();
        connection.close();
    }

}
