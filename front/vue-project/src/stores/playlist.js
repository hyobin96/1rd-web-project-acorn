import { computed, reactive, ref } from 'vue';
import axios from '@/api/axios'
import axios2 from 'axios'
import { defineStore } from 'pinia';
import { useUserStores } from './user';

const youtube_api = "https://www.googleapis.com/youtube/v3/playlistItems"

export const usePlaylistStores = defineStore('playlist-stores', () => {

    const userStore = useUserStores()
    const YOUTUBE_API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY

    const currentPlaylistId = ref(0)
    const currentPlaylistItemId = ref(0)

    const playlistArr = ref([])
    const progress = ref(0) //진행률
    const duration = ref(0) //전체 시간

    // CreatePlaylistName 컴포넌트의 플레이리스트 제목
    const title = ref('')
    // CreatePlaylistURL 컴포넌트의 플레이리스트 링크
    const link = ref('')
    const playlistId = computed(() => {
        const index = link.value.indexOf('=')
        return link.value.substring(index + 1)
    })

    // 플레이리스트 저장
    const savePlaylist = async () => {
        try {
            //유튜브로 API호출해서 playlistItems 가져오기
            const { data, status } = await axios2.get(youtube_api,
                {
                    params: {
                        part: 'snippet',
                        maxResults: 50,
                        playlistId: playlistId.value,
                        key: YOUTUBE_API_KEY,
                    },
                },
            )
            console.log("youtube", data)
            console.log("user", userStore.userId)
            //플레이리스트 생성
            const res = await axios.post('playlist',
                {
                    userId: userStore.userId,
                    title: title.value
                }
            )
            //플레이리스트 삽입
            let id = res.data.id
            const items = data.items
            const arr = []
            for (let i = 0; i < items.length; i++) {
                let playlistId = id
                let videoId = items[i].snippet.resourceId.videoId
                let thumbs = items[i].snippet.thumbnails;
                let thumbnails = thumbs.high?.url ||
                    thumbs.medium?.url ||
                    thumbs.default?.url || 'https://via.placeholder.com/320x180?text=No+Thumbnail'
                let title = items[i].snippet.title
                arr.push({ playlistId, videoId, thumbnails, title })
            }
            try {
                await axios.post('playlistItem',
                    arr,
                )
            } catch (err) {
                alert("플레이리스트 삽입 실패")
                console.log(err)
            }
            alert("플레이리스트 등록 성공")

        } catch (err) {
            alert("유튜브 api 요청 실패")
            console.log(err)
        }

    }

    //플레이리스트 로딩
    const getPlaylist = async () => {
        playlistArr.value = []
        try {
            const res = await axios.get('playlist',)
            for (const key in res.data) {
                playlistArr.value.push(res.data[key])
            }
            console.log(res)
        } catch (err) {
            console.log(err)
        }
    }

    const moveNextPlay = () => {
        if (currentPlaylistItemId.value + 1 === playlistArr.value[currentPlaylistId.value].length) {
            currentPlaylistItemId.value = 0
            currentPlaylistId.value++
        }
        else {
            currentPlaylistItemId.value++
        }

        if (currentPlaylistId.value === playlistArr.value.length) {
            currentPlaylistId.value = 0
        }
    }

    return {
        title, link, playlistId, savePlaylist, getPlaylist, playlistArr,
        currentPlaylistItemId, currentPlaylistId, moveNextPlay, progress, duration,
    }
}, { persist: true })