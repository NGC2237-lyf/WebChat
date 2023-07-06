const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  lintOnSave: false, //关闭语法检查
  transpileDependencies: true,
  publicPath: './',  //打包优化
  devServer: {
    client: {
      overlay: false,
    },
  },
  proxy: {
    '/': {
      target: 'http://192.168.0.117:8080', // 代理地址，这里设置的地址会代替axios中设置的baseURL
      changeOrigin: true, // 如果接口跨域，需要进行这个参数配置
      ws: true,
      pathRewrite: {
        '^/': ''
      }
    },

  }
})
