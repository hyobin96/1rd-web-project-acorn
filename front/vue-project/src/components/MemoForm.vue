<template>
    <div class="memo">
        <div class="creation-box">
            <input type="text" @keyup.enter="registMemo" v-model="memo">
            <div class="buttons">
                <div class="cancle-button"><button @click="cancleMemo">취소</button></div>
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
.memo-count {
    padding-top: 12px;
    /* border: 1px solid salmon; */
}
.creation-box>input {
    width: 884px;
    border-bottom: 2px solid rgb(254, 0, 0);
    /*밑줄 색 미정 */
    margin-top: 8px;
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
    color: inherit;
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
    background-color: rgb(182, 179, 179);
    /*버튼 색 미정 */
    /* font-size: 10px; */
    font-family: Roboto, Arial, sans-serif;
    border-radius: 13px;
    width: 40px;
    height: 28px;
    line-height: 28px;
    text-align: center;
}
</style>