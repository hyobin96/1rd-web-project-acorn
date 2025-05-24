import Sidebar from '@/components/Sidebar.vue'
import CreatePlaylistView from '@/views/CreatePlaylistView.vue'
import DetailView from '@/views/DetailView.vue'
import MainView from '@/views/MainView.vue'
import MyPageView from '@/views/MyPageView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/login',
      name: 'auth',
      component: () => import('../views/AuthView.vue'),
      children:[
        {
          path: 'login',
          name: 'login',
          component: () => import('../components/AuthLoginForm.vue'),
        },
        {
          path: 'regist',
          name: 'regist',
          component: () => import('../components/AuthResigtForm.vue'),
        }
      ]
    },
    {
      path: '/create-playlist',
      name: 'CreatePlayList',
      component: CreatePlaylistView,
    },
    {
      path: '/main',
      name: 'main',
      component: MainView,
      children: [
        {
          path: 'side',
          name: 'sidebar',
          component: Sidebar
        },
      ]
    },
    {
      path: '/detail-playlist/:playlistId',
      name: 'DetailPlayList',
      component: DetailView
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MyPageView
    }
  ],
})

export default router
