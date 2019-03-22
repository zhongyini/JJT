import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css' // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题
import 'vue-awesome/icons'
import Icon from 'vue-awesome/components/Icon'

Vue.config.productionTip = false
Vue.component('v-icon', Icon)
Vue.use(ElementUI, {
  size: 'small'
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
