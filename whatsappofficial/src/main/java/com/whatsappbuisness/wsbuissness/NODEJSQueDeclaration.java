package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NODEJSQueDeclaration {
 

    @Bean
    Queue NodejsEvent_Quequeue() {
        // Creating a queue.
        return new Queue(QueName.NodejsEvent_Que.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange NodejsEvent_QuetopicExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.NodejsEvent_Que.topic);
    }
 
    @Bean
    Binding NodejsEvent_Quebinding(final Queue NodejsEvent_Quequeue, final TopicExchange NodejsEvent_QuetopicExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(NodejsEvent_Quequeue).to(NodejsEvent_QuetopicExchange).with(QueName.NodejsEvent_Que.routing);
    }
}
