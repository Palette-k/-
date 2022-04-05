const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  productionSourceMap: false,
  lintOnSave: false,  //关闭语法检查　
  //publicPath: './com.gdufe.cs/',
  outputDir: 'dist',
  assetsDir: 'assets'
})


