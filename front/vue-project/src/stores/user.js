import { defineStore } from "pinia";
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from '@/api/axios'


export const useUserStores = defineStore('user-stores', () => {
    const router = useRouter()
    const userId = ref(-1)
    const username = ref('')
    const nickname = ref('')
    const email = ref('')
    const role = ref('')
    const profilePhotoSrc = ref('')

    const login = async (password) => {
        let message = ''
        try {
            // 로그인 요청
            await axios.post(
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
            const { data } = await axios.get(
                'users/me'
            )
            userId.value = data.userId
            username.value = data.username
            nickname.value = data.nickname
            email.value = data.email
            role.value = data.role
            profilePhotoSrc.value = data.profile_image
            message = ''
            router.push({ name: 'sidebar' })
        } catch (err) {
            console.log(err)
            message = err.response.data.message
        }
        return message
    }

    return { userId, username, role, login, profilePhotoSrc, nickname, email}
}, { persist: true })
// 저장 유지