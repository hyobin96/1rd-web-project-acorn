import { defineStore } from 'pinia'
import { ref, computed, watch } from 'vue'
import axios from '@/api/axios'
import { usePlaylistStores } from './playlist'

export const useMemoStores = defineStore('memo-stores', () => {
    const playlistStore = usePlaylistStores()
    const memoArr = ref([])
    const playlistItemsId = computed(() => {
        return playlistStore.playlistArr[playlistStore.currentPlaylistId][playlistStore.currentPlaylistItemId].playlistItemId
    })

    watch(playlistItemsId, () => {
        loadMemoArr()
    })

    // memo를 로딩
    const loadMemoArr = async () => {
        memoArr.value = []
        const { data } = await axios.get(`memo/${playlistItemsId.value}`,
        )
        memoArr.value = data
        console.log(memoArr.value)
    }

    const registMemo = async (memo) => {
        try {
            console.log(playlistItemsId.value)
            // 등록요청
            await axios.post(
                'memo',
                { playlistItemsId: playlistItemsId.value, memo }
            )

            // 다시 memo 로딩
            await loadMemoArr()
        } catch (err) {
            console.log(err)
        }
    }

    // 수정
    const modifyMemo = async (memo) => {
        try {
            await axios.put(
                'memo',
                memo,
            )
            // 수정 후 다시 로딩
            await loadMemoArr()
        } catch (err) {
            console.log(err)
        }
    }

    // 삭제
    const removeMemo = async (id) => {
        try {
            await axios.delete(
                `memo/${id}`
            )
            // 다시 로딩
            await loadMemoArr()
        } catch (err) {
            console.log(err)
        }
    }
    return { memoArr, registMemo, loadMemoArr, modifyMemo, removeMemo, }
}, { persist: true })