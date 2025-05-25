import { defineStore } from "pinia";
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from '@/api/axios'
import defaultProfile from '@/assets/default-profile.png'

export const useUserStores = defineStore('user-stores', () => {
    const url = 'https://localhost:9443'
    const router = useRouter()
    const userId = ref(0)
    const username = ref('')
    const nickname = ref('')
    const email = ref('')
    const role = ref('')
    const profileImage = ref(defaultProfile)

    // 로그인 과정정
    const login = async (password) => {
        let message = ''
        try {
            // 로그인 요청
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
            // 로그인 후 유저 정보 요청
            userId.value = data.userId
            username.value = data.username
            nickname.value = data.nickname
            email.value = data.email
            role.value = data.role
            profileImage.value = url + data.profileImage
            message = ''
            router.push({ name: 'sidebar' })
        } catch (err) {
            message = err.response.data.message
        }
        return message
    }

    // 로그아웃 과정
    const logout = async function () {
        await axios.post('auth/logout')
        reset()
        router.replace('/')
    }

    // 로그아웃 하면서 리셋하는 과정
    const reset = () => {
        userId.value = 0
        username.value = ''
        nickname.value = ''
        email.value = ''
        role.value = ''
        profileImage.value = defaultProfile
    }

    return { userId, username, role, login, nickname, email, profileImage, logout }
}, { persist: true })
// 저장 유지