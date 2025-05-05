<template>
    <div class="container">
        <h2>Update Menu Item</h2>
        <menu-item-form :menu-info="menuInfo" :onsubmit="handleOnSubmit" />
    </div>
</template>

<script>
import AdminService from '@/services/AdminService';
import MenuItemService from '../../services/MenuItemService'
import MenuItemForm from '../forms/MenuItemForm.vue';

export default {
    name: 'EditMenuItem',
    components: {
        MenuItemForm
    },
    data() {
        return {
            menuInfo: {},
        }
    },
    methods: {
        getMenuItemById(id) {
            MenuItemService.getMenuItemById(id).then((response) => {
                console.log(response.data);
                this.menuInfo = response.data;
            });
        },
        handleOnSubmit(menuItem) {
            const id = this.$route.params.id;
            AdminService.updateMenuItem(id, menuItem).then(() => {
                this.$router.push({ name: 'AdminMenuItemsManagement' });
            });
        }
    },
    mounted() {
        const id = this.$route.params.id;
        this.getMenuItemById(id);
    }
}
</script>
