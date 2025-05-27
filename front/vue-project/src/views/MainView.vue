<template>
    <div>
        <div class="menubar" @click="isSidebarVisible = !isSidebarVisible"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" width="18" height="18">
                <path
                    d="M0 96C0 78.3 14.3 64 32 64l384 0c17.7 0 32 14.3 32 32s-14.3 32-32 32L32 128C14.3 128 0 113.7 0 96zM0 256c0-17.7 14.3-32 32-32l384 0c17.7 0 32 14.3 32 32s-14.3 32-32 32L32 288c-17.7 0-32-14.3-32-32zM448 416c0 17.7-14.3 32-32 32L32 448c-17.7 0-32-14.3-32-32s14.3-32 32-32l384 0c17.7 0 32 14.3 32 32z" />
            </svg>
        </div>
        <Sidebar :visible="isSidebarVisible" @closeSidebar="isSidebarVisible = false" />
        <nav class="navbar">
            <div class="logo-container"><a class="logo-icon"><img class="logo-img" src="@/assets/logo.png"
                        alt="LOGO" /></a></div>
        </nav>
        <div class="motivation-banner">
            <span>{{ randomMent }}</span>
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

const userStore = useUserStores()
const playlistStore = usePlaylistStores()

const isSidebarVisible = ref(false)

const playlistCount = computed(() => {
    return playlistStore.playlistArr.length
})

const motivationMentList = ["오늘도 힘내세요! 💪",
    "한 걸음 한 걸음이 변화를 만듭니다! 🏃‍♂️",
    "지금 이 순간도 멋져요! ✨",
    "포기하지 않는 당신이 최고예요! 🥇",
    "조금만 더! 끝까지 화이팅! 🙌",
    "어제의 나를 이겨냅시다! 🏆",
    "운동하는 당신, 너무 멋져요! 😎",
    "작은 노력이 큰 변화를 만듭니다! 🌱",
    "오늘도 스스로를 칭찬하세요! 👏",
    "흘린 땀만큼 성장합니다! 💦",
    "조금씩, 꾸준히! 계속 가보자고! 🚶‍♀️",
    "운동은 최고의 자기 선물이에요! 🎁",
    "할 수 있다! 나는 할 수 있다! 🔥",
    "지금 포기하면 어제의 나와 똑같아요! ⏳",
    "건강이 최고! 오늘도 건강 챙기기! ❤️",
    "넘어져도 괜찮아, 다시 일어나면 돼! 🦸",
    "매일매일 더 강해지는 중! 🏋️‍♂️",
    "오늘도 완주! 내일도 화이팅! 🥳",
    "너무 잘하고 있어요! 계속 전진! ➡️",
    "힘들수록 웃어봐요! 😊",
    "운동하는 당신을 응원합니다! 📣",
    "목표까지 앞으로 한 걸음! 👣",
    "함께라서 더 힘이 나요! 🤗",
    "내 몸은 내가 책임진다! 🩺",
    "운동 끝나고 먹는 물이 꿀맛! 🥤",
    "지금의 노력이 내일의 나를 만듭니다! 🕰️",
    "모두가 쉬어도 나는 달린다! 🏃",
    "오늘의 선택이 내일을 바꾼다! 🔄",
    "나 자신을 믿어! 믿는 만큼 성장해! 🙏",
    "꾸준함이 곧 실력! 계속 도전! 🎯",
];

const randomMent = ref(motivationMentList[Math.floor(Math.random() * motivationMentList.length)]);

onMounted(async () => {
    await playlistStore.getPlaylist()
    playlistStore.currentPlaylistId = 0
    playlistStore.currentPlaylistItemId = 0
})

</script>


<style scoped>
.navbar {
    /* border: 2px solid yellowgreen; */
    width: 1262.5px;
    height: 215px;
    margin: 0 auto;
    padding-left: 10px;
}

.logo-img {
    width: 260px;
    height: 70px;
    display: block;
}

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