import http from "../http-common";

class OrderService {
  createOrder(order) {
    return http.post("/orders", order);
  }

  addItemToOrder(orderId, item) {
    return http.post(`/orders/${orderId}/items`, item);
  }

  deleteOrderItem(orderId, itemId) {
    return http.delete(`/orders/${orderId}/items/${itemId}`);
  }

  reviewOrder(orderId) {
    return http.get(`/orders/${orderId}/checkout`);
  }

  confirmOrder(orderId) {
    return http.post(`/orders/${orderId}/confirm`);
  }

  getOrder(orderId) {
    return http.get(`/orders/${orderId}`);
  }

  modifyOrderItem(orderId, itemId, item) {
    return http.put(`/orders/${orderId}/items/${itemId}`, item);
  }
}

export default new OrderService();
