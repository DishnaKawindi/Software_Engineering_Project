package com.example.coffeeshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffeeshop.model.CartItem;
import com.example.coffeeshop.model.CartItemRepository;
import com.example.coffeeshop.model.MenuItem;
import com.example.coffeeshop.model.MenuItemRepository;

@RestController
@RequestMapping("/api")
public class MenuItemController {

	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
    private CartItemRepository cartItemRepository;
	
	

	@GetMapping("/menuitems")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        try {
            List<MenuItem> menuItems = menuItemRepository.findAll();

            if (menuItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(menuItems, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	@PostMapping("/menuitems")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        try {
            MenuItem savedMenuItem = menuItemRepository.save(menuItem);
            return new ResponseEntity<>(savedMenuItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	// Find a menu item by id
	 @GetMapping("/menuitems/{id}")
	    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable("id") Long id) {
	        Optional<MenuItem> menuItem = menuItemRepository.findById(id);
	        if (menuItem.isPresent()) {
	            return ResponseEntity.ok(menuItem.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
