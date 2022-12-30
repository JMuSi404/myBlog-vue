<template>
  <div class="app-container" style="padding-top: 33px">
    <el-form  :model="form" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <div style="display: flex;">
     <div style="flex: 1.2">
       <el-form-item   label="文章名称" prop="title">
         <el-input  placeholder="请输入文章名称" v-model="form.title"></el-input>
       </el-form-item>
       <el-form-item   label="文章简介" prop="description">
         <el-input  placeholder="请输入文章简介" :rows="4" type="textarea" v-model="form.description"></el-input>
       </el-form-item>
     </div>
        <div style="flex: 0.8;">
          <el-form-item label="标题图链接" icon="el-icon-picture-outline-round"  prop="firstPicture">
            <el-popover
              placement="top-start"
              v-model="visible"
              width="200"
              trigger="hover">
              <p>前往壁纸网站 wallhaven</p>
              <div style="text-align: right; margin: 0">
                <el-button size="mini" type="text" @click="visible = false">取消</el-button>
                <el-button type="primary" size="mini" @click="toWallhaven">确定</el-button>
              </div>
              <i class="el-icon-warning-outline" style="font-size: 18px;position: relative;right: 10px;top: 2px" slot="reference"></i>
            </el-popover>
            <el-input placeholder="请输入链接" style="width: 90%" v-model="form.firstPicture"></el-input>
          </el-form-item>
          <el-form-item    label="标签分类"  prop="tags">
            <el-select style="width: 94%"  v-model="form.tags" multiple placeholder="请选择">
              <el-option
                v-for="(item,index) of options"
                :key="index"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </div>
      </div>
      <div style="display: flex;width: 100%">
        <el-form-item style="margin-left: 10px" required label="是否可评论">
          <el-switch
            v-model="form.commentabled">
          </el-switch>
        </el-form-item>

        <el-form-item style="margin-left: 10px" required label="是否推荐">
          <el-switch
            v-model="form.recommendned">
          </el-switch>
        </el-form-item>
        <el-form-item  style="margin-left: 10px" required label="是否私密">
          <el-radio-group v-model="form.published" size="mini">
            <el-radio :label="true" border>公开</el-radio>
            <el-radio :label="false" border >私密</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item  required label="创作类型">
          <el-radio-group v-model="form.flag" size="mini">
            <el-radio-button label="原创" ></el-radio-button>
            <el-radio-button label="转载" ></el-radio-button>
            <el-radio-button label="引用" ></el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item  required label="文章类型">
          <el-radio-group v-model="form.type" size="mini">
            <el-radio-button  :label="0" >普通文章</el-radio-button>
            <el-radio-button  :label="1" >友情链接</el-radio-button>
            <el-radio-button  :label="2" >关于我</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </div>
        <div style="padding: 0 2%">
          <mavon-editor
            style="height: 666px"
            ref="md"
            v-model="form.content"
          >
          </mavon-editor>
          <div style="margin: 50px 0;display: flex;justify-content: right;">
            <el-button type="primary" @click="submitForm('ruleForm')">{{$route.query.id?'保存修改':'创建文章'}}</el-button>
            <el-button  @click="$router.back()">返回</el-button>
          </div>
        </div>
    </el-form>
  </div>
</template>

<script>
import {getTags} from "@/api/tags";
import {getBlogDetails,addBlog,editBlog} from "@/api/blog";
export default {
  data() {
    return {
      visible:false,
      form:{
        title:null,
        description:null,
        content:null,
        firstPicture:null,
        tags:null,
        flag:'原创',
        published:true,
        recommendned:false,
        commentabled:false,
        id:null,
        type:0
      },
      rules: {
        title: [
          { required: true, message: '请输入文章标题', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请填写文章简介', trigger: 'blur' },
          { min: 1, max: 150, message: '长度最高 150 个字符', trigger: 'blur' }
        ],
        tags: [
          { required: true, message: '请选择标签', trigger: 'blur' }
        ],
        firstPicture:[
          { required: true, message: '请选择首图链接', trigger: 'blur' }
        ]
      },
      options: [],
    }
  },
 created() {
    if (this.$route.query.id){
      this.$route.meta.title="编辑文章"
      this.getTags()
       this.getBlogDetails()
    }else {
      this.$route.meta.title="新增文章"
      this.getTags()
    }
 },
  watch: {},
  methods: {
    toWallhaven(){
      window.open("https://wallhaven.cc");
    },
    getTags(){
      getTags().then(rep=>{
        this.options=rep.data.list
      })
    },
    getBlogDetails(){
      getBlogDetails(this.$route.query.id).then(rep=>{
          let blog=rep.data.blog
          let tags=rep.data.tags
        tags=tags.map(item=>{
          return item.id
        })
        this.form={
             title:blog.title,
             description:blog.description,
             content:blog.content,
             firstPicture:blog.firstPicture,
             tags:tags,
             flag:blog.flag,
             published:blog.published,
             recommendned:blog.recommendned,
             commentabled:blog.commentabled,
             id:blog.id,
             type:blog.type
        }
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.form.id==null){
            this.form.tagIds=this.form.tags.join(',')
            addBlog(this.form).then(rep=>{
              this.$notify({
                message: '添加文章成功',
                type: 'success'
              })
              this.$router.push("/blog/list")
            })
          }else {
            this.form.tagIds=this.form.tags.join(',')
            editBlog(this.form).then(rep=>{
              this.$notify({
                message: '修改文章成功',
                type: 'success'
              })
              this.$router.push("/blog/list")
            })
          }
        } else {
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
<style scoped lang="scss">
</style>
