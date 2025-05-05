package com.example.coffeeshop.model;

import java.util.List;

public class CreateOrderDTO {
    private String customerName;
    private List<OrderItemDTO> items;
    
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<OrderItemDTO> getItems() {
		return items;
	}
	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}

    // Getters and setters
    
}
