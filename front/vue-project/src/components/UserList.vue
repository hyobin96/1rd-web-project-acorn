<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>email</th>
                    <th>닉네임</th>
                    <th>가입일시</th>
                    <th>상태</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(user, index) in users" :key="user.id">
                    <td>{{ index + 1 }}</td>
                    <td>{{ user.username }}</td>
                    <td>{{ user.email }}</td>
                    <td>{{ user.nickname }}</td>
                    <td>{{ formatDate(user.createdAt) }}</td>
                    <td><button @click="editEvent(user)">{{ user.isDeleted === true || user.isDeleted === 'true' || user.isDeleted === 1 || user.isDeleted === '1' ? '정지 회원' : '정상 회원' }}</button></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from '@/api/axios'

const users = ref([])

/* 날짜 형식에 맞게 고치기 */
function formatDate(dateStr) {
    if (!dateStr) return ''
    const [year, month, day] = dateStr.split('-')
    return `${year.slice(2)}/${month}/${day}`
}

/* 사용자 정보 로딩하기 */
async function loadUserInfo() {
  try {
    const { data } = await api.get("/users");
  console.log("서버에서 받아온 원본 데이터:", data); 
    users.value = data.map(user => ({
      ...user,
      isDeleted: user.isDeleted === true || user.isDeleted === 'true' || user.isDeleted === 1 || user.isDeleted === '1'
    }));

  } catch (err) {
    console.log(err.response?.data || err.message);
  }
}

/* 사용자 정지하기 */
async function editEvent(user) {
    if (user.isDeleted) {
        alert('이미 정지된 사용자입니다.');
        return;
    }

    if (!confirm('정지 처리 하시겠습니까?')) return;
    try {
        await api.put(`/users/${user.id}`)
        console.log(`유저는 왜 정지가 계속 되는가?` ,user.isDeleted);
        
        alert('정지 처리 완료!')
        await loadUserInfo(); 

    } catch (err) {
        console.log(err.response?.data || err.message);
        alert('정지 처리 실패!');

    }
}

onMounted(() => {
    loadUserInfo();
});
</script>

<style scoped>
table {
    border-collapse: collapse;
    /*테두리가 겹치는 것 방지 */
}

th,
td {
    border: 1px solid rgba(255, 106, 0, 0.534);
    text-align: center;
    min-width: 200px;
}
</style>