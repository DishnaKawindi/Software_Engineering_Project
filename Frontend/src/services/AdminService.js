import http from "@/http-common";

class AdminService {
  constructor() {
    http.defaults.auth = {
      username: "admin",
      password: "adminPass123",
    };
  }

  getOrderByStatus(status) {
    return http.get(`/admin/orders/status/${status}`);
  }

  addMenuItem(data) {
    return http.post("/admin/menu-items", data);
  }

  deleteMenuItem(id) {
    return http.delete(`/admin/menu-items/${id}`);
  }

  updateMenuItem(id, data) {
    return http.put(`/admin/menu-items/${id}`, data);
  }

  getConfirmOrders() {
    return http.get(`/admin/orders/status/CONFIRMED`);
  }

  completeOrder(id) {
    return http.put(`/admin/orders/${id}/complete`);
  }
}

export default new AdminService();
