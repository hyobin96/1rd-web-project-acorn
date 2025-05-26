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
    height: 10px;
    background-color: #eee;
    margin-right: 5px;
}

.progress-bar {
    height: 100%;
    background-color: #42b983;
    transition: width 0.2s linear;
}
</style>