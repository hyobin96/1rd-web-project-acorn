import { defineStore } from "pinia";
import { ref } from 'vue'
import axios from 'axios'

const API_URL = 'http://localhost:8080/api'

export const useUserStores = defineStore('user-stores', () => {
    const userId = ref('')
    const username = ref('')
    const role = ref('')

    const login = (username, password) => {
        axios.post(
            API_URL,
            {
                username,
                password,
            },
            {
                withCredentials: true
            },
        ).then(response => {
            console.log(response)
        }).catch(err => {
            console.log(err)
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
            return '아이디는 8~16자여야 합니다.'
        }
        //에러 없음
        return null
    }

    return { userId, username, role, login, validateId, validatePassword }
})