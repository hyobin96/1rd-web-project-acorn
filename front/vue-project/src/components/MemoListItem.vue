<template>
    <div v-if="!isEditing" class="edit-btn">
        <p>{{ memo.memo }}</p>
        <div class="toggle-btn" @click="ToggleEvent"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 128 512" width="16" height="16" >
                <path
                    d="M64 360a56 56 0 1 0 0 112 56 56 0 1 0 0-112zm0-160a56 56 0 1 0 0 112 56 56 0 1 0 0-112zM120 96A56 56 0 1 0 8 96a56 56 0 1 0 112 0z" fill="#D9D9D9"/>
            </svg></div>
        <div class="buttons" v-if="toggleVisible">
            <div class="edit"><button @click="isEditing = !isEditing">수정</button></div>
            <div class="delete"><button @click="store.removeMemo(memo.id)">삭제</button></div>
        </div>
    </div>
    <div v-else class="save-btn">
        <textarea v-model="editText"></textarea> <button @click="modifyMemoHandler">저장</button><button
            @click="isEditing = !isEditing">취소</button>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useMemoStores } from '@/stores/memo';

const store = useMemoStores()
const props = defineProps(['memo',])
const isEditing = ref(false)
const toggleVisible = ref(false)
const editText = ref(props.memo.memo)

const modifyMemoHandler = () => {
    props.memo.memo = editText.value
    store.modifyMemo(props.memo)
}

function ToggleEvent(){
    toggleVisible.value = !toggleVisible.value;
}

</script>

<style scoped>
.buttons {
    /* border: 1px solid firebrick; */
    position: absolute;
    left: 890px;
    top: 7px;
}

button{
    /* border: none; */
    border: 1px solid #D9D9D9;
    background-color: #F2F2F2;
    color: #6c757d;
}

.edit-btn {
    border-bottom: 1px solid #D9D9D9;
    /* border: 1px solid firebrick; */
    display: flex;
    justify-content: space-between;
    position: relative;
    margin-left: 10px;
    margin-right: 10px;
}

.edit-btn>p {
    padding-top: 10px;
    padding-left: 10px;
}

.toggle-btn {
    position: absolute;
    left: 930px;
    top: 5px;
}
</style>