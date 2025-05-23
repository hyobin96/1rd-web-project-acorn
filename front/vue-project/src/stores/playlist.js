import { computed, ref } from 'vue';
import axios from '@/api/axios'
import { defineStore } from 'pinia';
import { useUserStores } from './user';

const youtube_api = "https://www.googleapis.com/youtube/v3/playlistItems"

export const usePlaylistStores = defineStore('playlist-stores', () => {
    const userStore = useUserStores()
    const YOUTUBE_API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY
    const title = ref('')
    const link = ref('')

    const playlistArr = ref([])
    
    const playlistId = computed(() => {
        const index = link.value.indexOf('=')
        return link.value.substring(index + 1)
    })

    // 플레이리스트 저장
    const savePlaylist = async () => {
        try {
            //유튜브로 API호출해서 playlistItems 가져오기
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
            //플레이리스트 생성
            const res = await axios.post('playlist',
                {
                    userId: userStore.userId,
                    title: title.value,
                }
            )
            //플레이리스트 삽입
            let id = res.data.id
            const items = data.items
            const arr = []
            for (let i = 0; i < items.length; i++) {
                let playlistId = id
                let videoId = items[i].snippet.resourceId.videoId
                let thumbnails = items[i].snippet.thumbnails.default.url
                arr.push({ playlistId, videoId, thumbnails })
            }
            try {
                await axios.post('playlistItem',
                    arr,
                )
            } catch (err) {
                alert("플레이리스트 삽입 실패")
                console.log(err)
            }

        } catch (err) {
            alert("유튜브 api 요청 실패")
            console.log(err)
        }
    }

    const getPlaylist = () => {
        axios.get('playlist',).then(res => console.log(res))
    }

    return { title, link, playlistId, savePlaylist, getPlaylist, playlistArr }
})