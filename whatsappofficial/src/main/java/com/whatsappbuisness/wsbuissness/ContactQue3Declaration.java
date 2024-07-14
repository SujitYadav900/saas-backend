package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactQue3Declaration {
 

    @Bean
    Queue Contact_Que3() {

        System.out.println("Declaring Que {}"+QueName.Contact_Que3.queName);
        // Creating a queue.
        return new Queue(QueName.Contact_Que3.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange Contact_Que3Exchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.Contact_Que3.topic);
    }
 
    @Bean
    Binding Contact_Que3binding(final Queue Contact_Que3, final TopicExchange Contact_Que3Exchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(Contact_Que3).to(Contact_Que3Exchange).with(QueName.Contact_Que3.routing);
    }
}
