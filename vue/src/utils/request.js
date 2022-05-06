import axios from 'axios'
import cookie from 'js-cookie'
import JSONBIG from 'json-bigint'
const request = axios.create({
    baseURL: 'http://511u188n49.zicp.vip/api/',  // 注意！！ 这里是全局统一加上了 后端接口前缀 前缀，后端必须进行跨域配置！
    timeout: 8000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    //如果有token值
    if(cookie.get('token')) {
        //token值放到cookie里面
        config.headers['token']=cookie.get('token')
    }

    return config;
}, error => {
    return Promise.reject(error)
});



// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;

       /* if(res.code === 406) {
            //去登录页面
            this.$router.push('/login');
            return
        }
*/
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)

request.defaults.transformResponse = [
    function (data) {
        const json = JSONBIG({
            storeAsString: true
        })
        const res = json.parse(data)
        return res
    }
]


export default request

