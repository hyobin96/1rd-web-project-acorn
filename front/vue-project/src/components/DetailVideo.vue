<template>
    <div class="video-container">
        <!-- <iframe :src="`https://www.youtube.com/embed/${playlist[0].videoId}`"  frameborder="0"></iframe> -->
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { usePlaylistStores } from '@/stores/playlist';
const playlistStore = usePlaylistStores()
let url = ref('')
onMounted(() => {
    url.value = playlistStore.playlistArr[playlistStore.currentPlaylistId.valueOf]
    console.log(url.value)
    console.log("playlistArr:", playlistStore.playlistArr)
    console.log("currentPlaylistId:", playlistStore.currentPlaylistId)

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
</style>