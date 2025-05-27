<template>
    <div>
        <img :src="item.thumbnails" @click="store.currentPlaylistItemId = index">
        <div v-if="isEditing">
            <button @click="updateHandler">수정</button>
            <button @click="deleteHandler">삭제</button>
        </div>
    </div>
</template>

<script setup>
import { usePlaylistStores } from '@/stores/playlist';
const store = usePlaylistStores()
const props = defineProps(['item', 'index', 'isEditing', ])

const deleteHandler = () => {
    store.deletePlaylistItem(props.index)
}
const updateHandler = () => {
    
}
</script>

<style scoped>
img {
    width: 100%;
    height: 150px;
    /* 원하는 높이 */
    object-fit: cover;
    /* ✅ 확대 잘라서 꽉 채우기 */
    display: block;
    margin-bottom: 10px;
    /* border: 2px solid #42b983;  */
}
</style>