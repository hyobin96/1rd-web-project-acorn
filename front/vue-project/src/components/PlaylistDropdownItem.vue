<template>
    <div>
        <RouterLink :to="{name: 'DetailPlayList'}">
            <img :src="playlist[0].thumbnails" alt=""> <span>{{ playlist[0].playlistTitle }}</span>
        </RouterLink>
    </div>
</template>

<script setup>
defineProps(['playlist'])
</script>

<style scoped>
img {
  max-height: 200px;
  object-fit: cover;
}
</style>