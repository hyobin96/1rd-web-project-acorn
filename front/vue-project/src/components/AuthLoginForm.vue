<template>
    <div>
        <h2>LoginForm</h2>
        <div :class="isIdFocus ? 'input_item_id_focus' : 'input_item_id'">
            <input type="text" id="id" name="id" @focus="isIdFocus = true" @blur="isIdFocus = false" v-model="store.username"
                autocomplete="off">
            <label for="id" :class="{ floated: isIdFocus || id }">아이디</label>
        </div>
        <div :class="isPwFocus ? 'input_item_pw_focus' : 'input_item_pw'">
            <input type="password" id="pw" name="pw" @focus="isPwFocus = true" @blur="isPwFocus = false"
                v-model="password" autocomplete="off">
            <label for="pw" :class="{ floated: isPwFocus || pw }">비밀번호</label>
        </div>
        <br>

        <button @click="store.login(password)">로그인</button>
        <br>
        <span>{{ store.message }}</span>
    </div>
</template>

<script setup>
import axios from 'axios'
import { ref } from 'vue'
import { useUserStores } from '@/stores/user'

const store = useUserStores()
const password = ref('')

const getPlaylist = async () => {
    await axios.get(
        "http://localhost:8080/api/playlist/1",
        {
            withCredentials: true
        }
    ).then(response => console.log(response))
}

const isIdFocus = ref(false)
const isPwFocus = ref(false)

const id = ref('');
const pw = ref('');

</script>

<style scoped></style>
