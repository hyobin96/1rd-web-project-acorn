<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th>등록일시</th>
                    <th>제목</th>
                    <th>내용</th>
                    <th>이벤트 시작일</th>
                    <th>이벤트 종료일</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="event in eventStore.events" :key="event.id">
                    <td>{{ formatDate(event.createdAt) }}</td>
                    <td>{{ event.title }}</td>
                    <td>{{ event.content }}</td>
                    <td>{{ formatDate(event.startDate) }}</td>
                    <td>{{ formatDate(event.endDate) }}</td>
                    <td><button @click="editEvent(event, $event)">수정</button></td>
                    <td><button @click="deleteEvent(event.id)">삭제</button></td>
                </tr>
            </tbody>
        </table>

        <!-- 수정 박스-->
        <div v-if="showEditBox" :style="{
            position: 'absolute',
            top: editBoxPosition.top + 'px',
            left: editBoxPosition.left + 'px',
            background: 'white',
            border: '1px solid #aaa',
            padding: '12px',
            zIndex: 1000
        }">
            <h4>이벤트 수정</h4>
            <label>제목: <input v-model="selectedEvent.title" /></label><br />
            <label>내용: <textarea v-model="selectedEvent.content"></textarea></label><br />
            <label>시작일: <input type="date" v-model="selectedEvent.startDate" /></label><br />
            <label>종료일: <input type="date" v-model="selectedEvent.endDate" /></label><br />
            <button @click="saveChanges">저장</button>
            <button @click="closeEditBox">취소</button>
        </div>
    </div>
</template>



<script setup>
import { useEventStore } from '@/stores/event';
import { ref, onMounted } from 'vue';
import api from '@/api/axios';

const eventStore = useEventStore()

const showEditForm = ref(false)
const selectedEvent = ref(null)

const editBoxPosition = ref({ top: 0, left: 0 })
const showEditBox = ref(false)

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
            console.log('이벤트 파일 리스트:', event.fileList);
        });
    } catch (err) {
        console.log("행사 정보 불러오기 실패: ", err.response?.data || err.message);
    }
}

/* 수정 버튼 클릭 시 수정 박스 열기*/
function editEvent(event, e) {
    const buttonRect = e.target.getBoundingClientRect()

    editBoxPosition.value = {
        top: buttonRect.bottom + window.scrollY + 4,
        left: buttonRect.left + window.scrollX
    }

    selectedEvent.value = { ...event } //event 객체의 복사본 생성
    console.log('선택된 이벤트:', selectedEvent.value)
    showEditBox.value = true;
}

/* 수정 박스 닫기 */
function closeEditBox() {
  selectedEvent.value = null
  showEditBox.value = false
}

/* 저장 버튼 클릭 시 이벤트 수정하기 */
async function saveChanges() {
    try {
        await api.put('/events', {
            id: selectedEvent.value.id,
            title: selectedEvent.value.title,
            content: selectedEvent.value.content,
            startDate: selectedEvent.value.startDate,
            endDate: selectedEvent.value.endDate,
        })
        await loadEventInfo()
        closeEditBox()
        alert('이벤트 수정 완료!')
    } catch (err) {
        console.log(`수정 실패:`, err.response?.data || err.message);
        alert('이벤트 수정 실패!')

    }

}

/* 이벤트 삭제하기 */
async function deleteEvent(id){
    if (!confirm('정말 삭제하시겠습니까?')) return
    try{
        await api.delete(`/events/${id}`)
        await loadEventInfo()
        alert('이벤트 삭제 완료!')
    }catch(err){
        console.log(`삭제 실패:`, err.response?.data || err.message);
        alert('이벤트 삭제 실패!')
    }
}

onMounted(() => {
    loadEventInfo();
});

</script>

<style scoped>
table {
    border-collapse: collapse;
    /*테투가 겹치는 것 방지 */
}

th,
td {
    border: 1px solid rgba(255, 106, 0, 0.534);
    text-align: center;
    min-width: 200px;
}
</style>