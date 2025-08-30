import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Schedule from '../schedule/schedule.vue'
import makingSchedule from '../makingSchedule/makingSchedule.vue'
import entranceAccount from '../entranceAccount/entrance.vue'
import adminPanel from '../adminPanel/adminPanel.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/schedule',
    name: 'schedule',
    component: Schedule
  },
  {
    path: '/makingSchedule',
    name: 'makingSchedule',
    component: makingSchedule
  },
  {
    path: '/entranceAccount',
    name: 'entranceAccount',
    component: entranceAccount
  },
  {
    path: '/adminPanel',
    name: 'adminPanel',
    component: adminPanel
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
