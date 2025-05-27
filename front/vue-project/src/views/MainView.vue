<template>
    <div>
        <div class="menubar" @click="isSidebarVisible = !isSidebarVisible"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" width="18" height="18">
                <path
                    d="M0 96C0 78.3 14.3 64 32 64l384 0c17.7 0 32 14.3 32 32s-14.3 32-32 32L32 128C14.3 128 0 113.7 0 96zM0 256c0-17.7 14.3-32 32-32l384 0c17.7 0 32 14.3 32 32s-14.3 32-32 32L32 288c-17.7 0-32-14.3-32-32zM448 416c0 17.7-14.3 32-32 32L32 448c-17.7 0-32-14.3-32-32s14.3-32 32-32l384 0c17.7 0 32 14.3 32 32z" />
            </svg>
        </div>
        <Sidebar :visible="isSidebarVisible" @closeSidebar="isSidebarVisible = false" />
        <Logo />
        <div class="motivation-banner">
            <span>{{userStore.username}}님, {{ randomMent }}</span>
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
                        <div class="category-filter-bar">
                            <span class="playlist-list-header-menu">정렬 기준</span>
                        </div>
                    </div>
                </div>
                <div class="content">
                    <PlaylistDropdownItem v-for="(playlist, index) in playlistStore.playlistArr" :index="index" />
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

const isSidebarVisible = ref(false)

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
    height: 420px; /* 스크롤 바 높이 */
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
    position: fixed;  /* 화면 고정 위치 */
    top: 10px;         /* 화면 위에서 20px 아래 */
    left: 10px;       /* 화면 오른쪽에서 20px 떨어짐 */
    cursor: pointer;   /* 커서가 손 모양으로 변경 */
}
</style>