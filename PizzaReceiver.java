/**
 * Project: Lab4 Pizza Shop
 * Purpose Details: Receive pizza object serialized as JSON via RabbitMQ and print details.
 * Course: IST 242
 * Author: Aayudh Nandiwdekar
 * Date Developed: 10/21/2024
 * Last Date Changed:
 * Revision:
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

public class PizzaReceiver {
    private static final String QUEUE_NAME = "pizzaQueue";

    public static void main(String[] args) throws Exception {
        // Setup RabbitMQ connection and channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Ensure RabbitMQ is running locally
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages...");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String pizzaJson = new String(delivery.getBody(), "UTF-8");

                // Deserialize JSON back to Pizza object
                ObjectMapper objectMapper = new ObjectMapper();
                Pizza pizza = objectMapper.readValue(pizzaJson, Pizza.class);

                // Print the pizza details
                System.out.println(" [x] Received: ");
                System.out.println(" Size: " + pizza.getSize());
                System.out.println(" Crust: " + pizza.getCrust());
                System.out.println(" Toppings: " + String.join(", ", pizza.getToppings()));
                System.out.println(" Price: $" + pizza.getPrice());
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        }
    }
}
