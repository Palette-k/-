//导入vue
//import Vue from 'vue';
//import VueRouter from 'vue-router';
import {createRouter, createWebHistory} from "vue-router";
//导入组件
import Layout from "../views/Layout";
import Login from "../views/Login";
import Register from "@/views/Register";
import Home from "@/views/Home";
import Manage from "@/views/Manage";
import Home1 from "@/views/Home1.vue";
import Comment from "@/views/Comment.vue";
import AdminMovie from "@/views/Movie.vue";
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
        path: '/admin',
        component: Manage,

       redirect: '/admin/home',
        children: [
            {path: '/admin/home', name: '首页', component: Home1},
            {path: '/admin/comment', name: '历史评论', component: Comment},
            {path: '/admin/user', name: '个人资料', component: () => import('../views/User.vue')},
            {path: '/admin/movie', name: '详细信息', component: AdminMovie},


        ]
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    }
]

const router = new createRouter({
    history: createWebHistory(),
    routes,
})

export {
    router,
}