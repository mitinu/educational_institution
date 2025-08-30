const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  outputDir: '../backend/src/main/resources/build',
  transpileDependencies: true,
  lintOnSave: false
})
