<template>
    <Transition name="sidebar-slide">
        <nav class="sidebar-container" v-show="visible">
            <div class="menubar" @click="emit('closeSidebar')"><svg xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 448 512" width="18" height="18">
                    <path
                        d="M0 96C0 78.3 14.3 64 32 64l384 0c17.7 0 32 14.3 32 32s-14.3 32-32 32L32 128C14.3 128 0 113.7 0 96zM0 256c0-17.7 14.3-32 32-32l384 0c17.7 0 32 14.3 32 32s-14.3 32-32 32L32 288c-17.7 0-32-14.3-32-32zM448 416c0 17.7-14.3 32-32 32L32 448c-17.7 0-32-14.3-32-32s14.3-32 32-32l384 0c17.7 0 32 14.3 32 32z" />
                </svg>
            </div>
            <div id="sidebar">
                <div class="account">
                    <img class="profile-photo" :src="store.profileImage" />
                    <span>{{ store.nickname }}</span>
                </div>
                <div class="nav-content">

                    <!-- 도토리 보상 -->
                    <div class="acorn-reward">
                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1"
                            width="18" height="18" viewBox="0 0 256 256" xml:space="preserve">
                            <g style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: none; fill-rule: nonzero; opacity: 1;"
                                transform="translate(1.4065934065934016 1.4065934065934016) scale(2.81 2.81)">
                                <path
                                    d="M 88.903 13.267 c -5.406 -4.439 -15.075 -3.289 -21.139 0.569 l -1.856 -1.857 c -12.381 -12.38 -32.527 -12.38 -44.909 0 c -2.131 2.132 -2.877 5.125 -2.256 7.868 C -5.127 44.336 -1.945 60.401 5.193 80.294 c 0.303 0.844 0.967 1.509 1.811 1.812 c 8.173 2.932 15.696 5.2 23.425 5.2 c 11.131 0 22.688 -4.713 37.204 -18.92 c 0.476 0.081 0.956 0.129 1.437 0.129 c 2.169 0 4.337 -0.825 5.987 -2.476 c 5.998 -5.997 9.301 -13.972 9.301 -22.454 s -3.303 -16.456 -9.301 -22.454 l -2.916 -2.916 c 4.274 -2.188 10.262 -2.517 12.952 -0.309 c 1.28 1.052 3.171 0.866 4.223 -0.416 C 90.37 16.21 90.184 14.318 88.903 13.267 z M 10.367 76.931 C 4.039 58.983 2.04 46.006 22.071 25.026 l 7.631 7.63 l 32.57 32.571 C 41.292 85.257 28.314 83.259 10.367 76.931 z M 70.814 61.795 c -0.96 0.96 -2.519 0.96 -3.48 0.005 l -28.15 -28.149 L 25.243 19.709 c -0.961 -0.961 -0.961 -2.524 0 -3.486 c 5.021 -5.021 11.615 -7.53 18.21 -7.53 c 6.594 0 13.19 2.511 18.21 7.53 l 9.151 9.151 C 80.855 35.416 80.855 51.754 70.814 61.795 z"
                                    style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(0,0,0); fill-rule: nonzero; opacity: 1;"
                                    transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round" fill="black" stroke="black"
                                    stroke-width="5" />
                            </g>
                        </svg><span>{{ store.rewardPoints }}개</span>
                    </div>

                    <!-- 플레이리스트 만들기 -->
                    <div class="create-playlist"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"
                            width="18" height="18">
                            <path
                                d="M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32l0 144L48 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l144 0 0 144c0 17.7 14.3 32 32 32s32-14.3 32-32l0-144 144 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-144 0 0-144z" />
                        </svg><span @click="showModal = true" style="cursor: pointer;">플레이리스트 만들기</span>
                        <ModalView v-if="showModal" @close="showModal = false">
                            <CreatePlaylistView />
                        </ModalView>
                    </div>

                    <!-- 이벤트 -->
                    <div class="event-page"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" width="18"
                            height="18">
                            <path
                                d="M190.5 68.8L225.3 128l-1.3 0-72 0c-22.1 0-40-17.9-40-40s17.9-40 40-40l2.2 0c14.9 0 28.8 7.9 36.3 20.8zM64 88c0 14.4 3.5 28 9.6 40L32 128c-17.7 0-32 14.3-32 32l0 64c0 17.7 14.3 32 32 32l448 0c17.7 0 32-14.3 32-32l0-64c0-17.7-14.3-32-32-32l-41.6 0c6.1-12 9.6-25.6 9.6-40c0-48.6-39.4-88-88-88l-2.2 0c-31.9 0-61.5 16.9-77.7 44.4L256 85.5l-24.1-41C215.7 16.9 186.1 0 154.2 0L152 0C103.4 0 64 39.4 64 88zm336 0c0 22.1-17.9 40-40 40l-72 0-1.3 0 34.8-59.2C329.1 55.9 342.9 48 357.8 48l2.2 0c22.1 0 40 17.9 40 40zM32 288l0 176c0 26.5 21.5 48 48 48l144 0 0-224L32 288zM288 512l144 0c26.5 0 48-21.5 48-48l0-176-192 0 0 224z" />
                        </svg>
                        <span @click="router.push('/events')" style="cursor: pointer;">이벤트</span>
                    </div>

                </div>
                <div class="user-menu">
                    <div class="mypage">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" width="18" height="18">
                            <path
                                d="M495.9 166.6c3.2 8.7 .5 18.4-6.4 24.6l-43.3 39.4c1.1 8.3 1.7 16.8 1.7 25.4s-.6 17.1-1.7 25.4l43.3 39.4c6.9 6.2 9.6 15.9 6.4 24.6c-4.4 11.9-9.7 23.3-15.8 34.3l-4.7 8.1c-6.6 11-14 21.4-22.1 31.2c-5.9 7.2-15.7 9.6-24.5 6.8l-55.7-17.7c-13.4 10.3-28.2 18.9-44 25.4l-12.5 57.1c-2 9.1-9 16.3-18.2 17.8c-13.8 2.3-28 3.5-42.5 3.5s-28.7-1.2-42.5-3.5c-9.2-1.5-16.2-8.7-18.2-17.8l-12.5-57.1c-15.8-6.5-30.6-15.1-44-25.4L83.1 425.9c-8.8 2.8-18.6 .3-24.5-6.8c-8.1-9.8-15.5-20.2-22.1-31.2l-4.7-8.1c-6.1-11-11.4-22.4-15.8-34.3c-3.2-8.7-.5-18.4 6.4-24.6l43.3-39.4C64.6 273.1 64 264.6 64 256s.6-17.1 1.7-25.4L22.4 191.2c-6.9-6.2-9.6-15.9-6.4-24.6c4.4-11.9 9.7-23.3 15.8-34.3l4.7-8.1c6.6-11 14-21.4 22.1-31.2c5.9-7.2 15.7-9.6 24.5-6.8l55.7 17.7c13.4-10.3 28.2-18.9 44-25.4l12.5-57.1c2-9.1 9-16.3 18.2-17.8C227.3 1.2 241.5 0 256 0s28.7 1.2 42.5 3.5c9.2 1.5 16.2 8.7 18.2 17.8l12.5 57.1c15.8 6.5 30.6 15.1 44 25.4l55.7-17.7c8.8-2.8 18.6-.3 24.5 6.8c8.1 9.8 15.5 20.2 22.1 31.2l4.7 8.1c6.1 11 11.4 22.4 15.8 34.3zM256 336a80 80 0 1 0 0-160 80 80 0 1 0 0 160z" />
                        </svg>
                        <span @click="router.push('/mypage')" style="cursor: pointer;">마이페이지</span>
                    </div>
                    <div class="logout"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" width="18"
                            height="18">
                            <path
                                d="M502.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-128-128c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L402.7 224 192 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l210.7 0-73.4 73.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l128-128zM160 96c17.7 0 32-14.3 32-32s-14.3-32-32-32L96 32C43 32 0 75 0 128L0 384c0 53 43 96 96 96l64 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32l0-256c0-17.7 14.3-32 32-32l64 0z" />
                        </svg><span @click="store.logout" style="cursor: pointer;">로그아웃</span></div>
                </div>
            </div>
        </nav>
    </Transition>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useUserStores } from '@/stores/user';
