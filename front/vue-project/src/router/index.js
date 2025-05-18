import CreatePlaylistView from '@/views/CreatePlaylistView.vue'
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
      ]
    },
    {
      path: '/create-playlist',
      name: 'CreatePlayList',
      component: CreatePlaylistView,
    }
  ],
})

export default router
