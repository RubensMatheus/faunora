<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import apiClient from './services/api';

export default defineComponent({
    setup() {
        const pets = ref<string[]>([]);

        onMounted(async () => {
            try {
                const response = await apiClient.get('/pets');
                pets.value = response.data;
            } catch (error) {
                console.error('Erro ao buscar pets:', error);
            }
        });

        return { pets };
    },
});
</script>

<template>
    <div>
        <h1>Pets</h1>
        <ul>
            <li v-for="pet in pets" :key="pet">{{ pet }}</li>
        </ul>
    </div>
</template>
