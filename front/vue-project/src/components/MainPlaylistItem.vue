<template>
    <div class="playlist" v-for="idx in 3" :key="idx">
        <!-- 실제 플레이리스트가 있는 경우 -->
        <RouterLink v-if="store.playlistArr[idx - 1]?.[0]?.thumbnails"
            :to="{ name: 'DetailPlayList', params: { index: idx - 1 } }" class="playlist-link">
            <p class="favorite-playlist-name">
                {{ store.playlistArr[idx - 1][0].playlistTitle }}
            </p>
            <img :src="store.playlistArr[idx - 1][0].thumbnails" alt="썸네일" />
        </RouterLink>

        <!-- 플레이리스트가 없을 때 안내용 3개 박스 -->
        <div v-else class="playlist-placeholder">
            {{ idx }}번째 플레이리스트를 등록해주세요!
        </div>
    </div>
</template>

<script setup>
import { usePlaylistStores } from '@/stores/playlist';
const store = usePlaylistStores()

</script>

<style scoped>
img {
    height: 300px;
}

.playlist {
    margin: 10px;
    display: inline-block;
    /* border: 1px solid firebrick; */
}

/* 스타일 리셋 */
.playlist-link {
    text-decoration: none;
    color: inherit;
    display: block;
    /* border: 1px solid darkblue; */
}

.favorite-playlist-name {
    padding-top: 3px;
    padding-left: 3px;
}

/* 플레이리스트가 없을 때의 안내 박스 스타일 */
.playlist-placeholder {
    border: 1px dashed gray;
    padding: 20px;
    margin: 5px 0;
    text-align: center;
    color: #666;
    background-color: #f9f9f9;
    width: 397px;
    /* height: 298px; */
}
</style>