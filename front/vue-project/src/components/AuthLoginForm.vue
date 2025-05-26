<template>
    <div class="container">
        <div class="login-container">
            <h2>로고</h2>
        </div>
        <div class="id-container">
            <input type="text" id="id" name="id" v-model="store.username" autocomplete="off" placeholder="아이디를 입력해주세요">
            <!-- <label for="id">아이디</label> -->
        </div>
        <div class="pw-container">
            <input type="password" id="pw" name="pw" v-model="password" autocomplete="off" placeholder="비밀번호를 입력해주세요">
            <!-- <label for="pw">비밀번호</label> -->
        </div>
        <div class="auth-actions">
            <button @click="router.push({ name: 'regist' })">회원가입</button>
            <!-- <span style="margin: 0 5px;">|</span> -->
            <!-- <button> 비밀번호 찾기</button> -->
        </div>
        <br>
        <span>{{ message }}</span>
        <div class="login-button"><button @click="store.login(password).then(res => message = res)">로그인</button></div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useUserStores } from '@/stores/user'
import { useRouter } from 'vue-router'

const store = useUserStores()
const password = ref('')
const message = ref('')
const router = useRouter()


</script>

<style scoped>
.container {
    border: 1px solid darkblue;
    width: 500px;
    margin: 120px auto;
    height: 632.75px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    /* 세로 중앙 정렬 */
    align-items: center;
    /* 가로 중앙 정렬 */
    /* background: #D0D7D9; */
}

.login-container {
    /* margin-top: ; */
    padding-bottom: 20px;
}

/* 인풋 창 크기 조정 */
.id-container>input {
    width: 400px;
    margin-bottom: 16px;
    height: 43.9px;
    border-radius: 4px;
    /* margin-top: 250px; */
    padding-left: 15px;
    border: 2px solid #b9b9b9;
}

.pw-container>input {
    width: 400px;
    margin-bottom: 16px;
    height: 43.9px;
    border-radius: 4px;
    padding-left: 15px;
    border: 2px solid #b9b9b9;
}

.auth-actions {
    /* border: 1px solid firebrick; */
    width: 400px;
    display: flex;
    justify-content: flex-end;
}

/* 버튼 속성 초기화 */
button {
    border: none;
    padding: 0;
    margin: 0;
    background: none;
    cursor: pointer;
}

.login-button {
    width: 400px;
    height: 43.9px;
    border: 2px solid #b9b9b9; 
    display: flex;
    justify-content: center;
    background: #9CA6A4;
    /* #F2F2F2 */
}

.login-button>button {
    color: #F2F2F2;
    font-weight: 700;
    width: 100%;
    height: 100%;

}

/* input창이 활성화 됐을 때 테두리 색 변경 */
.id-container>input:focus,
.pw-container>input:focus {
    outline: none;
    /* 기본 검은색 테두리 제거 */
    border: 2px solid #000000;
}
</style>
