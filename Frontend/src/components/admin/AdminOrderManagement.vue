<template>
    <div class="container">
        <h1 class="text-center">Admin Order Management</h1>
        <div>
            <!-- no orders -->
            <div v-if="orders.length === 0">
                <p>No orders available</p>
            </div>
        </div>
        <div class="row" v-show="orders.length > 0">
            <h2>Confirmed Orders</h2>
            <div class="col" v-for="order in orders" :key="order.id">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <div style="display: flex; justify-content: space-between;">
                            <h3 class="card-title" style="font-weight: 700;">{{ order.customerName }}</h3>
                            <div>#{{ order.id }}</div>
                        </div>
                        <h6 class="card-subtitle mb-2 text-body-secondary">{{ toCAD(order.totalAmount) }}</h6>


                        <div class="row mb-3" v-for="item in order.items" :key="item.id">
                            <div>
                                <span style="font-weight: 800;">{{ item.menuItem.name }}</span>
                                <span> x {{ item.quantity }}</span>
                            </div>
                            <ul class="list-group list-group-flush"
                                v-for="custom in Object.entries(item.selectedCustomizations)" :key="custom[0]">
                                <li class="list-group-item " style="display: grid; grid-template-columns: 1fr 1fr;">
                                    <span style="text-decoration: underline;">{{ custom[0] }}: </span>
                                    <span>{{ custom[1] }}</span>
                                </li>
                            </ul>
                        </div>

                    </div>
                    <div class="card-footer">
                        <button class="btn btn-primary" style="width: 100%;"
                            @click="handleOnClickCompletedOrder(order.id)">Completed Order</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</template>

<script>
import AdminService from '@/services/AdminService';



export default {
    data() {
        return {
            orders: [],
            interval: null
        }
    },
    created() {
        this.interval = setInterval(() => {
            this.getConfirmOrders();
        }, 5000);
        this.getConfirmOrders();
    },

    beforeUnmount() {
        clearInterval(this.interval);
    },

    methods: {
        getConfirmOrders() {
            AdminService.getConfirmOrders().then(response => {
                this.orders = response.data;
            });
        },
        toCAD(amount = 0) {
            return Intl.NumberFormat('en-CA', {
                currency: "CAD",
                style: "currency"
            }).format(amount);
        },
        handleOnClickCompletedOrder(orderId) {
            AdminService.completeOrder(orderId).then(() => {
                this.getConfirmOrders();
            });
        }
    }
}
</script>
