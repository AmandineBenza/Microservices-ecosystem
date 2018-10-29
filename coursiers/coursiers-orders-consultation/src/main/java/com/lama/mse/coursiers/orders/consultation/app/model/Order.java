package com.lama.mse.coursiers.orders.consultation.app.model;


import java.util.List;

// Is Delivery location the client address ?
public class Order {

	private long id;
	private String clientMail;
	private String restaurantName;
	private String deliveryLocation;
	private List<String> foodNames;
	
	public Order(long id, String clientMail, String restaurantName,
			String deliveryLocation, List<String> foodNames) {
		this.id = id;
		this.clientMail = clientMail;
		this.restaurantName = restaurantName;
		this.deliveryLocation = deliveryLocation;
		this.foodNames = foodNames;
	}

	public Order(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientMail() {
		return clientMail;
	}

	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<String> getFoodNames() {
		return foodNames;
	}

	public void setFoodNames(List<String> foodNames) {
		this.foodNames = foodNames;
	}

	public String getDeliveryLocation() {
		return deliveryLocation;
	}

	public void setDeliveryLocation(String deliveryLocation) {
		this.deliveryLocation = deliveryLocation;
	}
	
}