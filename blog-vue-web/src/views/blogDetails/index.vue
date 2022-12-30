<template>
<div style="margin-top: 66px;">
  <!-- 	主体 -->
  <div class="h-body-bottom h-padded-td-big animate__animated animate__fadeIn">
    <div class="ui container">
      <!-- 头部 -->
      <div class="ui top attached">
        <div class="ui mini horizontal link list">
          <div class="item">
            <div class="content">
              <span class="header"><i class = "user outline red icon" ></i>&nbsp;{{blog.userName}}</span>
            </div>
          </div>
          <div class="item">
            <i class="eye green icon"></i>&nbsp;{{blog.views}}
          </div>
          <div class="item">
            <i class="edit blue icon"></i>&nbsp;{{blog.edittime!=null && blog.edittime!=''?blog.edittime:blog.createtime}}
          </div>
          <div class="item">
            <i class="comment alternate outline orange icon"></i>&nbsp;{{blog.commentCount}}
          </div>
        </div>
      </div>
      <div class="ui stackable grid">
        <div class="fourteen wide column">
          <!-- 	内容 -->
          <div class="ui segment h-boder-radius">
            <h1 class="ui   dividing  header">
              <div class="content">{{blog.title}}</div>
            </h1>
            <!-- 文章内容 -->
            <div id="js-toc-content" class="typo typo-selection  h-padded-lr"  v-html="blog.content">

            </div>
            <!-- 标签 -->
            <div>
              <br>
              <div class="ui  basic label" v-text="blog.flag" :class="blog.flag=='原创' ?'teal':'red'"></div>
              <span id="TagList" class="ui"></span>
            </div>
            <div class="ui floating message">
              <p>文章作者：<a>{{blog.userName}}</a></p>
              <p>版权声明：本站文章等内容仅代表作者本人的个人观点，本站不保证文章等内容的真实性和有效性。转载请注明文章出处。</p>
            </div>
          </div>
          <div class="ui bottom attached" v-if="blog.commentabled">
            <!-- 留言区域 -->
            <div class="ui teal segment" id="comment_list">
              <div class="ui threaded comments" style="max-width: 100%;">
                <h3 class="ui dividing header">留言 <span style="color: #00B5AD">{{blog.commentCount}}</span></h3>
                <!--留言遍历-->
                <div>
                  <div class="comment"  v-for="(item,index)  of commentList">
                    <a class="avatar">
                      <img :src="item.avatar">
                    </a>
                    <div class="content">
                      <a class="author">{{item.nickname}}</a>
                      <div class="metadata">
                        <span class="date">{{item.createtime}}</span>
                      </div>
                      <div class="text">
                        <p>{{item.content}}</p>
                      </div>
                      <div class="actions">
                        <a class="reply" @click="reply(item.id,item.nickname)">回复</a>
                      </div>
                    </div>
                    <div class="comments">
                      <div class="comment" v-for="(cItem,cIndex)  of item.list">
                        <a class="avatar">
                          <img :src="cItem.avatar">
                        </a>
                        <div class="content">
                          <a class="author" v-if="cItem.extendsName==null">{{cItem.nickname}}</a>
                          <a class="author" v-if="cItem.extendsName!=null">{{cItem.nickname}} 回复 @{{cItem.extendsName}}</a>
                          <div class="metadata">
                            <span class="date">{{cItem.createtime}}</span>
                          </div>
                          <div class="text">
                            <p>{{cItem.content}}</p>
                          </div>
                          <div class="actions">
                            <a class="reply"  @click="reply(item.id,cItem.nickname)">回复</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div style="display: flex;align-items: center;justify-content: center;margin: 10px 0;cursor: pointer">
                  <div  @click="addLimit"  v-if="total>10&&commentList.length!=total" style="color: #777777">加载更多评论<i class="angle double down icon"></i></div>
                </div>
              </div>
            </div>
            <!-- 	评论区 -->
            <div class="ui form">
              <div  class="field">
                <textarea  ref="textarea" v-model="commentData.content" name="content" :placeholder="commentData.extendsName!=null?'回复 '+commentData.extendsName:'输入评论信息....'"></textarea>
              </div>
              <div class="fields">
                <div class="field h-mobile-wide h-margin-bottom-mini">
                  <div class="ui corner labeled input">
                    <div class="ui input focus" id="qq_input_box">
                      <img id="qq_avatar" class="ui avatar circular image" :src="commentData.avatar" style="width: 2.5em;height: 2.5em;">
                      <input type="text"  @blur="getQQInfo"  v-model="qqNumber"  placeholder="填写QQ号自动拉取信息">
                    </div>
                  </div>
                </div>
                <div class="field h-mobile-wide h-margin-bottom-mini">
                  <div class="ui left icon input">
                    <i class="user icon"></i>
                    <input type="text"  v-model="commentData.nickname" placeholder="请输入昵称(必填)" />
                  </div>
                </div>
                <div class="field h-mobile-wide h-margin-bottom-mini">
                  <div class="ui left icon input">
                    <i class="mail icon"></i>
                    <input type="text" v-model="commentData.email" placeholder="请输入邮箱(必填)" />
                  </div>
                </div>
                <div class="file h-mobile-wide h-margin-bottom-mini">
                  <div   @click="cancelReply" v-if="this.commentData.extendsCommentid!=null" class="ui h-mobile-wide  button">
                    <span>取消回复</span>
                  </div>
                </div>
                <div class="file h-mobile-wide h-margin-bottom-mini">
                  <div  @click="addComment" class="ui teal h-mobile-wide labeled submit icon button">
                    <i class="icon edit"></i>评论
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--				目录-->
        <div class="two wide column">
          <div class="h-moblile-hide" style="position: sticky;top: 100px;">
            <div  style="width: 300px">
              <ol class="js-toc">
              </ol>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import * as tocbot from 'tocbot';
