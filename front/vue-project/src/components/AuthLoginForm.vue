<template>
    <div>
        <h2>LoginForm</h2>
        <span>아이디: </span><input type="text" v-model="username">
        <br>
        <span>비밀번호: </span><input type="password" v-model="password">
        <br>
        <button class="btn btn-success" @click="login">로그인</button>
        <button @click="getPlaylist">playlist</button>
    </div>
</template>

<script setup>
import axios from 'axios'
import { ref } from 'vue'
import { useUserStores } from '@/stores/user'

const store = useUserStores()
const username = ref('')
const password = ref('')

const login = async () => {
    console.log(1)
    await axios.post(
        'http://localhost:8080/api/auth/login',
        {
            username: username.value,
            password: password.value,
        }, {
        withCredentials: true
    }
    ).then(response => {
        console.log(response)
        const {userId, username, role} = response.data
        console.log(username, role)
        // user저장소에 저장
        store.userId = userId
        store.username = username
        store.role = role
        console.log(store.userId, store.username, store.role)
    }).catch((err) => {
        console.log(err)

    })
}

const getPlaylist = async () => {
    await axios.get(
        "http://localhost:8080/api/playlist/1",
        {
            withCredentials: true
        }
    ).then(response => console.log(response))
}

</script>

<style scoped></style>