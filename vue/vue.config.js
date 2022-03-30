const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  productionSourceMap: false,
  lintOnSave: false,
  //publicPath: './com.gdufe.cs/',
  outputDir: 'dist',
  assetsDir: 'assets',
 // filenameHashing:false


/*  test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
      loader: 'url-loader',
   // exclude: [resolve('src/assets')],
    options: {
  limit: 10000,
      name: util.assetsPath('image/[name].[hash:7].[ext]')
}*/

})




