<template>
    <div>
        <div class="menubar" @click="isSidebarVisible = !isSidebarVisible"><svg xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 448 512" width="18" height="18">
                <path
                    d="M0 96C0 78.3 14.3 64 32 64l384 0c17.7 0 32 14.3 32 32s-14.3 32-32 32L32 128C14.3 128 0 113.7 0 96zM0 256c0-17.7 14.3-32 32-32l384 0c17.7 0 32 14.3 32 32s-14.3 32-32 32L32 288c-17.7 0-32-14.3-32-32zM448 416c0 17.7-14.3 32-32 32L32 448c-17.7 0-32-14.3-32-32s14.3-32 32-32l384 0c17.7 0 32 14.3 32 32z" />
            </svg>
        </div>
        <Sidebar :visible="isSidebarVisible" @closeSidebar="isSidebarVisible = false" />
        <Logo />
        <div class="motivation-banner">
            <span>{{ userStore.username }}님, {{ randomMent }}</span>
        </div>
        <main>
            <div class="favorite-playlist-section">
                <MainPlaylistItem />
            </div>
            <div class="playlist-list">
                <div class="playlist-list-header">
                    <div class="playlist-count">
                        <span class="playlist-list-header-menu">플레이리스트 {{ playlistCount }}개</span>
                    </div>
                    <div class="drop-down">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" width="18" height="18"
                            @click="isEditing = !isEditing">
                            <path
                                d="M495.9 166.6c3.2 8.7 .5 18.4-6.4 24.6l-43.3 39.4c1.1 8.3 1.7 16.8 1.7 25.4s-.6 17.1-1.7 25.4l43.3 39.4c6.9 6.2 9.6 15.9 6.4 24.6c-4.4 11.9-9.7 23.3-15.8 34.3l-4.7 8.1c-6.6 11-14 21.4-22.1 31.2c-5.9 7.2-15.7 9.6-24.5 6.8l-55.7-17.7c-13.4 10.3-28.2 18.9-44 25.4l-12.5 57.1c-2 9.1-9 16.3-18.2 17.8c-13.8 2.3-28 3.5-42.5 3.5s-28.7-1.2-42.5-3.5c-9.2-1.5-16.2-8.7-18.2-17.8l-12.5-57.1c-15.8-6.5-30.6-15.1-44-25.4L83.1 425.9c-8.8 2.8-18.6 .3-24.5-6.8c-8.1-9.8-15.5-20.2-22.1-31.2l-4.7-8.1c-6.1-11-11.4-22.4-15.8-34.3c-3.2-8.7-.5-18.4 6.4-24.6l43.3-39.4C64.6 273.1 64 264.6 64 256s.6-17.1 1.7-25.4L22.4 191.2c-6.9-6.2-9.6-15.9-6.4-24.6c4.4-11.9 9.7-23.3 15.8-34.3l4.7-8.1c6.6-11 14-21.4 22.1-31.2c5.9-7.2 15.7-9.6 24.5-6.8l55.7 17.7c13.4-10.3 28.2-18.9 44-25.4l12.5-57.1c2-9.1 9-16.3 18.2-17.8C227.3 1.2 241.5 0 256 0s28.7 1.2 42.5 3.5c9.2 1.5 16.2 8.7 18.2 17.8l12.5 57.1c15.8 6.5 30.6 15.1 44 25.4l55.7-17.7c8.8-2.8 18.6-.3 24.5 6.8c8.1 9.8 15.5 20.2 22.1 31.2l4.7 8.1c6.1 11 11.4 22.4 15.8 34.3zM256 336a80 80 0 1 0 0-160 80 80 0 1 0 0 160z" />
                        </svg>
                        <div v-if="isEditing">
                            <button @click="reset">취소</button>
                            <button @click="completeHandler">완료</button>
                        </div>
                        <div class="category-filter-bar">
                            <span class="playlist-list-header-menu">정렬 기준</span>
                        </div>
                    </div>
                </div>
                <div class="content">
                    <PlaylistDropdownItem v-for="(playlist, index) in playlistStore.playlistArr" :index="index"
                        :isEditing="isEditing" @isEditing="isEditing = false" :deleteArr="deleteArr" @delete="deleteArrAdd"/>
                </div>
            </div>
        </main>
    </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'
import { usePlaylistStores } from '@/stores/playlist';
import { useUserStores } from '@/stores/user';
import MainPlaylistItem from '@/components/MainPlaylistItem.vue';
import PlaylistDropdownItem from '@/components/PlaylistDropdownItem.vue';
import Sidebar from '@/components/Sidebar.vue';
import Logo from '../components/Logo.vue'

const userStore = useUserStores()
const playlistStore = usePlaylistStores()

const isEditing = ref(false)
const isSidebarVisible = ref(false)
const deleteArr = ref([])

defineEmits(['isEditing', 'deleteArrAdd'])

const reset = () => {
    deleteArr.value = []
    playlistStore.getPlaylist()
    isEditing.value = false
}

