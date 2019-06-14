const path = require('path')
const UglifyJsPlugin = require('uglifyjs-webpack-plugin')

function resolve (dir) {
  return path.join(__dirname, dir)
}

module.exports = {
  // 基本路径
  publicPath: '/',
  // 输出文件目录
  outputDir: 'dist',
  // eslint-loader是否在保存的时候检查,vue-cli3中compiler 换成了 runtimeCompiler
  // compiler: true,
  runtimeCompiler: false,
  chainWebpack: config => {
    if (process.env.NODE_ENV === 'production') {
      config
        .plugin('uglify')
        .tap(([options]) => {
          // 去除 console.log
          return [Object.assign(options, {
            uglifyOptions: {
              compress: {
                drop_console: true,
                pure_funcs: ['console.log']
              }
            }
          })]
        })
    }
    config.resolve.alias.set('@', resolve('src'))
    config.module.rules.delete('svg')
    config.module.rule('svg-smart')
      .test(/\.svg$/).include
      .add(resolve('src/assets/svg'))
      .end().use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: '[name]'
      })
  },
  configureWebpack: (config) => {
    let optimization = {
      minimizer: [ new UglifyJsPlugin({
        uglifyOptions: {
          compress: {
            warnings: false,
            drop_console: true,
            consoledrop_debugger: false,
            pure_funcs: ['console.log']
          }
        }
      })]
    }
    Object.assign(config, {
      optimization
    })
  },
  // https://vue-loader.vuejs.org/en/options.html
  // vueLoader: {},
  // 生产环境是否生成 sourceMap 文件
  productionSourceMap: false,
  // css相关配置
  css: {
    // 是否使用css分离插件 ExtractTextPlugin
    extract: true,
    // 开启 CSS source maps?
    sourceMap: false,
    // css预设器配置项
    loaderOptions: {},
    // 启用 CSS modules for all css / pre-processor files.
    modules: false
  },
  parallel: require('os').cpus().length > 1,
  pwa: {},
  lintOnSave: true
  // webpack-dev-server 相关配置
  /*
  devServer: {
    open: process.platform === 'darwin',
    host: '0.0.0.0',
    port: 3000,
    https: false,
    hotOnly: false,
    proxy: {
      '/api': {
        target: 'http://localhost:3000',
        changeOrigin: true,
        pathRewrite: {
          '^/api': '/mock'
        }
      }
    },
    before: app => {}
  }
  */
}
