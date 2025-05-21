<template>
    <div class="container">
        <h2>회원가입</h2>
        <br>
        <form @submit.prevent="regist">
            <div class="d-grid col-8 mx-auto gap-2">
                <input class="form-control form-control-lg" type="text" placeholder="아이디"
                    v-model="form.username" @blur="async () => { errors.username = await validateId(form.username) }"/>
                <span>{{ errors.username }}</span>
                <input class="form-control form-control-lg" type="password" placeholder="비밀번호"
                    v-model="form.password" @blur="errors.password = validatePassword(form.password)"/>
                <span>{{ errors.password }}</span>
                <input class="form-control form-control-lg" type="password" placeholder="비밀번호 확인"
                    v-model="form.confirmPassword" @blur="errors.confirmPassword = validateConfirmPassword(form.password, form.confirmPassword)" />
                <span>{{ errors.confirmPassword }}</span>
                <input class="form-control form-control-lg" type="text" placeholder="닉네임"
                    v-model="form.nickname" @blur="async() => { errors.nickname = await validateNickname(form.nickname) }"/>
                <span>{{ errors.nickname }}</span>
                <input class="form-control form-control-lg" type="email" placeholder="이메일"
                    v-model="form.email" @blur="async() => { errors.email = await validateEmail(form.email) }" />
                <span>{{ errors.email }}</span>
            </div>
            <br>
            <div class="d-flex justify-content-center gap-2">
                <button type="submit" class="btn btn-outline-info">등록</button>
                <button class="btn btn-outline-info">취소</button>

            </div>
        </form>
    </div>
</template>

<script setup>
import { useUserStores } from '@/stores/user';
import { reactive, ref } from 'vue';
import {
    validateId,
    validatePassword,
    validateConfirmPassword,
    validateEmail,
    validateNickname,
} from '@/utils/validation'

const store = useUserStores()
const form = reactive({
    username: '',
    password: '',
    confirmPassword: '',
    email: '',
    nickname: ''
})

const errors = reactive({
    username: '',
    password: '',
    confirmPassword: '',
    email: '',
    nickname: ''
})

const success = reactive({
    username: false,
    password: false,
    confirmPassword: false,
    email: false,
    nickname: false,
})

//등록과정
//모든 과정 검사
const regist = async () => {
    console.log(form)
    
}
</script>

<style scoped></style>