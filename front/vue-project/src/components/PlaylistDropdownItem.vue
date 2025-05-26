<template>
    <div>
        <RouterLink :to="{ name: 'DetailPlayList', params: { index } }">
            <img :src="thumbnails" alt=""> <span>{{ title }}</span> <span>{{ createdAt }}</span>
        </RouterLink>
    </div>
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
img {
    max-height: 200px;
    object-fit: cover;
}
</style>