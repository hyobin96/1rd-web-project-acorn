<template>
    <div class="playlist" v-if="store.playlistArr[0]['thumbnails'] !== null">
        <RouterLink :to="{ name: 'DetailPlayList', params: { index } }">
            <p class="favorite-playlist-name">{{ title }}</p>
            <img :src="thumbnails" alt="썸네일" />
        </RouterLink>
    </div>
</template>

<script setup>
import { usePlaylistStores } from '@/stores/playlist';
import { computed } from 'vue';
const store = usePlaylistStores()
const props = defineProps(['index',])
const title = computed(() => {
    return store.playlistArr[props.index][0].playlistTitle
})
const thumbnails = computed(() => {
    return store.playlistArr[props.index][0].thumbnails
})
</script>

<style scoped>
img {
    height: 300px;
}

.playlist {
    margin: 10px;
    display: inline-block;
}
</style>