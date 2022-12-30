<template>
  <div class="dashboard-container">
    <div class="dashboard-text"></div>
    <el-dialog   :center="true" title="通知"   :visible.sync="drawer">
      <div>欢迎来到Joe博客后台管理系统，开发这个项目主要是为了学习分享以及记录我的生活,也顺便打磨一下自己之前所学的技术.</div>
      <div>运行环境是腾讯云 Centos7 轻量应用服务器2核2G</div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  data(){
    return{
      drawer:false
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
