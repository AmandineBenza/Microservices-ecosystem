package com.lama.mse.commons.distanceEstimation.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaIO implements IKafkaIO {
	
	@Autowired
	private KafkaTemplate<String, Integer> kafkaTemplate;
	
	public KafkaIO(){
		
	}

	@Override
	public void sendEstimatedDistance(int distance) {
		kafkaTemplate.send("distance-estimated", distance);
		
	}

}