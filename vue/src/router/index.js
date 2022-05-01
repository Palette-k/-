//导入vue
// import Vue from 'vue';
// import VueRouter from 'vue-router';
import {createRouter,createWebHistory} from "vue-router";
//导入组件
import Home from "@/views/Home";
import Login from "@/views/Login";
import Register from "@/views/Register";
import About from "@/views/About";
import Layout from "@/views/Layout";
import Manage from "@/views/Manage";
import article from "../components/article"
//import Login1 from "@/components/Login1";
//使用
// Vue.use(VueRouter);
//导出
// export default new VueRouter({
const routes=[
        {
            path: '/',
            component: Home
        },
        {
            path: '/layout',
            component: Layout
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/about',
            component: About,
        },
        {
          path: '/article',
          component: article,
        },
        {
            path: '/manage',
            component: Manage,
            children: [
                {path: '/layout', name: '首页', component: () => import('../views/Home.vue')},
                {path: '/comment', name: '历史评论', component: () => import('../views/Comment.vue')},
                {path: '/movie', name: '详细信息', component: () => import('../views/Movie.vue')},

            ]
        },
    ]

const router =new createRouter({
    history: createWebHistory(),
    routes,
})

export {
    router,
}