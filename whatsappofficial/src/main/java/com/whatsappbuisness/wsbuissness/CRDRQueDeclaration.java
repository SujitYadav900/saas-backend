package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CRDRQueDeclaration {
 

    @Bean
    Queue CreditManagment_Quequeue() {
        // Creating a queue.
        return new Queue(QueName.CreditManagment_Que.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange CreditManagment_QuetopicExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.CreditManagment_Que.topic);
    }
 
    @Bean
    Binding CreditManagment_Quebinding(final Queue CreditManagment_Quequeue, final TopicExchange CreditManagment_QuetopicExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(CreditManagment_Quequeue).to(CreditManagment_QuetopicExchange).with(QueName.CreditManagment_Que.routing);
    }
}
