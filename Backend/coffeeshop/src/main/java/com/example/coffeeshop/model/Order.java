// Order.java
package com.example.coffeeshop.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<CartItem> items = new ArrayList<>();
    
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status = OrderStatus.DRAFT;
    
    @Column(name = "total_amount")
    private double totalAmount;
    
    @Column(name = "customer_name")
    private String customerName;
    
    // Constructor
    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.DRAFT;
    }
    
    // Helper method to check if order can be modified
    public boolean isModifiable() {
        return this.status == OrderStatus.DRAFT;
    }
    
    // Helper method to calculate total
    public void calculateTotal() {
        this.totalAmount = items.stream()
                .mapToDouble(item -> item.getPrice())
                .sum();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<CartItem> getItems() {
        return items;
    }
    
    public void setItems(List<CartItem> items) {
        this.items = items;
        calculateTotal();
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        // Optional: Add validation for valid status transitions
        if (this.status == OrderStatus.CONFIRMED && 
            status == OrderStatus.DRAFT) {
            throw new IllegalStateException("Cannot change status back to DRAFT once confirmed");
        }
        this.status = status;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    // Note: We don't provide a setter for totalAmount as it's calculated
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    // You might also want to add these helper methods for status checks
    public boolean isDraft() {
        return this.status == OrderStatus.DRAFT;
    }
    
    public boolean isConfirmed() {
        return this.status == OrderStatus.CONFIRMED;
    }
    
    public boolean isCompleted() {
        return this.status == OrderStatus.COMPLETED;
    }
    
    public boolean isCancelled() {
        return this.status == OrderStatus.CANCELLED;
    }
    
    // Optional: Add method to validate status transitions
    public void validateStatusTransition(OrderStatus newStatus) {
        if (this.status == OrderStatus.CONFIRMED && 
            newStatus == OrderStatus.DRAFT) {
            throw new IllegalStateException("Cannot revert to DRAFT status");
        }
        if (this.status == OrderStatus.COMPLETED && 
            newStatus != OrderStatus.CANCELLED) {
            throw new IllegalStateException("Completed order can only be cancelled");
        }
        if (this.status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cancelled order cannot change status");
        }
    }
    
    public void addItem(CartItem newItem) {
        // Look for existing item with same menuItem, size, and customizations
        Optional<CartItem> existingItem = items.stream()
            .filter(item -> isSameItem(item, newItem))
            .findFirst();

        if (existingItem.isPresent()) {
            // Update quantity of existing item
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + newItem.getQuantity());
            item.calculatePrice(); // Recalculate price with new quantity
        } else {
            // Add new item
            items.add(newItem);
        }
        calculateTotal();
    }

    private boolean isSameItem(CartItem item1, CartItem item2) {
        return item1.getMenuItem().getId().equals(item2.getMenuItem().getId()) &&
               Objects.equals(item1.getSize(), item2.getSize()) &&
               Objects.equals(item1.getSelectedCustomizations(), item2.getSelectedCustomizations());
    }
}