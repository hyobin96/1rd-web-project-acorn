import { computed, ref } from 'vue';
import axios from 'axios'
import { defineStore } from 'pinia';
import { useUserStores } from './user';

const API_URL = 'http://localhost:8080/api'
const youtube_api = "https://www.googleapis.com/youtube/v3/playlistItems"

export const usePlaylistStores = defineStore('playlist-stores', () => {
    const userStore = useUserStores()
    const YOUTUBE_API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY
    const title = ref('')
    const link = ref('')
    const playlistId = computed(() => {
        const index = link.value.indexOf('=')
        return link.value.substring(index + 1)
    })

    // 플레이리스트 저장
    const savePlaylist = async () => {
        console.log(1)
        try {
            const { data, status } = await axios.get(youtube_api,
                {
                    params: {
                        part: 'snippet',
                        maxResults: 50,
                        playlistId: playlistId.value,
                        key: YOUTUBE_API_KEY,
                    },
                }
            )
        } catch {
            alert("유튜브 api 요청 실패")
        }

        if (status === 200) {
            console.log(data)
            //플레이리스트 생성
            try {
                const res = await axios.post(`${API_URL}/playlist`,
                    {
                        userId: userStore.userId,
                        title: title.value,
                    }
                )

                let id = res.data.id
                //플레이리스트 삽입
                const items = data.items
                const arr = []
                for (let i = 0; i < items.length; i++) {
                    let playlistId = id
                    let videoId = items[i].snippet.resourceId.videoId
                    let thumbnails = items[i].snippet.thumbnails.default.url
                    arr.push({ playlistId, videoId, thumbnails })
                }
                const { status } = await axios.post(`${API_URL}/playlistItem`,
                    arr,
                )
            } catch {
                alert("플레이리스트 생성 실패")
            }
        }
    }

    return { title, link, playlistId, savePlaylist, }
})