package com.example.coffeeshop.model;

import java.util.Map;

public class OrderItemDTO {
    private Long menuItemId;
    private String size;
    private int quantity;
    private Map<String, String> customizations;
    
	public Long getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Map<String, String> getCustomizations() {
		return customizations;
	}
	public void setCustomizations(Map<String, String> customizations) {
		this.customizations = customizations;
	}

    // Getters and setters
    
}
