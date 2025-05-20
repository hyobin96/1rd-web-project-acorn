<template>
    <div>
        <h2>LoginForm</h2>
        <div :class="isIdFocus ? 'input_item_id_focus' : 'input_item_id'">
            <input type="text" id="id" name="id" 
            @focus="isIdFocus = true" @blur="isIdFocus = false" v-model="id" autocomplete="off">
            <label for="id" :class="{floated: isIdFocus || id}">아이디</label></div>
        <div :class="isPwFocus ? 'input_item_pw_focus' : 'input_item_pw'">
            <input type="password" id="pw" name="pw" 
            @focus="isPwFocus = true" @blur="isPwFocus = false" v-model="pw" autocomplete="off">
            <label for="pw" :class="{floated: isPwFocus || pw}">비밀번호</label></div>
        <br>
        <button @click="login">로그인</button>
    </div>
</template>

<script setup>
import {ref} from 'vue';
const isIdFocus = ref(false)
const isPwFocus = ref(false)

const id = ref('');
const pw = ref('');

function login(){
  console.log('입력된 아이디:', id.value);
  console.log('입력된 비밀번호:', pw.value);
  
}
</script>

<style scoped>
</style>