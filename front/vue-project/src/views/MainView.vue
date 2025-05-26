<template>
    <div>
        <Sidebar />
        <nav class="navbar">
            <div class="logo-container"><a class="logo-icon"><img class="logo-img" src="https://i.imgur.com/9YO79MC.png"
                        alt="LOGO" /></a></div>
        </nav>
        <div class="motivation-banner">
            <span>응원 멘트 공간</span>
        </div>
        <main>
            <div class="favorite-playlist-section">
                <MainPlaylistItem v-for="(playlist, index) in playlistStore.playlistArr.slice(0, 3)" :index="index" />
            </div>
            <div class="playlist-list">
                <div class="playlist-list-header">
                    <div class="playlist-count">
                        <span class="playlist-list-header-menu">플레이리스트 {{ playlistCount }}개</span>
                    </div>
                    <div class="drop-down">
                        <button class="category-filter-bar" type="button">
                            <span class="playlist-list-header-menu">업로드 날짜</span>
                        </button>
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

const playlistCount = computed(() => {
    return playlistStore.playlistArr.length
})

onMounted(async () => {
    await playlistStore.getPlaylist()
    playlistStore.currentPlaylistId = 0
    playlistStore.currentPlaylistItemId = 0
})

</script>

<style scoped>
.navbar {
    border: 2px solid yellowgreen;
    width: 1262.5px;
    height: 215px;
    margin: 0 auto;
}

.logo-img {
    width: 260px;
    height: 70px;
    display: block;
}

.motivation-banner {
    width: 1050px;
    height: 56px;
    /* border: 1px solid blueviolet; */
    margin: 0 auto;
    /* 가운데 정렬 */
}

.favorite-playlist-section {
    border: 1px solid darkblue;
    width: fit-content;
    margin: 0 auto;
}

.favorite-playlist {
    border: 1px solid saddlebrown;
    height: 365.33px;
    flex: 1;
    /* 자식 요소가 부모의 가로 폭을 똑같이 나눠서 꽉 차게 배치 */
}

.playlist-list {
    width: 1262.5px;
    border: 1px solid red;
    margin: 0 auto;
    padding-top: 40px;
    padding-bottom: 40px;
}

.playlist-list-header {
    border: 1px solid yellow;
    display: flex;
    justify-content: space-between;
    padding: 10px 0;
    /* height: 28px; */
}

/* span.playlist-list-header-menu{
    color: aquamarine;
} */

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
    height: 200px;
}

#thumbnail {
    margin-right: 8px;
}

.content>#meta>span {
    font-size: 16px;
    line-height: 22px;
    /* font-weight: 700; */
    height: 44px;
}

.favorite-playlist img {
    width: 100%;
    height: 100%;
    object-fit: contain;
    display: block;
    /* inline 공간 제거 */
}
</style>