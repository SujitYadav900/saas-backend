package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoQueDeclaration {
 

    @Bean
    Queue Mongo_Quequeue() {
        // Creating a queue.
        return new Queue(QueName.Mongo_Que.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange Mongo_QuetopicExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.Mongo_Que.topic);
    }
 
    @Bean
    Binding Mongo_Quebinding(final Queue Mongo_Quequeue, final TopicExchange Mongo_QuetopicExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(Mongo_Quequeue).to(Mongo_QuetopicExchange).with(QueName.Mongo_Que.routing);
    }
}
