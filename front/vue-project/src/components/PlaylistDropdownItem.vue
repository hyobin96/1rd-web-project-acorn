<template>
    <RouterLink :to="{ name: 'DetailPlayList', params: { index } }">
        <div class="playlist-item">
            <div class="thumbnail-img"><img :src="thumbnails" alt=""></div>
            <div class="playlist-info">
                <div class="title"><span>{{ title }}</span></div>
                <div class="created-time"><span>{{ createdAt }}</span></div>
            </div>
        </div>
    </RouterLink>
</template>

<script setup>
import { computed } from 'vue';
import { usePlaylistStores } from '@/stores/playlist';
const props = defineProps(['index'])
const store = usePlaylistStores()

// 썸네일 링크
const thumbnails = computed(() => {
    return store.playlistArr[props.index][0].thumbnails
})
// 플레이리스트 제목
const title = computed(() => {
    return store.playlistArr[props.index][0].playlistTitle
})
// 업로드 날짜
const createdAt = computed(() => {
    console.log(store.playlistArr[props.index][0])
    return store.playlistArr[props.index][0].createdAt
})
</script>

<style scoped>
a {
    text-decoration: none;
    color: inherit;
}

img {
    max-height: 200px;
    object-fit: cover;
}

.playlist-item {
    padding-left: 10px;
    display: flex;
    /* border: 1px solid darkblue; */
    margin-bottom: 10px;
}

.playlist-info {
    display: flex;
    flex-direction: column;
    /* border: 1px solid darkblue; */
    padding-left: 10px;
}

.title {
    font-size: 25px;
}

.created-time {
    font-size: 16px;
    color: #868686;
}
</style>