<template>
    <div>
        <form @submit.prevent="submitForm" class="event-create-section">
            <p>이벤트 생성</p>
            <div>
                <label for="title">제목<span>*</span></label>
                <input type="text" id="title" v-model="form.title" required>
            </div>
            <div>
                <label for="content">내용<span>*</span></label>
                <input type="text" id="content" v-model="form.content" required>
            </div>
            <div>
                <label for="start-date">이벤트 시작일<span>*</span></label>
                <input type="date" id="start-date" v-model="form.startDate" required>
            </div>
            <div>
                <label for="end-date">이벤트 종료일<span>*</span></label>
                <input type="date" id="end-date" v-model="form.endDate" required>
            </div>
            <div>
                <label for="thumbnail">썸네일<span>*</span></label>
                <input type="file" id="thumbnail" @change="handleThumbnailChange" required />
            </div>
            <div>
                <label for="contentImage">본문 이미지</label>
                <input type="file" id="contentImage" @change="handleContentImageChange" multiple />
            </div>
            <div class="btn-container"><button type="submit">이벤트 생성</button></div>
        </form>
        <EventList />
    </div>
</template>

<script setup>
import { ref } from 'vue';
import api from '@/api/axios.js';
import EventList from './EventList.vue';

//입력 폼 데이터
const form = ref({
    title: '',
    content: '',
    startDate: '',
    endDate: '',
})

//썸네일 단일 파일
const thumbnailFile = ref(null)

//본문 이미지 다중 파일
const selectedFiles = ref([])

// 썸네일 파일 저장
const handleThumbnailChange = (event) => {
    thumbnailFile.value = event.target.files[0] || null
}

// 본문 이미지 파일 저장
const handleContentImageChange = (event) => {
    selectedFiles.value = Array.from(event.target.files)
}

const submitForm = async () => {
    try {

        // 날짜 유효성 검사
        const start = new Date(form.value.startDate);
        const end = new Date(form.value.endDate);

        if (start >= end) {
            alert('이벤트 시작일은 종료일보다 빠르거나 같아야 합니다.');
            return;
        }

        // 폼데이터로 바꿔 보내기
        const formData = new FormData()
        formData.append('title', form.value.title)
        formData.append('content', form.value.content)
        formData.append('startDate', form.value.startDate)
        formData.append('endDate', form.value.endDate)
        formData.append('thumbnail', thumbnailFile.value)

        console.log(thumbnailFile.value);


        selectedFiles.value.forEach(file => {
            formData.append('files', file)
        })

        console.log([...selectedFiles.value]);


        await api.post('/events', formData)

        alert('이벤트 등록 성공!')

        // 입력 폼 초기화
        form.value = {
            title: '',
            content: '',
            startDate: '',
            endDate: '',
        }
        thumbnailFile.value = null
        selectedFiles.value = []


    } catch (err) {
        alert('이벤트 등록 실패!')
        console.log(err.response?.data || err.message)

    }
}




</script>

<style scoped>
form {
    border: 1px solid #d9d9d9;
    width: 400px;
}

form>div {
    margin-left: 15px;
}

button {
    margin-top: 10px;
    margin-bottom: 10px;
    margin-right: 15px;
    /* border: 1px solid darkblue; */
    background-color: #1c1c1c;
    color: #fff;
}

label {
    margin-right: 10px;
    margin-bottom: 5px;
}

.event-create-section {
    margin-bottom: 20px;
}

form>p {
    margin-top: 15px;
    margin-left: 15px;
    font-size: 22px;
}

span {
    color: rgb(234, 75, 75);
    font-size: 20px;
}

.btn-container{
    display: flex;
    justify-content: end;
}

</style>