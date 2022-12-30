<template>
  <div style="margin-top: 88px;">
    <div class="ui container">
      <div class="block animate__animated animate__fadeIn archiveList">
          <el-timeline>
            <el-timeline-item
                type="primary"
                size="large">
              <span style="font-size: 20px;">目前共计{{blogTotal}}篇文章，继续加油</span>
            </el-timeline-item>
            <el-timeline-item
                v-for="(activity, index) in list"
                :key="index"
                :timestamp="activity.createtime">
              <span @click="toBlogDetails(activity.id)" style="font-size: 18px;color: #505053;cursor: pointer">{{activity.title}}</span>
            </el-timeline-item>
          </el-timeline>
        </div>
        <el-pagination
            style="display: flex;justify-content: right"
            v-if="blogTotal>24"
            :page-size="limit"
            layout="prev, pager, next"
            @current-change="turnPages"
            :total="blogTotal">
        </el-pagination>
      </div>
  </div>
</template>

<script>
import api from "@/api";
export  default {
  name:'archive',
  data(){
    return{
      reverse:false,
      limit:24,
      page:1,
      list:[],
      blogTotal:0
    }
  },
  created() {
    this.getBlogArchive()
  },
  methods:{
    getBlogArchive(){
      this.axios({
        method: 'get',
        url: api.getBlogArchive,
        params:{
          pageNum:this.page,
          limit:this.limit
        }
      }).then(res => {
        let rep=res.data
        this.list=rep.data.list
        this.blogTotal=rep.data.total
      })
    },
    turnPages(page){
      this.page=page
      this.getBlogArchive()
    },
    toBlogDetails(id) {
      this.$router.push({
        name: 'blog',
        query: {
          blogId: id
        }
      })
    }
  }
}
</script>
