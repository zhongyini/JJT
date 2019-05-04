<template>
  <div class="content">
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="rgb(58, 235, 253)"
      text-color="#fff"
      active-text-color="#000"
      route=true>
      <el-menu-item index="1">主页</el-menu-item>
      <el-submenu index="2">
        <template slot="title">内容</template>
        <el-menu-item index="2-1">内容1</el-menu-item>
        <el-menu-item index="2-2">内容2</el-menu-item>
        <el-menu-item index="2-3">内容3</el-menu-item>
      </el-submenu>
      <el-menu-item index="3">关于</el-menu-item>
    </el-menu>
  </div>
</template>
<script>
export default {
  data () {
    return {
      activeIndex: '1',
      activeIndex2: '1'
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>
