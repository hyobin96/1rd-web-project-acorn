<template>
    <div>
        <RouterLink :to="{ name: 'DetailPlayList', params: { index } }">
            <img :src="thumbnails" alt=""> <span>{{ title }}</span>
        </RouterLink>
    </div>
</template>

<script setup>
import { computed } from 'vue';
import { usePlaylistStores } from '@/stores/playlist';
const props = defineProps(['index'])
const store = usePlaylistStores()

const thumbnails = computed(() => {
   return store.playlistArr[props.index][0].thumbnails
})
const title = computed(() => {
    return store.playlistArr[props.index][0].playlistTitle
})
</script>

<style scoped>
img {
    max-height: 200px;
    object-fit: cover;
}
</style>