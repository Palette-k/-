import { createApp } from 'vue'
import { store } from '@/store'
import App from './App.vue'
import'lib-flexible/flexible.js'
import '@/utils/rem'

//配置路由
import {router} from "@/router";

import './assets/global.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import * as ELIcons from '@element-plus/icons-vue'
/*import '@icon-park/vue-next/styles/index.css'*/
const app = createApp(App)
    for(const iconName in ELIcons){
        app.component(iconName,ELIcons[iconName])
    }
    app.use(router)
    app.use(ElementPlus)
    app.use(store)
    app.mount('#app')
