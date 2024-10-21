/**
 * Project: Lab4 Pizza Shop
 * Purpose Details: Send pizza object serialized as JSON via RabbitMQ.
 * Course: IST 242
 * Author: Aayudh Nandiwdekar
 * Date Developed: 10/21/2024
 * Last Date Changed:
 * Revision:
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PizzaSender {
    private static final String QUEUE_NAME = "pizzaQueue";

    public static void main(String[] args) throws Exception {
        // Create a new Pizza object
        Pizza pizza = new Pizza("Large", "Thin Crust", new String[]{"Cheese", "Pepperoni"}, 15.99);

        // Serialize pizza object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String pizzaJson = objectMapper.writeValueAsString(pizza);

        // Setup RabbitMQ connection and channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Ensure RabbitMQ is running locally
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Send message
            channel.basicPublish("", QUEUE_NAME, null, pizzaJson.getBytes());
            System.out.println(" [x] Sent: " + pizzaJson);
        }
    }
}
