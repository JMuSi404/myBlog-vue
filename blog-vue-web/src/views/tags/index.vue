<template>
<div style="margin-top: 66px;">
  <!-- 	主体 -->
  <div class="h-body-bottom h-margin-show h-padded-td-large">
    <div class="ui container" style="width: 80%">
      <!-- 标签 -->
      <div v-if="TagId==null" class="d-web-info-box animate__animated animate__fadeIn">
        <center><h1>标签-{{tagTotal}}</h1></center>
        <div class="ui h-padded-td-large aligned centered middle grid h-boder-radius" >
          <div class="ui labeled button h-margin-td-tiny" v-for="item of tagList" style="margin-left: 15px" >
            <a href="javascript:;" class="ui tagsBtn" :style="{fontSize:item.fontSize+'px'}"  @click="TagId=item.id;getBlogByTag()">{{item.name}}</a>
          </div>
        </div>
      </div>
      <!-- 内容 -->
      <blog-list  v-if="TagId!=null" :list="blogListInfo"></blog-list>
      <el-pagination
          style="float: right"
          v-if="TagId!=null&&blogTotal>6"
          :page-size="limit"
          layout="prev, pager, next"
          @current-change="turnPages"
          :total="blogTotal">
      </el-pagination>
      <el-empty description="空空如也" v-if="TagId!=null&&blogTotal==0">
      </el-empty>
    </div>
  </div>
</div>
</template>
<script>
import blogList from "@/components/blogList";
import api from "@/api";
export  default {
  components:{
    blogList
  },
  name:'tags',
  data(){
    return{
      TagId:null,
      tagList:[],
      tagTotal:0,
      page:1,
      limit:6,
      blogListInfo:[],
      blogTotal:0
    }
  },
  created() {
    if (this.$route.query.tagId){
       this.TagId=this.$route.query.tagId
       this.getBlogByTag()
    }else {
      this.getTags()
    }
  },
  methods:{
    getTags(){
      this.axios({
        method: 'get',
        url: api.getTags
      }).then(res => {
        let rep=res.data
        this.tagList=rep.data.list
        this.tagList.forEach(item=>{
          item.fontSize=Math.round(Math.random()*(30-15)+15)
        })
        this.tagTotal=rep.data.total
      })
    },
    getBlogByTag(){
      this.axios({
        method: 'get',
        url: api.getBlogByTag+this.TagId,
        params:{
          pageNum:this.page,
          limit:this.limit
        }
      }).then(res => {
        let rep=res.data
        this.blogListInfo=rep.data.list
        this.blogTotal=rep.data.total
      })
    },
    turnPages(page){
      this.page=page
      this.getBlogByTag()
    }
  }
}
</script>
