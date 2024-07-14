package com.whatsappbuisness.wsbuissness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainQueue3Declaration {

    @Bean
    Queue Main_Que3queue(){
        return  new Queue(QueName.Main_Que3.queName);
    }

    @Bean
    TopicExchange Main_Que3TopicExchange(){
        return  new TopicExchange(QueName.Main_Que3.topic);
    }
    @Bean
    Binding Main_Que3binding(final Queue Main_Que3queue, final TopicExchange Main_Que3TopicExchange){
        return BindingBuilder.bind(Main_Que3queue).to(Main_Que3TopicExchange).with(QueName.Main_Que3.routing);
    }
}
