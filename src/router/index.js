import { createRouter, createWebHistory } from 'vue-router'

  const  routes=[

  {path:'/layout',
   name:"lay",
    component:() => import('../views/layout.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
