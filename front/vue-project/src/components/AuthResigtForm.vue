<template>
    <div class="container">
        <h2>회원가입</h2>
        <br>
        <form @submit.prevent="regist">
            <div class="d-grid col-8 mx-auto gap-2">
                <input class="form-control form-control-lg" type="text" placeholder="아이디" v-model="form.username"
                    @blur="handlerUsername" />
                <span>{{ errors.username }}</span>

                <input class="form-control form-control-lg" type="password" placeholder="비밀번호" v-model="form.password"
                    @blur="handlerPassword" />
                <span>{{ errors.password }}</span>

                <input class="form-control form-control-lg" type="password" placeholder="비밀번호 확인"
                    v-model="form.confirmPassword" @blur="handlerConfirmPassword" />
                <span>{{ errors.confirmPassword }}</span>

                <input class="form-control form-control-lg" type="text" placeholder="닉네임" v-model="form.nickname"
                    @blur="handlerNickname()" />
                <span>{{ errors.nickname }}</span>

                <input class="form-control form-control-lg" type="email" placeholder="이메일" v-model="form.email"
                    @blur="handlerEmail()" />
                <span>{{ errors.email }}</span>
            </div>
            <br>
            <div class="d-flex justify-content-center gap-2">
                <button type="submit" class="btn btn-outline-info">등록</button>
                <button type="reset" @click="router.push({ name: 'login' })" class="btn btn-outline-info">취소</button>
            </div>
        </form>

    </div>
</template>

<script setup>
import { useUserStores } from '@/stores/user';
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router'
import axios from '@/api/axios'
import {
    validateUsername,
    validatePassword,
    validateConfirmPassword,
    validateEmail,
    validateNickname,
} from '@/utils/validation'

const router = useRouter()

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

const handlerUsername = async () => {
    const { message, success: ok } = await validateUsername(form.username)
    errors.username = message
    success.username = ok
}

const handlerPassword = () => {
    const { message, success: ok } = validatePassword(form.password)
    errors.password = message
    success.password = ok
}

const handlerConfirmPassword = () => {
    const { message, success: ok } = validateConfirmPassword(form.password, form.confirmPassword)
    errors.confirmPassword = message
    success.confirmPassword = ok
}

const handlerNickname = async () => {
    const { message, success: ok } = await validateNickname(form.nickname)
    errors.nickname = message
    success.nickname = ok
}

const handlerEmail = async () => {
    const { message, success: ok } = await validateEmail(form.email)
    errors.email = message
    success.email = ok
}

//모든 과정 검사
//등록과정
const regist = async () => {
    if (!(success.username && success.password &&
        success.confirmPassword && success.nickname && success.email)) {
        alert('유효하지 않은 항목이 있습니다.')
        return
    }
    const { confirmPassword, ...payload } = form
    await axios.post(
        'users',form,
    ).then(response => {
        alert('회원가입 성공!')
    }).catch(err => {
        alert('유효하지 않은 항목이 있습니다.')
    })

    router.push({ name: 'login' })
}
</script>

<style scoped></style>