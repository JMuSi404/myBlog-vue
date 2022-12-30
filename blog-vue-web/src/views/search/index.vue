<template>
<div style="margin-top: 66px;">
  <!-- 	主体 -->
  <div class="h-body-bottom h-margin-show h-padded-td-large">
    <div class="ui container" style="width: 80%">
      <!-- 标签 -->
      <div class="ui segment h-boder-radius animate__animated animate__fadeIn">
        <!-- 头部 -->
        <div class="ui top h-boder-radius">
          <div class="ui middle aligned two column grid">
            <div class="column">
              <h3 class="ui  header">搜索结果 {{total}} 条</h3>
            </div>
          </div>
        </div>
      </div>
      <!-- 内容 -->
       <blog-list :list="list"></blog-list>
      <el-pagination
          style="display: flex;justify-content: right"
          v-if="total>6"
          :page-size="limit"
          layout="prev, pager, next"
          @current-change="turnPages"
          :total="total">
      </el-pagination>
    </div>
  </div>
</div>
</template>

<script>
import {mapGetters} from 'vuex'
import api from "@/api";
import blogList from "@/components/blogList";
export  default {
  name:'search',
  components:{
    blogList
  },
  data(){
    return{
      page:1,
      limit:6,
      list:[],
      total:0
    }
  },
  created() {
    this.getBlogByKeyed()
  },
  computed:{
     ...mapGetters({
       getKeyed:'getKeyed'
     })
  },
  methods:{
   getBlogByKeyed(){
     this.axios({
       method: 'get',
       url: api.getBlogByKeyed,
       params:{
         pageNum:this.page,
         limit:this.limit,
         title:this.getKeyed
       }
     }).then(res => {
       let rep=res.data
       this.list=rep.data.list
       this.total=rep.data.total
     })
   },
    turnPages(page){
      this.page=page
      this.getBlogByKeyed()
    }
  },
  watch:{
    getKeyed(newValue,oldValue){
      this.getBlogByKeyed();
    }
  }
}
</script>