import { ref, onMounted } from 'vue';
import ModalView from '@/views/ModalView.vue';
import CreatePlaylistView from '@/views/CreatePlaylistView.vue';
import api from '@/api/axios';

const store = useUserStores()
const router = useRouter()
const showModal = ref(false)
const userStore = useUserStores()

const props = defineProps({
    visible: Boolean
})

const emit = defineEmits(['closeSidebar'])

// 사이드바 진입시 포인트 정보 불러오기
onMounted(async () => {
    try {
        const res = await api.get(`users/attendance?username=${userStore.username}`
        )
        userStore.setRewardPoints(res.data.rewardPoints)
    } catch (err) {
        userStore.setRewardPoints(err.response.data.rewardPoints)
    }
})

</script>

<style scoped>
html,
body {
    height: 100%;
    margin: 0;
    padding: 0;
}

.sidebar-container {
    width: 280px;
    height: 100vh;
    border: 1px solid #D9D9D9;
    position: fixed;
    /* 절대 위치가 아니라 화면 고정 */
    left: 0;
    top: 0;
    z-index: 1000;
    background-color: white;
    /* 투명하게 보일 가능성 방지 */
    padding-left: 12px;
    padding-right: 12px;
    /* transform: translateX(-100%); */
    /* transition: transform 0.35s cubic-bezier(0.4, 0, 0.2, 1); */
}

