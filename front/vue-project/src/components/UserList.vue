<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>email</th>
                    <th>닉네임</th>
                    <th>가입일</th>
                    <th>정지</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(user, index) in users" :key="user.id">
                    <td>{{ index + 1 }}</td>
                    <td>{{ user.username }}</td>
                    <td>{{ user.email }}</td>
                    <td>{{ user.nickname }}</td>
                    <td>{{ formatDate(user.createdAt) }}</td>
                    <td><button @click="editEvent(user)">정지</button></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import api from '@/api/axios'

const users = ref([])

/* 날짜 형식에 맞게 고치기 */
function formatDate(dateStr){
    if(!dateStr) return ''
    const [year, month, day] = dateStr.split('-')
    return `${year.slice(2)}/${month}/${day}`
}

/* 사용자 정보 로딩하기 */
async function loadUserInfo() {
    try{
        const {data} = await api.get("/users")
        users.value = data
    }catch(err){
        console.log(err.response?.data || err.message);
        
    }
    
}

/* 사용자 정지하기 */
function editEvent(user) {
    console.log('정지할 유저:', user);
}

onMounted(() => {
    loadUserInfo();
});
</script>

<style scoped>

</style>