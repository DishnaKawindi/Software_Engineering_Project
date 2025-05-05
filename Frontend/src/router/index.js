import { createWebHistory, createRouter } from "vue-router";
import MenuItems from "../components/MenuItems.vue";
import ReviewOrder from "../components/ReviewOrder.vue";
import OrderManagement from "../components/OrderManagement.vue";
import AdminOrderManagement from "@/components/admin/AdminOrderManagement.vue";
import AdminPage from "@/components/admin/AdminPage.vue";
import AdminMenuItemsManagement from "@/components/admin/AdminMenuItemsManagement.vue";
import AddMenuItem from "@/components/admin/AddMenuItem.vue";
import EditMenuItem from "@/components/admin/EditMenuItem.vue";

const routes = [
  {
    path: "/",
    alias: "/menuitems",
    name: "menuitems",
    component: MenuItems,
  },
  {
    path: "/orders",
    component: OrderManagement,
  },
  {
    path: "/orders/:orderId",
    component: ReviewOrder,
    props: true, // Automatically maps the route parameter to component prop.
  },

  {
    path: "/admin",
    component: AdminPage,
    children: [
      {
        path: "orders",
        name: "AdminOrderManagement",
        component: AdminOrderManagement,
      },
      {
        path: "menu-items",
        name: "AdminMenuItemsManagement",
        component: AdminMenuItemsManagement,
        children: [
          {
            path: "add",
            name: "AdminAddMenuItem",
            component: AddMenuItem,
          },
        ],
      },
      {
        path: "/admin/menu-items/:id/edit",
        name: "AdminEditMenuItem",
        component: EditMenuItem,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
