import Sidebar from '@/components/Sidebar.vue'
import CreatePlaylistView from '@/views/CreatePlaylistView.vue'
import DetailView from '@/views/DetailView.vue'
import MainView from '@/views/MainView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'auth',
      component: () => import('../views/AuthView.vue'),
      children:[
        {
          path: '',
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
      name: 'Main',
      component: MainView,
      children: [
        {
          path: '',
          name: 'sidebar',
          component: Sidebar
        }
      ]
    },
    {
      path: '/detail-playlist',
      name: 'DetailPlayList',
      component: DetailView
    }
  ],
})

export default router
