<template>
    <div class="playlist-item">
        <RouterLink :to="{ name: 'DetailPlayList', params: { index } }">
            <div class="thumbnail-img"><img :src="thumbnails" alt=""></div>
        </RouterLink>
        <div class="playlist-info" v-if="!isDeleted">
            <div class="title"><span>{{ title }}</span></div>
            <div class="created-time"><span>{{ createdAt }}</span></div>
            <div v-if="isEditing">
                <button @click="deleteHandler">삭제</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { computed, ref } from 'vue';
import { usePlaylistStores } from '@/stores/playlist';
const props = defineProps(['index', 'isEditing', 'deleteArr'])
const emit = defineEmits(['delete',])
const store = usePlaylistStores()

const isDeleted = ref(false)

// 썸네일 링크
const thumbnails = computed(() => {
    return store.playlistArr[props.index][0].thumbnails
})
// 플레이리스트 제목
const title = computed(() => {
    return store.playlistArr[props.index][0].playlistTitle
})
// 업로드 날짜
const createdAt = computed(() => {
    console.log(store.playlistArr[props.index][0])
    return store.playlistArr[props.index][0].createdAt
})


const updateHandler = () => {

}

const deleteHandler = () => {
    const playlistId = store.playlistArr[props.index][0].playlistId
    emit('delete', playlistId, props.index)
    // store.playlistArr.splice(props.index, 1)
    isDeleted.value = true
}
</script>

<style scoped>
a {
    text-decoration: none;
    color: inherit;
}

img {
    max-height: 200px;
    object-fit: cover;
}

.playlist-item {
    padding-left: 10px;
    display: flex;
    /* border: 1px solid darkblue; */
    margin-bottom: 10px;
}

.playlist-info {
    display: flex;
    flex-direction: column;
    /* border: 1px solid darkblue; */
    padding-left: 10px;
}

.title {
    font-size: 25px;
}

.created-time {
    font-size: 16px;
    color: #868686;
}
</style>