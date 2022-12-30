<template>
  <div id="app">
    <head-nav></head-nav>
    <div style="min-height: 666px">
      <router-view></router-view>
    </div>
    <BackTop></BackTop>
    <meting></meting>
    <Footer></Footer>
  </div>
</template>
<script>
import meting from "@/components/meting";
import BackTop from "@/components/BackTop";
import Footer from "@/components/base/footer";
import headNav from "@/components/base/headNav";
export  default{
  name: 'App',
  components:{
    headNav,Footer,BackTop,meting
  }
}
</script>
<style lang="scss" scoped>
body {
  background-color: #fcfcfc;
}
.h-body-bottom {
  margin: auto;
  margin-top: 66px;
}
.h-boder-radius {
  border-radius: 10px;
}
.h-box-margin-left{
  margin-left: 10px;
}
</style>
