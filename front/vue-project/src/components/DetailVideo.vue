<template>
    <div class="video-container">
        <iframe :src="src" frameborder="0"></iframe>
    </div>
    <div class="video-title">{{ title }}</div>
</template>

<script setup>
import { compile, computed, onMounted, ref } from 'vue';
import { usePlaylistStores } from '@/stores/playlist';
const store = usePlaylistStores()
const src = computed(() => {
    return `https://www.youtube.com/embed/${store.playlistArr[store.currentPlaylistId][store.currentPlaylistItemId].videoId}`
})
const title = computed(() => {
    return store.playlistArr[store.currentPlaylistId][store.currentPlaylistItemId].playlistItemTitle
})

</script>

<style scoped>
iframe {
    width: 98%;
    height: 98%;
    display: block;
    margin: 0 auto;
    object-fit: cover;
}

.video-container {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 900px;
    height: 506.3px;
    border: 1px solid red;
}

.video-title {
    width: 900px;
    height: 28px;
    font-size: 20px;
    /* border: 1px solid rebeccapurple; */
    margin-top: 12px;
    margin-bottom: 24px;
    font-weight: 700;
    word-break: break-all;
}
</style>