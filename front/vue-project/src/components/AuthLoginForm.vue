<template>
    <div class="container text-center">
        <h2><i class="bi bi-award"></i></h2>
        <div class="d-grid col-8 mx-auto">
            <div :class="isIdFocus ? 'input_item_id_focus' : 'input_item_id'">
                <input type="text" id="id" name="id" class="form-control form-control-lg" @focus="isIdFocus = true"
                    @blur="isIdFocus = false" v-model="store.username" autocomplete="off">
                <label for="id" :class="{ floated: isIdFocus || id }"></label>
            </div>
            
            <div :class="isPwFocus ? 'input_item_pw_focus' : 'input_item_pw'">
                <input type="password" id="pw" class="form-control form-control-lg" name="pw" @focus="isPwFocus = true"
                    @blur="isPwFocus = false" v-model="password" autocomplete="off">
                <label for="pw" :class="{ floated: isPwFocus || pw }"></label>
            </div>
        </div>
        <div class="d-flex gap-1 col-10">
            <button @click="router.push({ name: 'regist' })" class="btn btn-outline-info btn-sm ms-auto">회원가입</button>
            <button class="btn btn-outline-info btn-sm">비밀번호 찾기</button>
        </div>
        <br>
        <button class="btn btn-primary" @click="async () => await store.login(password)">로그인</button>
        <span>{{ store.message }}</span>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useUserStores } from '@/stores/user'
import { useRouter } from 'vue-router'

const store = useUserStores()
const password = ref('')
const router = useRouter()

const isIdFocus = ref(false)
const isPwFocus = ref(false)

const id = ref('');
const pw = ref('');

</script>

<style scoped></style>
