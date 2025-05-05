<template>
    <div class="container">
      <h2>Create your order here!</h2>
  
      <!-- Customer Name -->
      <form @submit.prevent="submitOrder">
        <div class="form-group">
          <label for="customerName">Customer Name</label>
          <input type="text" id="customerName" v-model="order.customerName" class="form-control" placeholder="Enter customer name" required/>
        </div>
  
        <!-- Order Items Array -->
        <h3>Items</h3>
        <div v-for="(item, index) in order.items" v-bind:key="index" class="mb-3">
          <h5>Item {{ index + 1 }}</h5>
          <div class="form-group">
            <label for="menuItemId">Menu Item ID</label>
            <input type="number" v-model="item.menuItemId" class="form-control" placeholder="Enter menu item ID" required/>
          </div>
  
          <div class="form-group">
            <label for="size">Size</label>
            <select v-model="item.size" class="form-control" required>
              <option value="SMALL">Small</option>
              <option value="MEDIUM">Medium</option>
              <option value="LARGE">Large</option>
            </select>
          </div>
  
          <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" v-model="item.quantity" class="form-control" placeholder="Enter quantity" required/>
          </div>
  
          <!-- Taking the customization in JSON format -->
          <div class="form-group">
            <label for="customizations">Customizations</label>
            <textarea v-model="item.customizationsJSON" class="form-control" placeholder='Enter customizations in JSON format (e.g., {"Milk Type": "Almond"})' rows="2" required></textarea>
          </div>
  
          <button type="button" class="btn btn-danger" @click="removeItem(index)"> Remove Item </button>
        </div>
  
        <button type="button" class="btn btn-primary" @click="addItem"> Add Item </button>
  
        <!-- Submit Order -->
        <div class="mt-4">
          <button type="submit" class="btn btn-success">Submit Order</button>
        </div>
      </form>
  
      <!-- Preview Order JSON - Only for reference -->
      <!-- <pre class="mt-4">{{ order }}</pre> -->
    </div>
  </template>
  
  <script>
  import OrderService from "../services/OrderService";
  
  export default {
    data() {
      return {
        order: {
          customerName: "",
          items: [],
        },
      };
    },
    methods: {
      addItem() {
        this.order.items.push({
          menuItemId: null,
          size: "MEDIUM",
          quantity: 1,
          customizationsJSON: '{"Milk Type": "Almond"}',
        });
      },
      removeItem(index) {
        this.order.items.splice(index, 1);
      },
      submitOrder() {
        try {
          // Converting customizationsJSON to proper JSON object for each item
          this.order.items.forEach((item) => {
            item.customizations = JSON.parse(item.customizationsJSON);
            delete item.customizationsJSON;
          });
  
          // Calling the OrderService to create the order
          OrderService.createOrder(this.order)
            .then((response) => {
              console.log("Order created:", response.data);
              const orderId = response.data.id;
              alert(`Order created successfully! Order ID: ${orderId}`);
              // Reset order after successful submission
              this.order = { customerName: "", items: [] };
            })
            .catch((error) => {
              console.error("Error creating order:", error.response || error.message);
              alert("Failed to create order.");
            });
        } catch (error) {
          console.error("Error parsing customizations JSON:", error);
          alert("Invalid customizations JSON format.");
        }
      },
    },
  };
  </script>
  
  <style>
  
  </style>