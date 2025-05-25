<template>
    <div>
        <div class="playlist-name">
            <span>{{ playlistTitle }}</span>
        </div>
        <div class="content">
            <div class="video-section">
                <DetailVideo />
                <MemoForm />
                <MemoList />
            </div>
            <div class="playlist-detail-sidebar">
                <div class="sidebar-timer">
                    <span>Timer</span>
                    <div class="timer"></div>
                </div>
                <div class="sidebar-calender">
                    <div class="calender">캘린더 자리</div>
                </div>
                <div class="sidebar-next-videos">
                    <DetailSideNextVideos />
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" width="18" height="18">
                        <path
                            d="M495.9 166.6c3.2 8.7 .5 18.4-6.4 24.6l-43.3 39.4c1.1 8.3 1.7 16.8 1.7 25.4s-.6 17.1-1.7 25.4l43.3 39.4c6.9 6.2 9.6 15.9 6.4 24.6c-4.4 11.9-9.7 23.3-15.8 34.3l-4.7 8.1c-6.6 11-14 21.4-22.1 31.2c-5.9 7.2-15.7 9.6-24.5 6.8l-55.7-17.7c-13.4 10.3-28.2 18.9-44 25.4l-12.5 57.1c-2 9.1-9 16.3-18.2 17.8c-13.8 2.3-28 3.5-42.5 3.5s-28.7-1.2-42.5-3.5c-9.2-1.5-16.2-8.7-18.2-17.8l-12.5-57.1c-15.8-6.5-30.6-15.1-44-25.4L83.1 425.9c-8.8 2.8-18.6 .3-24.5-6.8c-8.1-9.8-15.5-20.2-22.1-31.2l-4.7-8.1c-6.1-11-11.4-22.4-15.8-34.3c-3.2-8.7-.5-18.4 6.4-24.6l43.3-39.4C64.6 273.1 64 264.6 64 256s.6-17.1 1.7-25.4L22.4 191.2c-6.9-6.2-9.6-15.9-6.4-24.6c4.4-11.9 9.7-23.3 15.8-34.3l4.7-8.1c6.6-11 14-21.4 22.1-31.2c5.9-7.2 15.7-9.6 24.5-6.8l55.7 17.7c13.4-10.3 28.2-18.9 44-25.4l12.5-57.1c2-9.1 9-16.3 18.2-17.8C227.3 1.2 241.5 0 256 0s28.7 1.2 42.5 3.5c9.2 1.5 16.2 8.7 18.2 17.8l12.5 57.1c15.8 6.5 30.6 15.1 44 25.4l55.7-17.7c8.8-2.8 18.6-.3 24.5 6.8c8.1 9.8 15.5 20.2 22.1 31.2l4.7 8.1c6.1 11 11.4 22.4 15.8 34.3zM256 336a80 80 0 1 0 0-160 80 80 0 1 0 0 160z" />
                    </svg>
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

const store = usePlaylistStores()
const route = useRoute()
store.currentPlaylistId = route.params.index

const playlistTitle = computed(() => {
    return store.playlistArr[route.params.index][0].playlistTitle
})

</script>

<style scoped>
.playlist-name {
    width: 1200px;
    height: 215px;
    border: 1px solid navajowhite;
    margin: 0 auto;
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
    border: 1px solid darkblue;
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

.sidebar-calender {
    height: 272px;
    border: 1px solid darkcyan;
}

.sidebar-next-videos {
    margin-top: 8px;
    margin-left: 10px;
    justify-content: space-between;
    overflow-y: auto;
    height: 400px;
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