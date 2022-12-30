<template>
<div style="margin-top: 66px;">
    <div style="display: flex;padding: 0 2.5%">
      <div v-for="i in num"    style="flex: 1;display: flex;justify-content:flex-start;align-items: center;flex-direction: column">
        <el-image
            class="animate__animated animate__fadeIn"
            v-if="i==1"
            v-for="(item,index) of list.slice((i-1),Math.ceil(list.length/num))"
            style="margin: 5px;"
            :src="item.url"
            :preview-src-list="item.srcList">
        </el-image>
        <el-image
            class="animate__animated animate__fadeIn"
            v-if="i>1&&i<num"
            v-for="(item,index) of list.slice(Math.ceil(list.length/num)*(i-1),Math.ceil(list.length/num)*i)"
            style="margin: 5px;"
            :src="item.url"
            :preview-src-list="item.srcList">
        </el-image>
        <el-image
            class="animate__animated animate__fadeIn"
            v-if="i==num"
            v-for="(item,index) of list.slice(Math.ceil(list.length/num)*(i-1))"
            style=" margin: 5px;"
            :src="item.url"
            :preview-src-list="item.srcList">
        </el-image>
      </div>
    </div>
</div>
</template>
<script>
import api from "@/api";
export  default {
  name:'photoWall',
  data() {
    return {
      list:[],
      total:0,
      limit:9,
      page:1,
      num:3,
      IntervalOne:null,
      percentScroll:null
    }
  },
  created(){
    this.getImage()
    this.listenerFunction();
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.listenerFunction);
    clearTimeout(this.IntervalOne)
  },
  methods:{
    getImage(){
      this.axios({
        method: 'get',
        url: api.getImageList,
        params:{
          pageNum:this.page,
          limit:this.limit
        }
      }).then(res => {
        let rep=res.data
        this.list=rep.data.list
        this.total=rep.data.total
        this.list.forEach(item=>{
          item.srcList=[item.url]
        })
      })
    },
    addLimit() {
      this.IntervalOne = setTimeout(() => {
        this.limit += 9
        this.getImage()
      }, 1000)
    },
    listenerFunction(e) {
      document.addEventListener('scroll', this.handleScroll, true);
    },
    handleScroll() {
      //htmlHeight 是网页的总高度
      let htmlHeight = document.documentElement.scrollHeight;
      //clientHeight是网页在浏览器中的可视高度，
      let clientHeight = document.documentElement.clientHeight;
      //scrollTop是浏览器滚动条的top位置，
      let scrollTop = document.documentElement.scrollTop;
      //通过判断滚动条的top位置与可视网页之和与整个网页的高度来返回各项参数
      this.percentScroll = (scrollTop / (htmlHeight - clientHeight)) * 100; //该值为滚动条的页面百分比
      if (this.percentScroll >=99) {
        if (this.total != this.list.length){
          this.addLimit()
        }
      }
    }
  }
}
</script>
