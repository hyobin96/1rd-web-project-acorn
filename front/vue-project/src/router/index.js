import EventBoardHeader from '@/components/EventBoardHeader.vue'
import Sidebar from '@/components/Sidebar.vue'
import AdminView from '@/views/AdminView.vue'
import CreatePlaylistView from '@/views/CreatePlaylistView.vue'
import DetailView from '@/views/DetailView.vue'
import EventBoardView from '@/views/EventBoardView.vue'
import MainView from '@/views/MainView.vue'
import MyPageView from '@/views/MyPageView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import axios from '@/api/axios'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/login',
      name: 'auth',
      component: () => import('../views/AuthView.vue'),
      children: [
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
      path: '/detail-playlist/:index',
      name: 'DetailPlayList',
      component: DetailView
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MyPageView
    },
    {
      path: '/events',
      name: 'event-board',
      component: EventBoardView,
      children: [
        {
          path: 'event-header',
          name: 'event-header',
          component: () => import('../components/EventBoardHeader.vue')
        }
      ]
    },
    {
      path: '/admin',
      name: 'adminpage',
      component: AdminView,
      meta: { requiresAdmin: true },
      children: [
        {
          path: 'event-management',
          name: 'event-management',
          component: () => import('../components/EventManagementForm.vue')
        }, {
          path: 'user-management',
          name: 'user-management',
          component: () => import('../components/UserList.vue')
        },

      ]
    }
  ],
})

const publicPages = ['/login', '/regist']

router.beforeEach(async (to, from) => {
  if (publicPages.includes(to.path)) {
    return true
  }

  // 관리자인지 확인
  if (to.meta.requiresAdmin) {
    try {
      await axios.get('auth/admin')
      return true
    } catch (err) {
      if (err.response?.status === 403) {
        return '/'
      }
      console.error('인증 확인 실패:', err)
      return false
    }
  }
  // 로그인했는지 확인
  else {
    try {
      // 서버에 인증 확인 요청
      await axios.get('auth')  // 이 API는 인증이 안되면 403 반환해야 함
      return true
    } catch (err) {
      if (err.response?.status === 403) {
        return '/'  // 인증 안 된 경우 로그인 페이지로
      }
      console.error('인증 확인 실패:', err)
      return false  // 기타 에러 시 페이지 이동 막기
    }
  }
})

export default router
