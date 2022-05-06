const { defineConfig } = require('@vue/cli-service')
//vue.confiig.js

// 引入等比适配插件
const px2rem = require('postcss-px2rem')

// 配置基本大小
const postcss = px2rem({
  // 基准大小 baseSize，需要和rem.js中相同
  remUnit: 16
})




module.exports = defineConfig({
  devServer:{
    port:8084,
  },
  transpileDependencies: true,
  productionSourceMap: false,
  lintOnSave: false,
  //publicPath: './com.gdufe.cs/',
  outputDir: 'dist',
  assetsDir: 'assets',

  // 使用等比适配插件
/*  lintOnSave: true,
  css: {
    loaderOptions: {
      postcss: {
        plugins: [
          postcss
        ]
      }
    }
  }*/

 // filenameHashing:false

 /* chainWebpack: config => {

    config.module

        .rule('css')

        .test(/\.css$/)

        .oneOf('vue')

        .resourceQuery(/\?vue/)

        .use('px2rem')

        .loader('px2rem-loader')

        .options({

          remUnit: 128   //代表的是 1rem = ？px  这里假设设计稿是 1920px ，那么这里的比例就是 1/10

        })

        .end()

  }*/
/*  test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
      loader: 'url-loader',
   // exclude: [resolve('src/assets')],
    options: {
  limit: 10000,
      name: util.assetsPath('image/[name].[hash:7].[ext]')
}*/


})







