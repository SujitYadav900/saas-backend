package com.whatsappbuisness.wsbuissness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainQueDeclaration {
 

    @Bean
    Queue Main_Quequeue() {
        // Creating a queue.
        return new Queue(QueName.Main_Que.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange Main_QuetopicExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.Main_Que.topic);
    }
 
    @Bean
    Binding Main_Quebinding(final Queue Main_Quequeue, final TopicExchange Main_QuetopicExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(Main_Quequeue).to(Main_QuetopicExchange).with(QueName.Main_Que.routing);
    }
}
