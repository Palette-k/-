//导入vue
// import Vue from 'vue';
// import VueRouter from 'vue-router';
import {createRouter,createWebHistory} from "vue-router";
//导入组件
import Layout from "../views/Layout";
import Login from "../views/Login";
import Register from "@/views/Register";
//import Login1 from "@/components/Login1";
//使用
// Vue.use(VueRouter);
//导出
// export default new VueRouter({
const routes=[
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
        }
    ]

const router =new createRouter({
    history: createWebHistory(),
    routes,
})

export {
    router,
}