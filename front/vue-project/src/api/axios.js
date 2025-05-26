// src/api/axios.js
import axios from 'axios';
import router from '@/router'


const instance = axios.create({
  baseURL: 'https://localhost:9443/api',
  withCredentials: true, // HttpOnly 쿠키 포함 필수
  timeout: 5000,
});

instance.interceptors.response.use(
  response => response,
  error => {
    if (error.response && error.response.status === 403) {
      // 인증 실패 → 로그인 페이지로 이동
      router.replace('/')
    }
    return Promise.reject(error)
  }
)

export default instance;
