package com.whatsappbuisness.wsbuissness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainQueue2Declaration {
    @Bean
    Queue Main_Que2queue() {
        // Creating a queue.
        return new Queue(QueName.Main_Que2.queName, Boolean.FALSE);
    }

    @Bean
    TopicExchange Main_Que2topicExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.Main_Que2.topic);
    }

    @Bean
    Binding Main_Que2binding(final Queue Main_Que2queue, final TopicExchange Main_Que2topicExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(Main_Que2queue).to(Main_Que2topicExchange).with(QueName.Main_Que2.routing);
    }
}
