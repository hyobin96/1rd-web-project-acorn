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
    height: auto;
    display: block;
}
</style>