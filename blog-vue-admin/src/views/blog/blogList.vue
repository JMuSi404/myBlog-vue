<template>
  <div class="app-container">
<!--    条件搜索-->
    <el-form :inline="true"  ref="ruleForm"  :model="form" class="demo-form-inline">
      <el-form-item label="文章名称">
        <el-input style="width: 200px" size="small"  v-model="form.title" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="标签名">
        <el-select style="width: 250px"  size="small" v-model="form.tagsIds" multiple placeholder="请选择">
          <el-option
            v-for="(item,index) of options"
            :key="index"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="状态">
        <el-select   style="width: 100px"   size="small" v-model="form.type" placeholder="请选择">
          <el-option
            v-for="(item,index) of types"
            :key="index"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button  size="small" type="primary" @click="onSubmit">查询</el-button>
        <el-button  size="small" @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
    <div  style="margin: 10px 0">
      <el-button icon="el-icon-plus" @click="toBlogAdd" type="primary" size="small">添加文章</el-button>
      <el-button icon="el-icon-delete" @click="blogDeletes" type="danger" size="small">批量删除</el-button>
    </div>
<!--  文章列表-->
    <el-table
      border
      :data="tableData"
      style="width: 99%"
      max-height="666"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        fixed
        align="center"
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        prop="title"
        align="center"
        label="文章名称"
        width="233">
      </el-table-column>
      <el-table-column
        label="文章封面"
        align="center"
        width="250">
        <template slot-scope="scope">
          <el-image
            :src="scope.row.firstPicture">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="文章类型"
        align="center"
        width="100">
        <template slot-scope="scope">
          <el-tag
            v-if="scope.row.type==0"
            size="small"
            effect="dark">
             普通文章
          </el-tag>
          <el-tag
            v-if="scope.row.type==1"
            size="small"
            effect="dark">
            友联页
          </el-tag>
          <el-tag
            v-if="scope.row.type==2"
            size="small"
            effect="dark">
             关于我页
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="创作类型"
        align="center"
        width="100">
        <template slot-scope="scope">
          <el-tag
            v-if="scope.row.flag=='原创'"
            type="success"
            size="small"
            effect="dark">
            {{scope.row.flag}}
          </el-tag>
          <el-tag
            v-if="scope.row.flag=='转载'"
            type="warning"
            size="small"
            effect="dark">
            {{scope.row.flag}}
          </el-tag>
          <el-tag
            v-if="scope.row.flag=='引用'"
            type="danger"
            size="small"
            effect="dark">
            {{scope.row.flag}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="标签"
        align="center"
        width="200">
        <template slot-scope="scope">
         <el-tag   size="mini" style="margin-right: 5px" :key="index"  v-for="(item,index) in  scope.row.tagsName">{{item}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="状态"
        align="center"
        width="200">
        <template slot-scope="scope">
          <el-radio-group @change="updatePublished(scope.row)"  v-model="scope.row.published"  size="small">
            <el-radio-button :label="true">公开</el-radio-button>
            <el-radio-button :label="false">隐藏</el-radio-button>
          </el-radio-group>
        </template>
      </el-table-column>
      <el-table-column
        prop="userName"
        label="发布者"
        align="center"
        width="100">
      </el-table-column>
      <el-table-column
        prop="views"
        label="浏览量"
        align="center"
        width="100">
      </el-table-column>
      <el-table-column
        prop="commentCount"
        label="评论数"
        align="center"
        width="100">
      </el-table-column>

      <el-table-column
        prop="createtime"
        label="创建时间"
        align="center"
        width="150">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        align="center"
        width="100">
        <template slot-scope="scope">
          <el-button   @click="blogDelete(scope.row.id)" type="text" size="small">删除</el-button>
          <el-button   @click="toBlogEdit(scope.row.id)" type="text" size="small">编辑</el-button>
          <el-button   @click="commentEdit(scope.row.id)" type="text" size="small">评论</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {blogList,deleteBlogs,updatePublished} from "@/api/blog";
import {getTags} from "@/api/tags";
export default {

  data() {
    return {
      checkedAll:0,
      form: {
        title: null,
        tagsIds: null,
        type:null
      },
      types:[
        {
          value: 0,
          label: '隐藏'
        },
        {
          value: 1,
          label: '公开'
        }
      ],
      options: [],
      tableData: [],
      ids:[]
    }
  },
  watch: {

  },
  created() {
    this.getTags()
    this.getBlogList()
  },
  methods: {
    commentEdit(id){
       this.$router.push({
         path:'/blog/comment',
         query:{
           id:id
         }
       })
    },
    getBlogList(){
      blogList(this.form).then(rep=>{
        this.tableData=rep.data
      })
    },
    onSubmit(){
      this.getBlogList()
    },
    resetForm(){
      this.form={
        title:null,
        tags:null,
        type:null
      }
    },
    handleSelectionChange(val) {
      this.ids= val.map(item=>{
        return item.id
      })
    },
    toBlogAdd(){
      this.$router.push('/blog/add')
    },
    toBlogEdit(id){
      this.$router.push({
        path:'/blog/add',
        query:{
          id:id
        }
      })
    },
    blogDelete(id){
      let ids=[]
      ids.push(id)
      this.$confirm('此操作将删除文章, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteBlogs(ids.join(',')).then(rep=>{
          if (rep.code==200){
            this.$notify({
              message: '删除文章成功',
              type: 'success'
            });
            this.getBlogList()
          }
        })
      })
    },
    blogDeletes() {
      if (this.ids.length == 0) {
        this.$notify({
          message: '至少选择一篇文章',
          type: 'error'
        });
        return
      }
      this.$confirm('此操作将删除文章, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
      deleteBlogs(this.ids.join(',')).then(rep=>{
        if (rep.code==200){
          this.$notify({
            message: '删除文章成功',
            type: 'success'
          });
          this.getBlogList()
        }
      })
      })
    },
    getTags(){
      getTags().then(rep=>{
        this.options=rep.data.list
      })
    },
    updatePublished(item){
      updatePublished({id:item.id,published:item.published}).then(rep=>{
        if (rep.code==200){
          this.$notify({
            message: '修改状态成功',
            type: 'success'
          });
        }
      })
    }
  }
}
</script>