/* 
.sidebar-container.active {
    transform: translateX(0);
} */

.menubar {
    /* border: 1px solid palevioletred; */
    position: absolute;
    top: 10px;
    right: 10px;
    cursor: pointer;
}

#sidebar {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
}

/* .sidebar-container > #sidebar{
    border: 1px solid violet;
} */

.profile-photo {
    width: 100px;
    height: 100px;
    margin-bottom: 12px;
    border-radius: 50%;
    /* 프사 원형 */
}

.account {
    display: flex;
    flex-direction: column;
    /* margin: 0 auto; */
    padding-top: 40px;
    /* border: 1px solid pink; */
    align-items: center;
    /*가운데 정렬*/
    padding-bottom: 15px;
    margin-bottom: 20px;
}

.nav-content>.create-playlist {
    display: flex;
    align-items: center;
}

.nav-content>div>svg {
    margin-left: 15px;
    /* border: 1px solid brown; */
}

.nav-content>div>span {
    font-size: 18px;
    /* font-weight: 700; */
    height: 24px;
    margin-left: 12px;
}

.user-menu {
    margin-top: auto;
    margin-bottom: 30px;
}

.user-menu>div>svg {
    margin-left: 15px;
}

.user-menu>div>span {
    font-size: 18px;
    /* font-weight: 700; */
    height: 24px;
    margin-left: 12px;
    /* border: 1px solid yellow; */
}

.create-playlist,
.event-page {
    /* border: 1px solid wheat; */
    margin-top: 8px;
}

.mypage,
.logout {
    /* border: 1px solid red; */
    margin-top: 8px;
}

.sidebar-slide-enter-from,
.sidebar-slide-leave-to {
    transform: translateX(-100%);
    opacity: 0;
}

.sidebar-slide-enter-to,
.sidebar-slide-leave-from {
    transform: translateX(0);
    opacity: 1;
}

.sidebar-slide-enter-active {
    transition: transform 1s cubic-bezier(0.22, 0.61, 0.36, 1), opacity 0.3s;
}

.sidebar-slide-leave-active {
    transition: transform 1s cubic-bezier(0.4, 0, 0.2, 1), opacity 0.3s;
}
</style>