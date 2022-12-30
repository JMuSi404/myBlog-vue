<template>
  <div>
    <!-- 	背景图 -->
    <div :style="backgroundStyle">
      <div class="h-bg-text" style="overflow: hidden;">
        <center><p class="text-animation" id="p-title">{{ title }}</p></center>
      </div>
    </div>
    <!-- 	主体 -->
    <div class="h-padded-td-big" id="Indexbody">
      <div class="ui container">
        <div class="ui stackable grid">
          <div class="eleven wide column">
            <!--  content -->
              <blog-list :list="blogList"></blog-list>
          </div>
          <!-- 	侧边栏 -->
          <div class="five wide column animate__animated animate__fadeIn">
            <div class="d-web-info-box" data-wow-duration="0.4s" id="H-Sidebar">
              <div class="d-web-user-info-box">
                <img :src="$api.URL+'/avatar/myAvatar.jpg'" class="avatarImg" width="80px" height="80px">
                <h3>Joe</h3>
                <span>往者不可谏,来者犹可追</span>
              </div>
              <div class="ui aligned centered grid h-padded-td-large">
                <span class="m-text">文章<br><a href="javascript:;" class="m-text-span"
                                              id="blogCount">&nbsp;{{ BlogCount }}</a></span>
                <span class="m-text">标签<br><a href="javascript:;" class="m-text-span"
                                              id="TagCount">&nbsp;{{ TagCount }}</a></span>
                <span class="m-text">评论<br><a href="javascript:;" class="m-text-span"
                                              id="commentCount">&nbsp;{{ CommentCount }}</a></span>
                <span class="m-text">浏览量<br><a href="javascript:;" class="m-text-span"
                                               id="ViewCount">&nbsp;{{ ViewCount }}</a></span>
              </div>
              <div class="h-padded-td-small">
                <span><i class="rocket icon"></i>社交</span>
              </div>
              <br>
              <div class="ui aligned centered  middle grid h-padded-td">
                <p class="h-text-font-size"><i class="copyright orange icon"></i><a
                    href="https://blog.csdn.net/qq_53282665" target="_blank">&nbsp;CSDN</a></p>
                <p class="h-text-font-size"><i class="github square black icon"></i><a
                    href="https://github.com/JMuSi404" target="_blank">&nbsp;GitHub</a></p>
              </div>
              <div class="h-padded-td-small">
                <span><i class="tags icon"></i>标签</span>
              </div>
              <div class="h-padded-td-small">
                <!--							标签-->
                <div class="ui">
                  <a @click="$router.push({path:'/tags',query:{tagId:item.id}})" v-for="item in tagList" href="javascript:;" class="ui left label  m-margin-tb-mid"
                     :class="item.color"><span>{{ item.name }}</span></a>
                </div>
              </div>
              <div class="h-padded-td-small">
                <span><i class="globe icon"></i>网站运行时间</span>
              </div>
              <div class="h-padded-td-small">
                <span>{{ currentTimeHtml }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import api from "@/api";
import blogList from "@/components/blogList";
export default {
  components:{
    blogList
  },
  data() {
    return {
      backgroundStyle: {
        marginTop: "60px",
        background: "url('/assets/img/b" + Math.round(Math.random() * (2 - 1) + 1) + ".jpg')  center center / cover no-repeat"
      },
      title: '',
      pageNum: 1,
      limit: 6,
      blogList: [],
      tagList: [],
      blogListTotal: 0,
      loadingShow: false,
      percentScroll: 0,
      BlogCount: null,
      CommentCount: null,
      TagCount: null,
      ViewCount: null,
      currentTimeHtml: '',
      IntervalOne: null,
      IntervalTwo: null
    }
  },
  created() {
    this.getTitle()
    this.getIndexBlogList()
    this.getIndexTagList()
    this.getIndexBlogsCount()
    this.listenerFunction();
    this.setTime()
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.listenerFunction);
    clearInterval(this.IntervalOne)
    clearTimeout(this.IntervalTwo)
  },
  methods: {
    getTitle() {
      this.axios({
        method: 'get',
        url: api.getTitle,
        params: {
          c: "d"
        }
      }).then(res => {
        this.title = res.data.hitokoto
      })
    },
    getIndexBlogList() {
      this.axios({
        method: 'get',
        url: api.getIndexBlogList,
        params: {
          pageNum: this.pageNum,
          limit: this.limit
        }
      }).then(res => {
        let rep = res.data
        this.blogList = rep.data.list
        this.blogListTotal = rep.data.total
      })
    },
    getIndexTagList() {
      this.axios({
        method: 'get',
        url: api.getIndexTagList
      }).then(res => {
        let rep = res.data
        this.tagList = rep.data
        this.tagList.forEach(item => {
          let color = new Array("orange", "teal", "blue", "grey", "red");
          let random = Math.floor(Math.random() * 10) % color.length;
          item.color = color[random]
        })
      })
    },
    getIndexBlogsCount() {
      this.axios({
        method: 'get',
        url: api.getIndexBlogsCount
      }).then(res => {
        let rep = res.data
        this.BlogCount = rep.data.BlogCount
        this.CommentCount = rep.data.CommentCount
        this.TagCount = rep.data.TagCount
        this.ViewCount = rep.data.ViewCount
      })
    },
    addLimit() {
      this.IntervalTwo = setTimeout(() => {
        this.limit += 6
        this.getIndexBlogList()
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
      if (this.percentScroll >= 99&&this.blogListTotal != this.blogList.length) {
          this.addLimit()
      }
    },
    setTime() {
      this.IntervalOne = setInterval(() => {
        /*此处为网站的创建时间*/
        let create_time = Math.round(new Date(Date.UTC(2022, 0o5, 22, 18, 0o1, 0o1)).getTime() / 1000);
        let timestamp = Math.round((new Date().getTime() + 8 * 60 * 60 * 1000) / 1000);
        let currentTime = this.secondToDate((timestamp - create_time));
        this.currentTimeHtml = '本站已运行：' + currentTime[0] + '年' + currentTime[1] + '天'
            + currentTime[2] + '时' + currentTime[3] + '分' + currentTime[4]
            + '秒';
      }, 1000)
    },
    secondToDate(second) {
      if (!second) {
        return 0;
      }
      var time = new Array(0, 0, 0, 0, 0);
      if (second >= 365 * 24 * 3600) {
        time[0] = parseInt(second / (365 * 24 * 3600));
        second %= 365 * 24 * 3600;
      }
      if (second >= 24 * 3600) {
        time[1] = parseInt(second / (24 * 3600));
        second %= 24 * 3600;
      }
      if (second >= 3600) {
        time[2] = parseInt(second / 3600);
        second %= 3600;
      }
      if (second >= 60) {
        time[3] = parseInt(second / 60);
        second %= 60;
      }
      if (second > 0) {
        time[4] = second;
      }
      return time;
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
