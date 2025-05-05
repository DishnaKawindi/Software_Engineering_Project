<template>
    <div class="container">
        <h1>
            Admin Menu Items Management
        </h1>
        <router-link :to="{ name: 'AdminAddMenuItem' }" class="btn btn-primary">Add Menu Item</router-link>
        <div>
            <table class="table table-striped">
                <thead>
                    <th> Item ID </th>
                    <th> Item Name </th>
                    <th> Category </th>
                    <th> Price </th>
                    <th class="text-center"> Actions </th>
                </thead>
                <tbody>
                    <tr v-for="menuItem in menuItems" v-bind:key="menuItem.id">
                        <td>{{ menuItem.id }}</td>
                        <td>{{ menuItem.name }}</td>
                        <td>{{ menuItem.category }}</td>
                        <td>{{ toCAD(menuItem.price) }}</td>

                        <td class="text-right" style="display: flex;justify-content: center;">
                            <router-link :to="{ name: 'AdminEditMenuItem', params: { id: menuItem.id } }"
                                class="btn btn-outline-secondary">Edit</router-link>
                            <button class="btn btn-danger" @click="handleOnClickShowDialog(menuItem.id)">Delete</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <RouterView />

        <!-- Modal -->
        <dialog id="modal">
            <div class=" modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="display: flex;justify-content: space-between;">
                        <h5 class="modal-title">Delete?</h5>
                        <button type="button" class="btn-close" onclick="modal.close()"></button>
                    </div>
                    <div class="modal-body">
                        <p>It cannot be undone. Do you want to proceed?</p>
                    </div>
                    <div class="modal-footer col space-x-4">
                        <button type="button" class="btn btn-outline-secondary" onclick="modal.close()">Close</button>
                        <button type="button" class="btn btn-danger" @click="handleOnClickDelete()">Delete</button>
                    </div>
                </div>
            </div>
        </dialog>

    </div>
</template>

<script>
import AdminService from '@/services/AdminService';
import MenuItemService from '@/services/MenuItemService';

export default {
    name: 'AdminMenuItemsManagement',
    data() {
        return {
            menuItems: [],
            dialog: null,
        }
    },
    created() {
        this.getAllMenuItems();
    },
    mounted() {
        this.dialog = document.getElementById('modal');
    },
    methods: {
        getAllMenuItems() {
            MenuItemService.getAllMenuItems().then((response) => {
                this.menuItems = response.data;
            });
        },
        handleOnClickDelete() {
            if (this.selectedId) {
                AdminService.deleteMenuItem(this.selectedId).then(() => {
                    this.getAllMenuItems();
                    this.selectedId = null;
                    this.dialog.close();
                    this.menuItems = this.menuItems.filter((menuItem) => menuItem.id !== this.selectedId);
                });
            }
        },
        handleOnClickShowDialog(id) {
            this.selectedId = id;
            this.dialog.showModal();
        },
        toCAD(price) {
            return Intl.NumberFormat('en-CA', { style: 'currency', currency: 'CAD' }).format(price);
        }
    }
}
</script>

<style scoped>
dialog::backdrop {
    background-color: rgba(0, 0, 0, 0.5);
}
</style>