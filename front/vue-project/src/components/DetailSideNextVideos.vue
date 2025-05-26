<template>
    <div>
        <img :src="item.thumbnails" alt="" v-for="(item, index) in store.playlistArr[store.currentPlaylistId]"
            @click="store.currentPlaylistItemId = index">
    </div>
</template>

<script setup>
import { usePlaylistStores } from '@/stores/playlist';
const store = usePlaylistStores()
</script>

<style scoped>
img {
    width: 100%;
    height: 150px;
    /* 원하는 높이 */
    object-fit: cover;
    /* ✅ 확대 잘라서 꽉 채우기 */
    display: block;
    margin-bottom: 10px;
    border: 2px solid #42b983; /* Vue 기본 테마 색 */
}
</style>