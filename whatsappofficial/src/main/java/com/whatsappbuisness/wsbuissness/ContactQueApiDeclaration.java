package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactQueApiDeclaration {
 

    @Bean
    Queue Contact_Que_Apiqueue() {

        System.out.println("Declaring Que {}"+QueName.Contact_Que_Api.queName);
        // Creating a queue.
        return new Queue(QueName.Contact_Que_Api.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange Contact_Que_ApiExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.Contact_Que_Api.topic);
    }
 
    @Bean
    Binding Contact_Que_Apibinding(final Queue Contact_Que_Apiqueue, final TopicExchange Contact_Que_ApiExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(Contact_Que_Apiqueue).to(Contact_Que_ApiExchange).with(QueName.Contact_Que_Api.routing);
    }
}
