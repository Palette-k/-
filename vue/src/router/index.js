//导入vue
// import Vue from 'vue';
// import VueRouter from 'vue-router';
import {createRouter,createWebHistory} from "vue-router";
//导入组件
import Home from "@/views/Home";
import Main from "../views/Main";
import Login from "../views/Login";
import Register from "@/views/Register";
import Movie from "@/views/Movie";
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
            path: '/main',
            component: Main
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/register',
            component: Register
        },
    {
        path: '/movie',
        component: Movie
    }
    ]

const router =new createRouter({
    history: createWebHistory(),
    routes,
})

export {
    router,
}