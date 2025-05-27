<template>
    <div class="signup-container">
        <div class="title">
            <h2>회원가입</h2>
        </div>
        <form @submit.prevent="regist" class="form-container">
            <div class="content">
                <input type="text" placeholder="아이디" v-model="form.username" @blur="handlerUsername" class="id" />
                <span>{{ errors.username }}</span>

                <input type="password" placeholder="비밀번호" v-model="form.password" @blur="handlerPassword" class="pw" />
                <span>{{ errors.password }}</span>

                <input type="password" placeholder="비밀번호 확인" v-model="form.confirmPassword"
                    @blur="handlerConfirmPassword" class="check-pw" />
                <span>{{ errors.confirmPassword }}</span>

                <input type="text" placeholder="닉네임" v-model="form.nickname" @blur="handlerNickname()"
                    class="nickname" />
                <span>{{ errors.nickname }}</span>

                <input type="email" placeholder="이메일" v-model="form.email" @blur="handlerEmail()" class="email" />
                <span>{{ errors.email }}</span>
            </div>
            <br>
            <div class="butttons">
                <div class="signup"><button type="submit">회원가입하기</button></div>
                <div class="check-has-id"><span>이미 아이디가 있으신가요?</span>
                    <button type="reset" @click="router.push({ name: 'login' })">로그인</button>
                </div>
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
        'users', form,
    ).then(response => {
        alert('회원가입 성공!')
    }).catch(err => {
        alert('유효하지 않은 항목이 있습니다.')
    })

    router.push({ name: 'login' })
}
</script>

<style scoped>
.signup-container {
    width: 500px;
    margin: 120px auto;
    height: 632.75px;
    border: 1px solid #D9D9D9;
    border-radius: 15px;
}

.title {
    padding-top: 52px;
    padding-bottom: 34px;
    /* border: 1px solid violet; */
    text-align: center;
}

.content {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.content span {
    color: rgb(138, 138, 138);
    font-size: 14px;
    margin-top: -8px;
    margin-bottom: 4px;
}

.content>input {
    width: 400px;
    margin-bottom: 16px;
    height: 43.9px;
    border-radius: 4px;
    padding-left: 15px;
    border: 2px solid #D9D9D9;
}

.butttons{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

/* 버튼 속성 초기화 */
button {
    border: none;
    padding: 0;
    margin: 0;
    background: none;
    cursor: pointer;
}

.signup>button {
    width: 400px;
    height: 43.9px;
    border-radius: 4px;
    margin-bottom: 16px;
    padding-left: 15px;
    border: 2px solid #D9D9D9;
    background-color: #f9a63a;
}

.check-has-id > span{
    /* border: 1px solid firebrick; */
    margin-right: 5px;
}

.check-has-id > button{
    /* border: 1px solid red; */
    color: #f9a63a;
    text-decoration: underline;
}

</style>