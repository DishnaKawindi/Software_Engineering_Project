import http from "../http-common.js";

class MenuItemService {
  getAllMenuItems() {
    return http.get("/menuitems");
  }

  getMenuItemById(id) {
    return http.get(`/menuitems/${id}`);
  }
}

export default new MenuItemService();
