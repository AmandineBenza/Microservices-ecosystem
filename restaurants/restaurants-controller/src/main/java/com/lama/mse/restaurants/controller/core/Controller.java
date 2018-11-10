package com.lama.mse.restaurants.controller.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lama.mse.restaurants.controller.kafka.KafkaConsumerConfig;
import com.lama.mse.restaurants.controller.kafka.KafkaIO;

@RestController
@RequestMapping("/MS")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Controller {

	@Autowired
	private KafkaIO kafkaIO;

	public Controller() {
	}

//	@RequestMapping(value = "FOOD/{category}", method = RequestMethod.GET)
//	public ResponseEntity consultFoodByCategoryEntryPoint(@PathVariable("category") String category) {
//		System.out.println("consult-category !");
//
//		ListenableFuture<SendResult<String, String>> future = kafkaIO.sendConsultFoodByCategory(category);
//		// TODO future.addCallback(arg0);
//
//		return new ResponseEntity("", HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "FOODS/{restaurant}", method = RequestMethod.GET)
//	public ResponseEntity consultFoodEntryPoint(@PathVariable("restaurant") String restaurantName) {
//		ListenableFuture<SendResult<String, String>> future = kafkaIO.sendConsultFood(restaurantName);
//		return new ResponseEntity("", HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "RESTAURANT/{restaurant}", method = RequestMethod.GET)
//	public ResponseEntity consultRestaurantEntryPoint(@PathVariable("restaurant") String restaurant) {
//		ListenableFuture<ConsumerRecord<String, String>> future = kafkaIO.sendConsultRestaurant(restaurant);
//		
//		return new ResponseEntity("", HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "ORDER/{restaurant}", method = RequestMethod.GET)
//	public ResponseEntity consultOrderEntryPoint(@PathVariable("restaurant") String restaurant) {
//		ListenableFuture<SendResult<String, String>> future = kafkaIO.sendConsultOrder(restaurant);
//		return new ResponseEntity("", HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "FOOD/{category}", method = RequestMethod.POST)
//	public ResponseEntity editFoodByCategoryEntryPoint(@PathVariable("category") String category) {
//		ListenableFuture<SendResult<String, String>> future = kafkaIO.sendConsultFoodByCategory(category);
//		return new ResponseEntity("", HttpStatus.OK);
//	}

	@RequestMapping(value = "FOOD", method = RequestMethod.POST)
	public ResponseEntity editFoodEntryPoint(@RequestBody String foodJson) throws InterruptedException, ExecutionException, TimeoutException {
		ListenableFuture<ConsumerRecord<String,String>> future = kafkaIO.sendCreateFood(foodJson);
		String result = "Food could not been created.";
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
//		try {
		
			result = future.get(15000, TimeUnit.MILLISECONDS).value();
			status = HttpStatus.OK;
//		} catch (InterruptedException | ExecutionException | TimeoutException e) {
//			System.out.println("XD");
//			e.printStackTrace();
//		}
		
		return new ResponseEntity<>(result,status);
	}

//	@RequestMapping(value = "RESTAURANT/{restaurant}", method = RequestMethod.POST)
//	public ResponseEntity editRestaurantEntryPoint(@PathVariable("restaurant") String restaurant) {
//		ListenableFuture<ConsumerRecord<String, String>> future = kafkaIO.sendConsultRestaurant(restaurant);
//		return new ResponseEntity("", HttpStatus.OK);
//	}

//	@RequestMapping(value = "ORDER/{restaurant}", method = RequestMethod.POST)
//	public ResponseEntity editOrderEntryPoint(@PathVariable("restaurant") String restaurant) {
//		ListenableFuture<SendResult<String, String>> future = kafkaIO.sendConsultOrder(restaurant);
//		return new ResponseEntity("", HttpStatus.OK);
//	}

}