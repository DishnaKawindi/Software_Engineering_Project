<template>
    <h1>Add Menu Item</h1>
    <menu-item-form :onsubmit="addMenuItem" />
</template>

<script>
import AdminService from '@/services/AdminService';
import MenuItemForm from '../forms/MenuItemForm.vue';

export default {
    name: 'AddMenuItem',
    components: {
        MenuItemForm
    },

    methods: {
        addMenuItem(payload) {
            AdminService.addMenuItem(payload).then(() => {
                this.$router.push({ name: 'AdminMenuItemsManagement' });
            });
        }
    }
}
</script>