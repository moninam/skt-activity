package com.encora.microservice.configuration;

import com.encora.microservice.repository.ProductDAO;
import com.encora.microservice.repository.impl.ProductDAOImpl;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableRabbit
public class MicroserviceConfiguration {
    @Value("${rabbit.product.exchange}")
    private String productExchange;
    @Value("${rabbit.product.request.queue}")
    private String requestProductQueue;
    @Value("${rabbit.product.response.queue}")
    private String responseProductQueue;
    @Value("${rabbit.product.stored.queue}")
    private String storedProductQueue;
    @Value("${rabbit.product.reply.queue}")
    private String replyProductQueue;

    @Value("${rabbit.rk.response}")
    private String responseRK;
    @Value("${rabbit.rk.request}")
    private String requestRK;
    @Value("${rabbit.rk.stored}")
    private String storedRK;
    @Value("${rabbit.rk.reply}")
    private String replyRK;
    @Bean
    public ProductDAO provideProductRepository(){
        return new ProductDAOImpl();
    }

    @Bean
    public Executor executor(){
        return Executors.newCachedThreadPool();
    }
    @Bean
    public SimpleRabbitListenerContainerFactory listenerContainer(ConnectionFactory connectionFactory,
                                                                  SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setTaskExecutor(executor());
        configurer.configure(factory, connectionFactory);
        return factory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        template.setReplyTimeout(20000);

        return template;
    }
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public Queue responseQueue() {
        return new Queue(responseProductQueue);
    }

    @Bean
    public Queue requestQueue() {
        return new Queue(requestProductQueue);
    }

    @Bean
    public Queue storedQueue() {
        return new Queue(storedProductQueue);
    }

    @Bean
    public Queue replyQueue() {
        return new Queue(replyProductQueue);
    }


    @Bean
    public SimpleMessageListenerContainer replyListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.setQueues(replyQueue());
        simpleMessageListenerContainer.setTaskExecutor(executor());
        return simpleMessageListenerContainer;
    }


    @Bean
    public AsyncRabbitTemplate asyncRabbitTemplate(ConnectionFactory connectionFactory) {

        AsyncRabbitTemplate asyncRabbitTemplate = new AsyncRabbitTemplate(rabbitTemplate(connectionFactory),
                replyListenerContainer(connectionFactory),
                productExchange + "/" + responseRK);
        return asyncRabbitTemplate;
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(productExchange);
    }

    @Bean
    public Binding requestBinding(){
        return BindingBuilder.bind(requestQueue()).to(directExchange()).with(requestRK);
    }

    @Bean
    public Binding responseBinding(){
        return BindingBuilder.bind(responseQueue()).to(directExchange()).with(responseRK);
    }
    @Bean
    public Binding storedBinding(){
        return BindingBuilder.bind(storedQueue()).to(directExchange()).with(storedRK);
    }
    @Bean
    public Binding replyBinding(){
        return BindingBuilder.bind(replyQueue()).to(directExchange()).with(replyRK);
    }
}
