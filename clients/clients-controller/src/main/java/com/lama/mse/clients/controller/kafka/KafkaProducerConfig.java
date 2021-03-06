package com.lama.mse.clients.controller.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaProducerConfig {

	@Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
		          "kafka:9092");
        configProps.put(
          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        configProps.put(
          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
 
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

 // ReplyingKafkaTemplate
    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyKafkaTemplate(ProducerFactory<String,String> pf, KafkaMessageListenerContainer<String, String> container) {
      return new ReplyingKafkaTemplate<>(pf, container);
    }
    
    // Listener Container to be set up in ReplyingKafkaTemplate
    @Bean
    public KafkaMessageListenerContainer<String, String> replyContainer(ConsumerFactory<String, String> cf) {
      ContainerProperties containerProperties = new ContainerProperties("topic");
      return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }
}