<template>
    <div class="memo">
        <div class="creation-box">
            <input type="text" @keyup.enter="registMemo" v-model="memo" placeholder="메모를 입력해보세요">
            <div class="buttons">
                <!-- <div class="cancle-button"><button @click="cancleMemo">취소</button></div> -->
                <div class="regist-button" :class="{ active: memo.trim() }"><button @click="registMemo"
                        :disabled="!memo.trim()">등록</button></div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useMemoStores } from '@/stores/memo';

const store = useMemoStores()
const memo = ref('');

onMounted(() => {
    store.loadMemoArr()
})

function registMemo() {
    const newMemo = memo.value.trim()
    if (newMemo) {
        store.registMemo(newMemo)
        memo.value = ''
    }
}

function cancleMemo() {
    memo.value = '';
}

</script>

<style scoped>
.memo{
    /* border: 1px solid darkblue; */
    margin-left: 10px;
    margin-right: 10px;
}

.memo-count {
    padding-top: 12px;
    /* border: 1px solid salmon; */
}

.creation-box {
    width: 100%;
    height: 100px;
    border: 2px solid #D9D9D9;
    /*밑줄 색 미정 */
    /* border-radius: 10px; */
    margin-top: 8px;
    position: relative;
    padding-top: 10px;
    padding-left: 10px;
}

.buttons {
    display: flex;
    justify-content: end;
}

input,
button {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    border: none;
    outline: none;
    background: none;
    font: inherit;
    color: #868686;
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
}

.cancle-button {
    margin-top: 8px;
    margin-left: 8px;
    /* font-size: 10px; */
    font-family: Roboto, Arial, sans-serif;
    border-radius: 15px;
    width: 40px;
    height: 28px;
    line-height: 28px;
    text-align: center;
}

.regist-button {
    margin-top: 8px;
    margin-left: 8px;
    border: 1px solid #D9D9D9;
    /* background-color: rgb(182, 179, 179); */
    /*버튼 색 미정 */
    /* font-size: 10px; */
    font-family: Roboto, Arial, sans-serif;
    border-radius: 13px;
    width: 50px;
    height: 30px;
    line-height: 28px;
    text-align: center;
    position: absolute;
    top: 50px;
    right: 10px;
    background-color: #F2F2F2;
}

.regist-button.active {
    background-color: #0D0D0D;
    color: #fff;
}
</style>