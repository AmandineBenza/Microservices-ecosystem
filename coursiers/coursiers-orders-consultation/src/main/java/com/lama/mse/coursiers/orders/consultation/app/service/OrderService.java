package com.lama.mse.coursiers.orders.consultation.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lama.mse.coursiers.orders.consultation.app.model.Order;
import com.lama.mse.coursiers.orders.consultation.app.repository.IOrderRepository;

@Service("OrderService")
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;
	
	public OrderService(){
		
	}

	// TODO
	@Override
	public List<Order> getOrdersWithCoursierLocation(String coursierLocation) {
		List<Order> orders = orderRepository.findAll();
//		orders = orders.stream()
//				.filter(o -> distance(o.getDeliveryLocation(), coursierLocation))
//			.collect(Collections::list);
		
		return null;
	}
	
}