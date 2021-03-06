package com.lama.mse.coursiers.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lama.mse.coursiers.orders.kafka.KafkaIO;
import com.lama.mse.coursiers.orders.model.Order;
import com.lama.mse.coursiers.orders.repository.IOrderRepository;

@Service("OrderService")
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private KafkaIO kafkaIO;

	public OrderService() {
	}

	@Override
	public List<Order> findByCoursierMail(String coursierMail) {
		return orderRepository.findByCoursierMail(coursierMail);
	}

//	@Override
//	public List<Order> getOrdersAroundMe() {
//		List<Order> orders = new ArrayList<>();
//		orders = orderRepository.findAll().stream().filter(order -> order.isAroundMe())
//				.collect(Collectors.toList());
//		return orders;
//	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

//	@Override
//	public void deleteOrder(long id) {
//		Order order = orderRepository.findById((int) id);
//		orderRepository.delete(order);
//		kafkaIO.sendDeletedOrderMessage(new Gson().toJson(order));
//	}
//
//	@Override
//	public void modifyClientMail(long id, String mail) {
//		Order order = orderRepository.findById((int) id);
//		order.setClientMail(mail);
//		kafkaIO.sendDeletedOrderMessage(new Gson().toJson(order));
//	}
//
//	@Override
//	public void modifyRestaurantName(long id, String restaurantName) {
//		Order order = orderRepository.findById((int) id);
//		order.setRestaurantName(restaurantName);
//		kafkaIO.sendDeletedOrderMessage(new Gson().toJson(order));
//	}
//
//	@Override
//	public void modifyDeliveryLocation(long id, String deliveryLocation) {
//		Order order = orderRepository.findById((int) id);
//		order.setDeliveryLocation(deliveryLocation);
//		kafkaIO.sendDeletedOrderMessage(new Gson().toJson(order));
//	}
//
//	@Override
//	public void modifyFoodNames(long id, List<String> foodNames) {
//		Order order = orderRepository.findById((int) id);
//		order.setFoodNames(foodNames);
//		kafkaIO.sendDeletedOrderMessage(new Gson().toJson(order));
//	}

	@Override
	public List<Order> findById(String orderId) {
		return orderRepository.findByOrderId(orderId);
	}

	@Override
	public void deleteOrder(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyClientMail(long id, String mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyRestaurantName(long id, String restaurantName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyDeliveryLocation(long id, String deliveryLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyFoodNames(long id, List<String> foodNames) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getOrdersAroundMe() {
		// TODO Auto-generated method stub
		return null;
	}

	
}