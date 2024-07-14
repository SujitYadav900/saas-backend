package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebhookQueDeclaration {


    @Bean
    Queue Webhook_Quequeue() {
        // Creating a queue.
        return new Queue(QueName.Webhook_Que.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange Webhook_QuetopicExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.Webhook_Que.topic);
    }
 
    @Bean
    Binding Webhook_Quebinding(final Queue Webhook_Quequeue, final TopicExchange Webhook_QuetopicExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(Webhook_Quequeue).to(Webhook_QuetopicExchange).with(QueName.Webhook_Que.routing);
    }
}
