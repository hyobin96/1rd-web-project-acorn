// src/api/axios.js
import axios from 'axios';

const instance = axios.create({
  baseURL: 'https://localhost:9443/api',
  withCredentials: true, // HttpOnly 쿠키 포함 필수
  timeout: 5000,
});

export default instance;
