<template>
    <div class="container">
        <div class="row">
            <div class="col-2">
                <ul class="list-group ">
                    <router-link :to="{ name: 'AdminMenuItemsManagement' }"
                        :class="`list-group-item ${activeWhen('AdminMenuItemsManagement')}`" aria-current="true">
                        Menu Management
                    </router-link>
                    <router-link :to="{ name: 'AdminOrderManagement' }"
                        :class="`list-group-item ${activeWhen('AdminOrderManagement')}`">
                        Order Management
                    </router-link>
                </ul>
            </div>
            <div class="col">
                <RouterView />
            </div>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        activeWhen(routeName) {
            return this.$route.name === routeName ? 'active' : '';
        }
    }
}
</script>