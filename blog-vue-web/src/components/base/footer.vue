<template>
  <div style="margin-top: 20px">
    <!--音乐插件-->
    <meting-js
        server="netease"
        type="playlist"
        id="7470734184"
        fixed="true"
        loop="all"
        order="random"
        preload="auto"
        list-folded="ture"
        list-max-height="500px"
        lrc-type="1">
    </meting-js>
    <footer class="ui attached segment">
      <div class="ui container">
        <center>
          <p>©2022 By Joe</p>
          <a href="https://beian.miit.gov.cn">
            湘ICP备2022011818号</a>
        </center>
      </div>
    </footer>
  </div>
</template>
<script>
export  default{
  name:'Footer'
}
</script>
<style lang="scss" scoped>
>>>.el-backtop{

}
</style>
