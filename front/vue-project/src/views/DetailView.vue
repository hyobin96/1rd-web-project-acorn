<template>
    <div>
        <div class="playlist-name">
            <span>사용자 설정 플리 제목</span>
        </div>
        <div class="content">
        <div class="video-section">
        <div class="video-container">
            <iframe src="https://www.youtube.com/watch?v=rlh76p4T6qw&list=WL" allow="autoplay" allowfullscreen> <!--일단 오토플레이로 설정-->
            </iframe>
        </div>
        <div class="video-title">감성을 깨우는 커피 향과 고요한 피아노 선율</div>
        <div class="memo-container">
            <span class="memo-count">메모 n개</span>
            <div class="memo">
                <div class="creation-box">
                    <input type="text" @keyup.enter="registMemo" v-model="memo">
                    <div class="buttons">
                    <div class="cancle-button"><button @click="cancleMemo">취소</button></div>
                    <div class="regist-button" :class="{active : memo.trim()}"><button @click="registMemo" :disabled="!memo.trim()">등록</button></div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <div class="playlist-detail-sidebar">
            <div class="sidebar-timer">
                타이머
            </div>
            <div class="sidebar-calender">
                캘린더
            </div>
            <div class="sidebar-next-videos">
                다음 영상
            </div>
        </div>
        </div>
    </div>
</template>

<script setup>

import {ref} from 'vue';

const memo = ref('');
const memoList = ref([])

function registMemo(){
    const newMemo = memo.value.trim()
    if(newMemo) {
        memoList.value.push(newMemo)
        memo.value =''
    }
}

function cancleMemo(){
    memo.value = '';
}

</script>

<style scoped>

input, button {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: none;
  outline: none;
  background: none;
  font: inherit;
  color: inherit;
  appearance: none; /* 브라우저 기본 스타일 제거 */
  -webkit-appearance: none;
  -moz-appearance: none;
}

.playlist-name{
    width: 1200px;
    height: 215px;
    border: 1px solid navajowhite;
    margin: 0 auto;
}

.content{
    display: flex;
    justify-content: center;
}

.video-container{
    width: 900px;
    height: 506.3px;
    border: 1px solid red;
    display: flex;
    flex-direction: column;
}

.video-title{
    height: 28px;
    font-size: 20px;
    /* border: 1px solid rebeccapurple; */
    margin-top: 12px;
    margin-bottom: 24px;
    font-weight: 700;
}

.playlist-detail-sidebar{
    width: 300px;
    border: 1px solid yellow;
}

.memo-container{
    /* border: 1px solid darkblue; */
}

.creation-box > input{
    width: 884px;
    border-bottom: 2px solid rgb(254, 0, 0); /*밑줄 색 미정 */
    margin-top: 8px;
}

.buttons{
    /* border: 1px solid firebrick; */
    display: flex;
    justify-content: flex-end;
    margin-right: 16px;
}

.cancle-button {
    margin-top: 8px;
    margin-left: 8px;
    /* font-size: 10px; */
    font-family: Roboto, Arial, sans-serif;
    border-radius: 15px;
    width: 38px;
    height: 25px;
    text-align: center;
}

.regist-button {
    margin-top: 8px;
    margin-left: 8px;
    background-color: rgb(182, 179, 179); /*버튼 색 미정 */
    /* font-size: 10px; */
    font-family: Roboto, Arial, sans-serif;
    border-radius: 15px;
    width: 38px;
    height: 25px;
    text-align: center;
}
</style>