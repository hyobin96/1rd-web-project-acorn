<template>
    <div v-if="!isEditing">
        <p>{{ memo.memo }} <button @click="isEditing = !isEditing">수정</button> <button @click="store.removeMemo(memo.id)">삭제</button></p>
    </div>
    <div v-else>
        <textarea v-model="editText"></textarea> <button @click="modifyMemoHandler">저장</button><button @click="isEditing = !isEditing">취소</button>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useMemoStores } from '@/stores/memo';

const store = useMemoStores()
const props = defineProps(['memo',])
const isEditing = ref(false)
const editText = ref(props.memo.memo)

const modifyMemoHandler = () => {
    props.memo.memo = editText.value
    store.modifyMemo(props.memo)
}

</script>

<style scoped>

</style>