<template>
    <div class="playlist" v-if="playlist[0]['thumbnails'] !== null">
        <RouterLink :to="{name: 'DetailPlayList'}">
            <p class="favorite-playlist-name">{{ playlist[0]['playlistTitle'] }}</p>
            <img :src="playlist[0]['thumbnails']" alt="썸네일" />
        </RouterLink>
    </div>
</template>

<script setup>
defineProps(['playlist',])

</script>

<style scoped>
img {
    height: 300px;
}

.playlist{
    margin: 10px;
    display: inline-block;
}

</style>