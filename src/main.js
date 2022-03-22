import { createApp } from 'vue'
import App from './App.vue'
import 'element-plus/theme-chalk/index.css'
import ElementPlus from 'element-plus'
import router from './router'

let app=createApp(App).use(router)
app.use(ElementPlus)

app.use(router).mount('#app')