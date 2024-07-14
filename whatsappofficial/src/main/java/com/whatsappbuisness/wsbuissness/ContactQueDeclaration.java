package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactQueDeclaration {
 

    @Bean
    Queue Contact_Que() {

        System.out.println("Declaring Que {}"+QueName.Contact_Que.queName);
        // Creating a queue.
        return new Queue(QueName.Contact_Que.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange Contact_QueExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.Contact_Que.topic);
    }
 
    @Bean
    Binding Contact_Quebinding(final Queue Contact_Que, final TopicExchange Contact_QueExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(Contact_Que).to(Contact_QueExchange).with(QueName.Contact_Que.routing);
    }
}
