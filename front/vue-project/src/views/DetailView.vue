<template>
    <div>
        <div class="playlist-name">
            <span>로고 누르면 메인 가도록 할 것</span>
        </div>
        <div class="content">
            <div class="video-section">
                <DetailVideo />
                <MemoForm />
                <MemoList />
            </div>
            <div class="playlist-detail-sidebar">
                <div class="sidebar-timer">
                    <span>🏃‍♀️Timecode</span>
                    <!-- <div class="timer"> -->
                        <DetailPrograssBar />
                    <!-- </div> -->
                </div>
                <div class="sidebar-next-videos">
                    <DetailSideNextVideos />
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

import { ref, onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import { usePlaylistStores } from '@/stores/playlist';
import DetailVideo from '@/components/DetailVideo.vue';
import DetailSideNextVideos from '@/components/DetailSideNextVideos.vue';
import MemoForm from '@/components/MemoForm.vue';
import MemoList from '@/components/MemoList.vue';
import DetailPrograssBar from '@/components/DetailPrograssBar.vue';

const store = usePlaylistStores()
const route = useRoute()
store.currentPlaylistId = route.params.index

const playlistTitle = computed(() => {
    return store.playlistArr[route.params.index][0].playlistTitle
})

</script>

<style scoped>
.playlist-name {
    width: 1262.5px;
    height: 215px;
    /* border: 1px solid navajowhite; */
    margin: 0 auto;
    font-size: 45px;
}

.content {
    display: flex;
    justify-content: center;
}



.playlist-detail-sidebar {
    width: 300px;
    /* border: 1px solid yellow; */
}



.buttons {
    /* border: 1px solid firebrick; */
    display: flex;
    justify-content: flex-end;
    margin-right: 16px;
}

.sidebar-timer {
    height: 76px;
    /* border: 1px solid darkblue; */
    padding-top: 8px;
    padding-left: 10px;
}

.sidebar-timer>.timer {
    border: 1px solid rgb(159, 127, 97);
    height: 30px;
    margin-top: 3px;
    margin-right: 10px;
    font-size: 16px;
}

.sidebar-next-videos {
    margin-top: 8px;
    margin-left: 10px;
    justify-content: space-between;
    overflow-y: auto;
    height: 800px;
}

.sidebar-next-videos>svg {
    height: 30px;
    margin-right: 10px;
}

.sidebar-next-videos>span {
    border: 1px solid darkgoldenrod;
    font-size: 16px;
    height: 30px;
}
</style>