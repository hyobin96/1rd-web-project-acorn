<template>
    <div>
        <h2>메인페이지 만드는 중</h2>
        <RouterView/>
        <nav class="navbar">
            <div class="logo-container"><a class="logo-icon">로고 이미지</a></div>
            <div class="account"><span>y</span></div>
        </nav>
        <div class="motivation-banner">
            <span>응원하는 말 넣는 곳~~~</span>
        </div>
        <main>
            <div class="favorite-playlist-section">
                <div class="favorite-playlist"><span class="favorite-playlist-name">사용자 설정 영상 제목1</span></div>
                <div class="favorite-playlist"><span class="favorite-playlist-name">사용자 설정 영상 제목1</span></div>
                <div class="favorite-playlist"><span class="favorite-playlist-name">사용자 설정 영상 제목1</span></div>
            </div>
            <div class="playlist-list">
            </div>
        </main>

    </div> 
</template>

<script setup>

</script>

<style scoped>
 .navbar{
    border: 1px solid red;
 }
</style>