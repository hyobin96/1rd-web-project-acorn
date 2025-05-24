<template>
    <div>
        <EventBoardHeader />
        <div class="event-item-container">
        <div class="event-item" v-for="event in eventStore.events" :key="event.id">
            <h3>이벤트 제목: {{ event.title }}</h3>
            <p>이벤트 시작일: {{ event.startDate }}</p>
            <p>이벤트 종료일: {{ event.endDate }}</p>
            <img src="" alt="썸네일">
        </div>
        </div>
    </div>
</template>

<script setup>
import EventBoardHeader from '@/components/EventBoardHeader.vue'
import {onMounted} from 'vue';
import api from '@/api/axios';
import { useEventStore } from '@/stores/event';

const eventStore = useEventStore()

async function loadEventInfo(){
    try{
        const res = await api.get("/events")
        eventStore.setEvents(res.data)
        
        eventStore.events.forEach((event, index) => {
        console.log(`Event ${index + 1} - title: ${event.title}`);
        console.log(event.fileList); 
});

        
        
    } catch(err){
        console.log("행사 정보 불러오기 실패: ", err.response?.data || err.message);
    }
}

onMounted(() => {
    loadEventInfo();
});

</script>

<style scoped>

</style>