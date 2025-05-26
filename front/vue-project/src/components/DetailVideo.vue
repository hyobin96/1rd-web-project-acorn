<template>
    <div class="video-container">
        <iframe :src="src" ref="iframeRef" frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen></iframe>
    </div>
    <div class="video-title">{{ title }}</div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { usePlaylistStores } from '@/stores/playlist'

const store = usePlaylistStores()
const iframeRef = ref(null)
let player = null
let intervalId = null

const src = computed(() => {
    const item = store.playlistArr[store.currentPlaylistId][store.currentPlaylistItemId]
    return `https://www.youtube.com/embed/${item.videoId}?enablejsapi=1`
})

const title = computed(() => {
    const item = store.playlistArr[store.currentPlaylistId][store.currentPlaylistItemId]
    return item.playlistItemTitle
})

const setupPlayer = () => {
    player = new window.YT.Player(iframeRef.value, {
        events: {
            onReady: () => {
                intervalId = setInterval(() => {
                    const current = player.getCurrentTime()
                    const duration = player.getDuration()
                    if (duration > 0) {
                        store.progress = (current / duration) * 100
                        store.duration = duration
                    }
                }, 500)
            }
        }
    })
}

onMounted(() => {
    if (!window.YT) {
        const tag = document.createElement('script')
        tag.src = 'https://www.youtube.com/iframe_api'
        document.body.appendChild(tag)
        window.onYouTubeIframeAPIReady = setupPlayer
    } else {
        setupPlayer()
    }
})

onUnmounted(() => {
    clearInterval(intervalId)
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

.video-container {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 962.5px;
    height: 506.3px;
    border: 2px solid #42b983; /* Vue 기본 테마 색 */
}

.video-title {
    width: 900px;
    height: 28px;
    font-size: 20px;
    margin-top: 12px;
    margin-bottom: 24px;
    font-weight: 700;
    word-break: break-word;
}
</style>
