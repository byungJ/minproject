import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import SignUp from '../views/SignUp.vue'
import MyHome from '../views/MyHome.vue'
import MyHome2 from '../views/MyHome2.vue'
import StudyRegister from '../views/StudyRegister.vue'
import CrawlCategory from '../views/CrawlCategory.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/Login',
    name: 'Login',
    component: Login
  },
  {
    path: '/SignUp',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/MyHome',
    name: 'MyHome',
    component: MyHome
  },
  {
    path: '/MyHome2',
    name: 'MyHome2',
    component: MyHome2
  },
  {
    path: '/StudyRegister',
    name: 'StudyRegister',
    component: StudyRegister
  },
  {
    path: '/CrawlCategory',
    name: 'CrawlCategory',
    components: {
      default: CrawlCategory
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
