import { createApp } from 'vue'
import App from './App.vue'

//配置路由
import {router} from "@/router";

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
    app.mount('#app')
