import { defineStore } from "pinia";
import { ref } from 'vue'
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
                "username":username.value,
                password,
            },
            {
                withCredentials: true
            },
        ).then(response => {
            const {data} = response
            userId.value = data.userId
            role.value = data.role
        }).catch(err => {
            message.value = err.response.data.message
        })
    }

    // 아이디 유효성 검사
    const validateId = (username) => {
        if (!username) {
            return '아이디를 입력해주세요';
        }
        if (username.includes(' ')) {
            return '공백은 사용할 수 없습니다';
        }
        if (!/^[a-z0-9_]*$/.test(username)) {
            return '소문자, 숫자, 밑줄(_)만 사용할 수 있습니다';
        }
        if (username.length < 5 || username.length > 15) {
            return '아이디는 5~20자여야 합니다';
        }
        //에러 없음
        return null
    }

    // 패스워드 유효성 검사
    const validatePassword = (password) => {
        if (!password) {
            return '패스워드를 입력해주세요.'
        }
        if (username.includes(' ')){
            return '공백은 사용할 수 없습니다.'
        }
        if (username.length < 8 || username.length > 16){
            return '패스워드는 8~16자여야 합니다.'
        }
        //에러 없음
        return null
    }

    //닉네임 유효성 검사
    const validateNickname = (nickname) => {
        if (!nickname) {
            return '닉네임을 입력해주세요.'
        }
        if (nickname.includes(' ')){
            return '공백은 사용할 수 없습니다.'
        }
        if (nickname.length < 2 || nickname > 10){
            return '닉네임은 2~10자여야 합니다.'
        }
        return null

    }

    //이메일 유효성 검사
    const validateEmail = (email) => {
        if (!email){
            return '이메일을 입력해주세요.'
        }
        if (email.includes(' ')){
            return '공백은 사용할 수 없습니다.'
        }
        if ('/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/'.test(email)){
            return '이메일 형식이 올바르지 않습니다.'
        }
        return null
    } 

    return { userId, username, role, login, message, }
})