package com.lama.mse.restaurants.modification.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
				"kafka:9092");
		props.put(
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
				StringDeserializer.class);
		props.put(
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
				StringDeserializer.class);
	
		return new DefaultKafkaConsumerFactory<>(props);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> 	kafkaListenerContainerFactory() {
		
		ConcurrentKafkaListenerContainerFactory<String, String> factory
		= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setReplyTemplate(kafkaTemplate());
		return factory;
	}
	
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
	  
		return new KafkaTemplate<>(new KafkaProducerConfig().producerFactory());
	}



}