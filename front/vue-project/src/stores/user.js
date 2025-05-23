import { defineStore } from "pinia";
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from '@/api/axios'


export const useUserStores = defineStore('user-stores', () => {
    const router = useRouter()
    const userId = ref(-1)
    const username = ref('')
    const role = ref('')

    const login = async (password) => {
        let message = ''
        try {
            const { data } = await axios.post(
                'auth/login',
                {
                    "username": username.value,
                    password,
                },
                {
                    withCredentials: true
                },
            )
            console.log(data)
            userId.value = data.userId
            role.value = data.role
            message = ''
            router.push({name: 'sidebar'})
        } catch (err) {
            console.log(err)
            message = err.response.data.message
        }
        return message
    }

    return { userId, username, role, login, }
}, { persist: true })
// 저장 유지