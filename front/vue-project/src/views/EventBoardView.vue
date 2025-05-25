<template>
    <div>
        <EventBoardHeader :totalCount="eventStore.events.length"/>
        <div class="event-item-container">
            <div class="event-item" v-for="event in eventStore.events" :key="event.id">
                <div class="event-thumbnail">
                    <img :src="getThumbnailPath(event.fileList)" alt="썸네일">
                </div>
                <div class="event-info">
                    <div class="event-title">
                        <span>{{ event.title }}</span>
                    </div>
                    <div class="event-status">
                        <span>{{ new Date(event.endDate) > new Date() ? "진행중" : "종료" }}</span>
                        <span>{{ formatDate(event.startDate) }}~{{ formatDate(event.endDate) }}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import EventBoardHeader from '@/components/EventBoardHeader.vue'
import { onMounted } from 'vue';
import api from '@/api/axios';
import { useEventStore } from '@/stores/event';

const eventStore = useEventStore()

/* 썸네일 경로 가져오기 */
function getThumbnailPath(fileList) {
    if (!fileList || fileList.length === 0) return ''
    const thumbnails = fileList.filter(file => file.thumbnail === true)
    if(thumbnails.length ===0) return ''

    //최근에 업로드된 파일을 선택
    thumbnails.sort((a,b)=> new Date(b.uploadedAt) - new Date(a.uploadedAt))
    
    return `https://localhost:9443${thumbnails[0].filePath}`
}

/* 날짜 형식에 맞게 고치기 */
function formatDate(dateStr) {
    if (!dateStr) return ''
    const [year, month, day] = dateStr.split('-')
    return `${year.slice(2)}/${month}/${day}`
}

/* 이벤트 정보 로딩하기 */
async function loadEventInfo() {
    try {
        const res = await api.get("/events")
        eventStore.setEvents(res.data)

        eventStore.events.forEach((event, index) => {
            console.log(`Event ${index + 1} - title: ${event.title}`);
            console.log('이벤트 파일 리스트:',  event.fileList);
        });
    } catch (err) {
        console.log("행사 정보 불러오기 실패: ", err.response?.data || err.message);
    }
}

onMounted(() => {
    loadEventInfo();
});

</script>

<style scoped>
.event-item-container {
    border: 1px solid hotpink;
    width: 1200px;
    /*임의로 정한 컨텐츠 가로 길이 */
    margin: 0 auto;
    /* 가운데 정렬 */
    display: flex;
    flex-wrap: wrap;
}

.event-item {
    width: 50%;
    /* event-item-container의 반을 차지 */
    height: 360px;
    border: 1px solid navy;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.event-thumbnail {
    flex: 8;
    overflow: hidden;
}

.event-thumbnail img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    display: block;
}

.event-info {
    flex: 2;
    border: 1px solid yellowgreen;
    display: flex;
    flex-direction: column;
}

/* 이벤트 제목 */
.event-title>span {
    margin-left: 8px;
    font-size: 20px;
    font-weight: 700;
}

/* 이벤트 진행 상태 */
.event-status>span {
    border: 1px solid red;
    margin-left: 8px;
}
</style>