const deleteArrAdd = (playlistId, index) => {
    console.log(playlistId)
    console.log(index)
    deleteArr.value.push(playlistId)
    playlistStore.playlistArr.splice(index, 1)
}
const completeHandler = () => {
    playlistStore.deletePlaylist(deleteArr.value)
    isEditing.value = false
    deleteArr.value = []
}

const playlistCount = computed(() => {
    return playlistStore.playlistArr.length
})

const motivationMentList = [
    "오늘도 힘차게 시작해볼까요? 💪",
    "끝까지 함께 해봐요! 🙌",
    "한 걸음 더 내딛어봐요! 👣",
    "우리 같이 포기하지 말아요! 🥇",
    "조금만 더 힘내볼까요? 🚀",
    "오늘도 멋지게 도전해봐요! ✨",
    "함께 웃으면서 해봐요! 😊",
    "우리 계속 꾸준히 가볼까요? 🚶",
    "오늘도 최선을 다해봐요! 🏆",
    "힘들 땐 잠깐 쉬어가도 괜찮아요! 🌈",
    "다시 시작해볼까요? 언제나 응원해요! 📣",
    "오늘의 노력은 내일의 힘이 돼요! 🕰️",
    "우리 같이 성장해봐요! 🌱",
    "더 멋진 내일을 위해 힘내요! 🔥",
    "함께라면 뭐든 해낼 수 있어요! 🤗",
    "운동하고 시원한 물 한 잔 어때요? 🥤",
    "건강을 위해 오늘도 챙겨봐요! ❤️",
    "매일 조금씩 발전해봐요! 📈",
    "우리 목표까지 계속 달려봐요! 🏃",
    "오늘도 잘하고 있어요! 응원해요! 🥳",
    "끝까지 함께 완주해봐요! 🏅",
    "지치면 잠시 멈춰도 괜찮아요! 🛋️",
    "오늘의 선택이 멋진 변화를 가져와요! 🔄",
    "자신을 믿고 도전해봐요! 🙏",
    "지금 포기하지 말고 한 번 더 해봐요! 🦾",
    "꾸준함이 가장 큰 힘이에요! 계속 해봐요! 🎯",
    "오늘도 응원합니다! 할 수 있어요! 📢",
    "우리 함께 힘내서 해봐요! 🏋️‍♂️",
    "어제보다 더 멋진 자신을 만나봐요! 🌟",
    "잘하고 있으니 계속 가볼까요? ➡️"
];

const randomMent = ref(motivationMentList[Math.floor(Math.random() * motivationMentList.length)]);

onMounted(async () => {
    await playlistStore.getPlaylist()
    playlistStore.currentPlaylistId = 0
    playlistStore.currentPlaylistItemId = 0
})

</script>


<style scoped>
.motivation-banner {
    width: 1262.5px;
    height: 56px;
    border: 1px solid #D9D9D9;
    padding-left: 15px;
    margin: 0 auto;
    display: flex;
    /* flex로 중앙정렬 */
    align-items: center;
    /* 세로 중앙 */
}

.favorite-playlist-section {
    /* border: 1px solid yellow; */
    /* width: fit-content; */
    width: 1262.5px;
    margin: 0 auto;
}

.favorite-playlist {
    /* border: 1px solid saddlebrown; */
    height: 365.33px;
    flex: 1;
    /* 자식 요소가 부모의 가로 폭을 똑같이 나눠서 꽉 차게 배치 */
}

.playlist-list {
    width: 1262.5px;
    /* border: 1px solid red; */
    margin: 0 auto;
    padding-top: 10px;
    padding-bottom: 40px;
}

.playlist-list-header {
    /* border: 1px solid yellow; */
    display: flex;
    justify-content: space-between;
    /* height: 28px; */
}

/* span.playlist-list-header-menu{
    color: aquamarine;
} */

.playlist-count {
    padding-left: 15px;
}

span.playlist-title {
    font-size: 20px;
}

.playlist-thumbnail {
    width: 240px;
    height: 50px;
}

.content {
    padding-top: 8px;
    padding-bottom: 8px;
    overflow-y: auto;
    height: 420px;
    /* 스크롤 바 높이 */
}

#thumbnail {
    margin-right: 8px;
}

.content>#meta>span {
    font-size: 16px;
    line-height: 22px;
    font-weight: 700;
    height: 44px;
}

.favorite-playlist img {
    width: 100%;
    height: 100%;
    object-fit: contain;
    display: block;
    /* inline 공간 제거 */
}

.category-filter-bar {
    padding-right: 15px;
}

.menubar {
    position: fixed;
    /* 화면 고정 위치 */
    top: 10px;
    /* 화면 위에서 20px 아래 */
    left: 10px;
    /* 화면 오른쪽에서 20px 떨어짐 */
    cursor: pointer;
    /* 커서가 손 모양으로 변경 */
}
</style>