package com.lama.mse.clients.controller.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.lama.mse.clients.controller.commons.Logs;

@Component
public class KafkaIO {

	@Autowired
	private KafkaTemplate<String, String> template;
	
	public KafkaIO() {
		
	}
	
	public ListenableFuture<SendResult<String, String>> sendCreateOrderRequest(String orderJson) {
		ListenableFuture<SendResult<String, String>> future = template.send("create-order", orderJson);
		Logs.infoln("Sent create-order.");
		return future;
	}
	
	public ListenableFuture<SendResult<String, String>> sendEditClientRequest(String mail, String clientAttribute, String attributeValue) {
		String verb = "edit-client";
		
		if(clientAttribute != null) {
			verb += "-" + clientAttribute;
		}
		
		String requestContent = mail + ";" + attributeValue;
		ListenableFuture<SendResult<String, String>> future = template.send(verb, requestContent);
		Logs.infoln("Sent edit-client: " + requestContent);
		return future;
	}
	
}
