const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  outputDir: '../backend/src/main/resources/build',
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false
      }
    }
  }
})
