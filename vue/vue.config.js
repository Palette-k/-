const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  productionSourceMap: false,
  //publicPath: './com.gdufe.cs/',
  outputDir: 'dist',
  assetsDir: 'assets'
})


