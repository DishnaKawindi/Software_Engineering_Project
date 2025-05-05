<template>
    <div class="container">
      <h2>Review Order</h2>
      <div v-if="order">
        <p>Customer Name: {{ order.customerName }}</p>

        <ul>
          <li v-for="item in order.items" v-bind:key="item.id">
            {{ item.menuItem.name }} - {{ item.quantity }} x {{ item.size }} - ${{ item.price }}
            <button class="btn btn-danger btn-sm" @click="deleteItem(item.id)"> Remove </button>
          </li>
        </ul>

        <p>Total: ${{ order.totalAmount }}</p>

        <button class="btn btn-success" @click="confirmOrder"> Confirm Order </button>
      </div>
    </div>
  </template>
  
  <script>
  import OrderService from "../services/OrderService";
  
  export default {
    props: ["orderId"],
    data() {
      return {
        order: null,
      };
    },
    mounted() {
      OrderService.getOrder(this.orderId)
        .then((response) => {
          this.order = response.data;
          console.log("Order details:", this.order);
        })
        .catch((error) => {
          console.error("Error fetching order details:", error);
          alert("Failed to load order.");
        });
    },
    methods: {
      deleteItem(itemId) {
        OrderService.deleteOrderItem(this.orderId, itemId)
          .then(() => {
            console.log("Item removed from order");
            this.order.items = this.order.items.filter((item) => item.id !== itemId);
          })
          .catch((error) => {
            console.error("Error deleting item:", error);
            alert("Failed to delete item.");
          });
      },
      confirmOrder() {
        OrderService.confirmOrder(this.orderId)
          .then(() => {
            console.log("Order confirmed");
            alert("Order confirmed!");
            // Going back to menu page after confirming the order
            this.$router.push("/");
          })
          .catch((error) => {
            console.error("Error confirming order:", error);
            alert("Failed to confirm order.");
          });
      },
    },
  };
  </script>