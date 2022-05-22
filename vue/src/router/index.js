//导入vue
//import Vue from 'vue';
//import VueRouter from 'vue-router';
import {createRouter, createWebHistory} from "vue-router";
//导入组件
import About from "@/views/About";
import Layout from "../views/Layout";
import Login from "../views/Login";
import Register from "@/views/Register";
import Home from "@/views/Home";
import Manage from "@/views/Manage";
import Home1 from "@/views/Home1.vue";
import Comment from "@/views/Comment.vue";
import AdminMovie from "@/views/Movie.vue";
import category from "@/views/category.vue";
import workscate from "@/views/workscate"
import carousel from "@/views/carousel.vue"
import userInfo from "@/components/member/UserInfo"
import article from "../components/Article/article"
import notification from "@/components/member/notification"
import movieclassify from "@/views/movie-classify"
import chat from "@/components/chat/chat"
import simpleChat from '@/components/chat/simpleChat'
//import Login1 from "@/components/Login1";
//使用
//Vue.use(VueRouter);
//导出
// export default new VueRouter({

const routes = [
    {
        path: '/',
        component: Home
    },
    {
        //首页
        path: '/layout',
        component: Layout
    },
    //登录页
    {
        path: '/login',
        component: Login
    },
    {
        path: '/register',
        component: Register
    },
    {
        path: '/userInfo',
        component: userInfo

    },
    {
        path: '/article',
        component: article,
    },
    {
        path: '/notification',
        component: notification,
    },
    {
        path:'/movie',
        component:movieclassify

    },

    {
        path: '/chat',
        component: chat
    },
    {
      path: '/simpleChat',
      component: simpleChat
    },
    {
        path: '/admin',
        component: Manage,

       redirect: '/admin/home',
        children: [
            {path: '/admin/home', name: '首页', component: Home1},
            {path: '/admin/comment', name: '历史评论', component: Comment},
            {path: '/admin/user', name: '个人资料', component: () => import('../views/User.vue')},
            {path: '/admin/movie', name: '详细信息', component: AdminMovie},
            {path: '/admin/category',name: '作品分类维护',component: category},
            {path: '/admin/workscate',name: '作品形式维护',component: workscate},
            {path: '/admin/carousel',name: '轮播图',component: carousel}

        ]
    },
    {
        path: '/about',
        component: About,
    }
]

const router = new createRouter({
    history: createWebHistory(),
    routes,
})

export {
    router,
}