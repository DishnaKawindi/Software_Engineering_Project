package com.example.coffeeshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "menuItems")
public class MenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "category")
	private String category; // For Coffee, Tea, Pastry, Beans

	@Column(name = "basPrice")
	private double basePrice; //base price

	@ElementCollection
	@MapKeyColumn(name = "customizationType")
	@Column(name = "options")
	private Map<String, List<String>> customizations; // ArrayList for customization options

	@ElementCollection
	@Column(name = "nutritionalInfo")
	private List<String> nutritionalInformation = new ArrayList<>(); 

	public MenuItem() {

	}

	public MenuItem(Long id, String name, String description, String category, double price,
			Map<String, List<String>> customizations, ArrayList<String> nutritionalInformation) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.basePrice = price;
		this.customizations = customizations;
		this.nutritionalInformation = nutritionalInformation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return basePrice;
	}

	public void setPrice(double price) {
		this.basePrice = price;
	}

	public Map<String, List<String>> getCustomizations() {
		return customizations;
	}

	public void setCustomizations(Map<String, List<String>> customizations) {
		this.customizations = customizations;
	}
	
	public List<String> getNutritionalInformation() {
        return nutritionalInformation;
    }

    public void setNutritionalInformation(List<String> nutritionalInformation) {
        this.nutritionalInformation = nutritionalInformation;
    }

}
