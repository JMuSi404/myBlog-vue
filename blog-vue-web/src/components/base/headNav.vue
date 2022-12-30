<template>
  <div class="ui top fixed menu">
    <nav class="h-shadow-small ui attached segment h-padded-td-mini">
      <!-- 		滚轮进度条 -->
      <div class="ui bottom indicating attached  progress" data-value="1" data-total="100" id="example">
        <div class="bar"></div>
      </div>
      <div class="ui secondary stackable menu navigation_height">
        <h2 class="ui left header item">
          <div class="content">Joe<span style="font-size: 20px">的小窝</span></div>
        </h2>
        <div class="item  h-item h-moblile-hide">
          <div class="ui item  category search">
            <div class="ui  icon input">
              <input class="prompt" type="text" v-model="keyed"  placeholder="搜索文章"  @keydown:ended="toSearch">
              <i @click="toSearch" class="search link icon" id="search_blog_btn"></i>
            </div>
            <div class="results"></div>
          </div>
        </div>
        <a  @click="$router.push('/')" class="h-item item h-moblile-hide Navbtn"><i class="home icon"></i>首页</a>

        <a  @click="$router.push('/tags')" class="h-item item h-moblile-hide  Navbtn"><i class="tags icon"></i>标签</a>

        <a  @click="$router.push('/archive')" class="h-item item h-moblile-hide  Navbtn"><i class="calendar alternate icon"></i>归档</a>

        <a @click="$router.push('/photoWall')" class="h-item item h-moblile-hide Navbtn"><i class="image icon"></i>照片墙</a>

        <a @click="$router.push({path:'/blog',query:{type:1}})" class="h-item item h-moblile-hide Navbtn"><i class="linkify icon"></i>友链</a>

        <a @click="$router.push({path:'/blog',query:{type:2}})" class="h-item item h-moblile-hide  Navbtn"><i class="address card outline icon"></i>关于我</a>
      </div>
      <a href="#" class="ui icon button h-right-top-storage h-moblile-show" id="stackable-hide"
         style="background-color: #ffffff;">
        <i class="sidebar icon"></i>
      </a>
    </nav>
  </div>
</template>
<script>
import {mapMutations} from 'vuex'
export  default{
  name:'headNav',
  data(){
    return{
      keyed:''
    }
  },
  methods:{
    ...mapMutations({
      setKeyed:'setKeyed'
    }),
    toSearch(){
      this.setKeyed(this.keyed)
       this.$router.push({
         path:'/search'
       })
    }
  }
}
</script>
