<!-- eslint-disable vue/require-v-for-key -->
<template>
    <form @submit="handleOnSubmit" class="row g-3">
        <div v-show="!!menuInfo?.id">
            <label for="item_id">Item ID</label>
            <input readonly type="text" v-model="menuItem.id" id="item_id" disabled />
        </div>
        <div class="col">
            <label for="name">Name</label>
            <input type="text" v-model="menuItem.name" placeholder="Name" class="form-control" />

            <label for="description">Description</label>
            <textarea aria-multiline="true" v-model="menuItem.description" placeholder="Description"
                class="form-control" />
        </div>
        <div class="col">
            <label for="category">Category</label>
            <input type="text" v-model="menuItem.category" placeholder="Category" class="form-control"> /

            <label for="price">Price</label>
            <!-- input number allows decimal -->
            <input type="number" v-model="menuItem.price" placeholder="Price" class="form-control" step="0.01" />
        </div>

        <!-- Options -->
        <div style="display: flex;justify-content: space-between;">
            <div class="list-group" v-for="[key, options] in Object.entries(defaultOptions)" :key="key"
                style="flex-grow: 1;">
                <h3 class="fs-5">{{ key }}</h3>
                <label class="list-groupo-item" v-for="option in options">
                    <input type="checkbox" :id="option" v-model="menuItem.customizations[key]"
                        class="form-check-input me-1" :value="option" />
                    {{ option }}
                </label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary" style="margin: 1em 0;">{{ buttonLabel }} Item</button>
    </form>
</template>

<script>
export default {
    props: {
        onsubmit: {
            type: Function,
            required: true
        },
        menuInfo: {
            type: Object,
            required: true
        }
    },

    data() {
        return {
            menuItem: {
                name: '',
                description: '',
                category: '',
                price: 0,
                customizations: {
                    'MilkType': [],
                    'Toppings': [],
                    'Sweetness': [],
                    'Size': [],
                    'Allergens': []
                }
            },
            defaultOptions: {
                'MilkType': ['Whole', '2%', 'Almond', 'Soy', 'Oat'],
                'Toppings': ['Whipped Cream', 'Caramel Drizzle', 'Chocolate Drizzle', 'Cinnamon Powder'],
                'Sweetness': ['Light', 'Medium', 'Full'],
                'Size': ['Small', 'Medium', 'Large'],
                'Allergens': ['Dairy', 'Nuts', 'Soy', 'Gluten']
            },
        }
    },
    watch: {
        menuInfo: {
            immediate: true,
            handler(newVal) {
                if (!newVal || !newVal?.id) return;
                if (Object.keys(newVal).length === 0) return;

                const customOptions = {
                    'MilkType': newVal.customizations?.['MilkType'] || newVal.customizations?.['Milk Type'] || [],
                    'Toppings': newVal.customizations['Toppings'] || [],
                    'Sweetness': newVal.customizations['Sweetness'] || [],
                    'Size': newVal.customizations['Size'] || [],
                    'Allergens': newVal.customizations['Allergens'] || []
                }

                this.menuItem = { ...newVal, customizations: customOptions };
            }
        }
    },
    computed: {
        buttonLabel() {
            return this.menuInfo?.id ? 'Update' : 'Create';
        }
    },
    methods: {
        handleOnSubmit(e) {
            e.preventDefault();
            this.onsubmit(this.menuItem);
        }
    },
}
</script>