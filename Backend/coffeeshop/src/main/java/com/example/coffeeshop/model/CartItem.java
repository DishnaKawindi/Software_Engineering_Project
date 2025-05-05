package com.example.coffeeshop.model;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem; //select a menu item object to add to cart_items table

    @Column(name = "size")
    private String size; // Small, Medium, Large

    @Column(name = "price")
    private double price; // Updated price based on size

    @Column(name = "quantity")
    private int quantity; //to select more than one 
    
    @ElementCollection
    @CollectionTable(name = "cart_item_customizations")
    @MapKeyColumn(name = "customization_type")
    @Column(name = "customization_value")
    private Map<String, String> selectedCustomizations = new HashMap<>();

    // Add new method to calculate price based on size
    public void calculatePrice() {
        double sizeExtra = switch (this.size.toUpperCase()) {
            case "SMALL" -> 1.0;
            case "MEDIUM" -> 2.0;
            case "LARGE" -> 3.0;
            default -> 0.0;
        };
        this.price = (this.menuItem.getPrice() + sizeExtra) * this.quantity;
    }

    // Add getters and setters for selectedCustomizations
    public Map<String, String> getSelectedCustomizations() {
        return selectedCustomizations;
    }

    public void setSelectedCustomizations(Map<String, String> selectedCustomizations) {
        this.selectedCustomizations = selectedCustomizations;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    
    
}