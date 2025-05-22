import { defineStore } from "pinia";
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'


const API_URL = 'http://localhost:8080/api/auth'
export const useUserStores = defineStore('user-stores', () => {
    const userId = ref(-1)
    const username = ref('')
    const role = ref('')
    const message = ref('')


    const login = async (password) => {
        await axios.post(
            `${API_URL}/login`,
            {
                "username": username.value,
                password,
            },
            {
                withCredentials: true
            },
        ).then(response => {
            const { data } = response
            userId.value = data.userId
            role.value = data.role
            message.value = ''
        }).catch(err => {
            // 에러메시지 출력
            message.value = err.response.data.message
        })

        return message.value
    }

    return { userId, username, role, login, message, }
}, { persist: true })
// 저장 유지