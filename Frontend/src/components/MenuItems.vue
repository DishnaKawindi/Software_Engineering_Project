<template>
    <div class="container">
        <h1 class="text-center">Hello! Welcome to Brewed Awakening!</h1>
        <h2 class="text-center">Our Menu Items</h2>
        <table class="table table-striped">
            <thead class="text-center">
                <th> Item ID </th>
                <th> Item Name </th>
                <th> Category </th>
                <th> Price </th>
                <th> Description </th>
            </thead>
            <tbody>
                <tr v-for="menuItem in menuItems" v-bind:key="menuItem.id">
                    <td>{{ menuItem.id }}</td>
                    <td>{{ menuItem.name }}</td>
                    <td>{{ menuItem.category }}</td>
                    <td>{{ menuItem.price }}</td>
                    <td>{{ menuItem.description }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import MenuItemService from '../services/MenuItemService'

export default {
    name: 'MenuItems',
    data() {
        return {
            menuItems: [],
        }
    },
    methods: {
        getAllMenuItems() {
            MenuItemService.getAllMenuItems().then((response) => {
                this.menuItems = response.data;
            });
        },
    },
    created() {
        this.getAllMenuItems();
    }
}
</script>

<style scoped>
.space-x-4>*+* {
    margin-left: 1rem;
}
</style>