import api from "@/api";
export  default {
  name:'blogDetails',
  data(){
    return{
         blog:{},
         commentList:[],
         pageNum:1,
         limit:10,
         total:0,
         commentData:{
           nickname:null,
           email:null,
           content:null,
           avatar:api.URL+'/avatar/touristAvatar.png',
           extendsCommentid:null,
           administrator:false,
           blogId:null,
           extendsName:null
          },
          qqNumber:null,
          Timeout:null
    }
  },
  created() {
    if (this.$route.query.type && !this.$route.query.blogId){
        if (this.$route.query.type=='1' || this.$route.query.type=='2'){
          this.getDetailsByType()
        }
      }
      if (this.$route.query.blogId && !this.$route.query.type){
        this.getBlogDetails()
      }
  },
  updated(){
    this.getTocbot()
  },
  beforeDestroy() {
    if (this.Timeout) {
      clearTimeout(this.Timeout)
    }
  },
  destroyed() {
    tocbot.destroy();
  },
  methods:{
    addLimit(){
      this.limit+=10
      this.getComment()
    },
     getTocbot(){
      tocbot.init({
        tocSelector: ".js-toc", //要把目录添加元素位置，支持选择器
        contentSelector: "#js-toc-content", //获取html的元素
        headingSelector: "h1, h2, h3", //要显示的id的目录
        hasInnerContainers: true,
        onClick: function(e) {
          e.preventDefault();
        },
        headingsOffset: 60,
        scrollSmoothOffset: -60
      })
    },
    getQQInfo(){
      if (this.qqNumber==null || this.qqNumber==''){
        this.resetName()
        return
      }
      if (!/^\d{6,12}$/.test(this.qqNumber)){
        this.$notify({
          title: 'qq账号格式不正确',
          type: 'warning'
        });
        this.resetName()
        return
      }
      this.axios({
        method: 'get',
        url: api.getQQInfo+this.qqNumber,
      }).then(res => {
        this.$notify({
          title: '获取成功',
          type: 'success'
        });
        let rep = res.data
        this.commentData.nickname=rep.data.nickname
        this.commentData.email=rep.data.email
        this.commentData.avatar=rep.data.avatar
      })
    },
    addComment(){
      if (this.commentData.content==null || this.commentData.content==""){
        this.$notify({
          title: '评论内容不可为空',
        });
         return
      }
      if (this.commentData.nickname==null || this.commentData.nickname==""){
        this.$notify({
          title: '昵称不可为空',
        });
        return
      }
      if (this.commentData.email==null || this.commentData.email==""){
        this.$notify({
          title: '邮箱不可为空',
        });
        return
      }
      if (!/\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/.test(this.commentData.email)){
        this.$notify({
          title: '邮箱格式不正确',
        });
        return
      }
      if (!/^.{1,10}$/.test(this.commentData.nickname)){
        this.$notify({
          title: '昵称仅支持1-10个字符',
        });
        return
      }
      if (!/^.{1,666}$/.test(this.commentData.content)){
        this.$notify({
          title: '最高只能评论666个字符',
        });
        return
      }
      this.axios({
        method: 'post',
        url: api.addComment,
        data:this.commentData
      }).then(res => {
        this.$notify({
          title: '评论成功',
          type: 'success'
         });
        this.emailAlert()
        this.getComment()
        this.resetContent()
      })
    },
     emailAlert(){
      this.axios({
        method: 'post',
        url: api.emailAlert,
        data:this.commentData
      }).then(res => {})
    },
    getComment(){
      this.axios({
        method: 'get',
        url: api.getComment,
        params: {
          blogId:this.blog.id,
          pageNum:this.pageNum,
          limit:this.limit
        }
      }).then(res => {
        let rep = res.data
         this.commentList=rep.data.list
         this.total=rep.data.total
      })
    },
      getDetailsByType(){
      this.axios({
        method: 'get',
        url: api.getDetailsByType,
        params: {
          type:this.$route.query.type
        }
      }).then(res => {
        let rep = res.data
        this.blog=rep.data
        this.commentData.blogId= this.blog.id
        if (this.blog.commentabled){
          this.getComment()
        }
      })
    },
      getBlogDetails(){
      this.axios({
        method: 'get',
        url: api.getBlogDetails,
        params: {
          blogId:this.$route.query.blogId
        }
      }).then(res => {
        let rep = res.data
        this.blog=rep.data
        this.commentData.blogId= this.blog.id
        if (this.blog.commentabled){
          this.getComment()
        }
      })
    },
    reply(id,nickname){
      this.commentData.extendsCommentid=id
      this.commentData.extendsName=nickname
      this.$refs.textarea.scrollIntoView({ behavior: 'smooth' })
       this.Timeout= setTimeout(()=>{
        this.$refs.textarea.focus()
      },1000)
    },
    cancelReply(){
      this.commentData.extendsCommentid=null
      this.commentData.extendsName=null
    },
    resetContent(){
      this.commentData.content=null
      this.commentData.extendsCommentid=null
      this.commentData.extendsName=null
    },
    resetName(){
      this.commentData.nickname=null
      this.commentData.email=null
      this.commentData.avatar=api.URL+'/avatar/touristAvatar.png'
      this.qqNumber=null
    }
  },
  watch: {
    $route () {
      if (this.$route.query.type && !this.$route.query.blogId){
        if (this.$route.query.type=='1' || this.$route.query.type=='2'){
          this.getDetailsByType()
        }
      }
      if (this.$route.query.blogId && !this.$route.query.type){
        this.getBlogDetails()
      }
    }
  },
}
</script>

<style lang="scss" scoped>
</style>
