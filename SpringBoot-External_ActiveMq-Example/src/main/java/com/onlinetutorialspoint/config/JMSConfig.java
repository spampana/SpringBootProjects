package com.onlinetutorialspoint.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
@EnableJms
public class JMSConfig {
   
	@Value("spring.activemq.broker-url")
    private String activeMqUrl;
	
	@Value("spring.activemq.user")
    private String username;
	
	@Value("spring.activemq.password")
    private String password;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("simple-jms-queue");
    }

    @Bean
    public ActiveMQConnectionFactory connectionFatory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(activeMqUrl);
        factory.setUserName(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(connectionFatory());
    }
}
