<template>
    <div>
        <RouterView />
        <nav class="navbar">
            <div class="logo-container"><a class="logo-icon"><img class="logo-img" src="https://i.imgur.com/9YO79MC.png"
                        alt="LOGO" /></a></div>
        </nav>
        <div class="motivation-banner">
            <span>응원 멘트 공간</span>
        </div>
        <main>
            <div class="favorite-playlist-section">
                <div class="favorite-playlist"><span class="favorite-playlist-name">사용자 설정 영상 제목1</span></div>
                <div class="favorite-playlist"><span class="favorite-playlist-name">사용자 설정 영상 제목1</span></div>
                <div class="favorite-playlist"><span class="favorite-playlist-name">사용자 설정 영상 제목1</span></div>
            </div>
            <div class="playlist-list">
                <div class="playlist-list-header">
                    <div class="playlist-count">
                        <span class="playlist-list-header-menu">플레이리스트 7개</span>
                    </div>
                    <div class="drop-down">
                        <button class="category-filter-bar" type="button">
                            <span class="playlist-list-header-menu">업로드 날짜</span>
                        </button>
                    </div>
                </div>
                <div class="content">
                    <div id="thumbnail"><a href="/watch?v=rlh76p4T6qw&list=WL&index=2"
                            title="𝗽𝗹𝗮𝘆𝗹𝗶𝘀𝘁 이런날 딱이잖아, 고개 끄덕여지는 적당한 리듬의 플레이리스트🎧|R&B Pop| 2시간"><img
                                src="https://i.ytimg.com/vi/rlh76p4T6qw/hqdefault.jpg?sqp=-oaymwEmCKgBEF5IWvKriqkDGQgBFQAAiEIYAdgBAeIBCggYEAIYBjgBQAE=&rs=AOn4CLAvireZUSZi88uqAprb2NOLfQ3xSQ"
                                class="playlist-thumbnail"></a></div>
                    <div id="meta"><span class="playlist-title">사용자 설정 플리 제목</span></div>
                </div>
            </div>
        </main>
    </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { usePlaylistStores } from '@/stores/playlist';

const playlistStore = usePlaylistStores()

onMounted(() => {
    playlistStore.getPlaylist()
    console.log(playlistStore.playlistArr)
    
})

</script>

<style scoped>
.navbar {
    border: 2px solid yellowgreen;
    width: 1050px;
    height: 215px;
    margin: 0 auto;
}

.logo-img {
    width: 260px;
    height: 70px;
    display: block;
}

.motivation-banner {
    width: 1050px;
    height: 56px;
    /* border: 1px solid blueviolet; */
    margin: 0 auto;
    /* 가운데 정렬 */
}

.favorite-playlist-section {
    width: 1050px;
    height: 365.33px;
    border: 1px solid darkblue;
    margin: 0 auto;
    display: flex;
}

.favorite-playlist {
    border: 1px solid saddlebrown;
    height: 365.33px;
    flex: 1;
    /* 자식 요소가 부모의 가로 폭을 똑같이 나눠서 꽉 차게 배치 */
}

.playlist-list {
    width: 1050px;
    border: 1px solid red;
    margin: 0 auto;
    /* padding-top: 40px; */
    /* padding-bottom: 40px; */
}

.playlist-list-header {
    border: 1px solid yellow;
    display: flex;
    justify-content: space-between;
    padding: 10px 0;
    /* height: 28px; */
}

/* span.playlist-list-header-menu{
    color: aquamarine;
} */

span.playlist-title {
    font-size: 20px;
}

.playlist-thumbnail {
    width: 240px;
}

.content {
    padding-top: 8px;
    padding-bottom: 8px;
    display: flex;
}

#thumbnail {
    margin-right: 8px;
}

.content>#meta>span {
    font-size: 16px;
    line-height: 22px;
    /* font-weight: 700; */
    height: 44px;
}
</style>