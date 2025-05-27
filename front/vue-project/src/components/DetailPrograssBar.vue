<template>
    <div class="progress-container">
        <div class="progress-bar" :style="{ width: store.progress + '%' }"></div>
    </div>
</template>

<script setup>
import { usePlaylistStores } from '@/stores/playlist'

const store = usePlaylistStores()
// const progress = store.progress
// console.log(progress.log)
</script>

<style scoped>
.progress-container {
    margin-top: 5px;
    width: 94%;
    height: 25px;
    background-color: #eee;
    margin-right: 10px;
}

.progress-bar {
    height: 100%;
    background-color: #f9a63a;
    transition: width 0.2s linear;
}
</style>