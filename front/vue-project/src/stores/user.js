import { defineStore } from "pinia";
import { ref } from 'vue'

export const useUserStores = defineStore('user-stores', () => {
    const userId = ref('')
    const username = ref('')
    const role = ref('')

    return {userId, username, role}
})