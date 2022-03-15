import { createRouter, createWebHistory } from 'vue-router'


const routes = [

  {
    path: '/',
    name: 'main',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Main.vue')
  },{
  path:'/ex',
    name:'ex',
    component: () => import('../views/ex')
  },
  {
    path:'/img',
    name:"img",
    component:() => import('../views/Img.vue')
  },
  {path:'/layout',
  name:"lay",
    component:() => import('../views/layout.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
