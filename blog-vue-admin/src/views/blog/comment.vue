<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="昵称" prop="nickname">
        <el-input
          v-model="queryParams.nickname"
          placeholder="请输入昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="博客id" prop="blogId">
        <el-select  v-model="queryParams.blogId" placeholder="请选择">
          <el-option
            v-for="(item,index) of blogList"
            :key="index"
            :label="item.title"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="commentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="昵称" align="center" prop="nickname" />
      <el-table-column label="头像" align="center"  >
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="scope.row.avatar"
            fit="fill"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="内容" >
        <template slot-scope="scope">
          <div style="text-overflow: ellipsis;overflow: hidden;white-space: nowrap;width: 250px;text-align: left">
            {{scope.row.content}}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createtime" />
      <el-table-column label="是否为管理员评论" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.administrator" type="success">管理员</el-tag>
          <el-tag v-if="!scope.row.administrator" type="danger">游客</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      style="display: flex;justify-content: right;margin:30px 0;"
      layout="prev, pager, next"
      v-if="total>0"
      :total="total"
      :page-size="queryParams.pageSize"
      @current-change="currentPage"
    >
    </el-pagination>
    <!-- 添加或修改评论对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="昵称">
          <el-input  v-model="form.nickname"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input  v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="头像链接">
          <el-input  v-model="form.avatar"></el-input>
        </el-form-item>
        <el-form-item label="评论内容">
        <el-input type="textarea"  v-model="form.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getBlogListIdAndName} from '@/api/blog'
import  {queryComment,editComment,removeComment} from "@/api/comment";
export default {
  name: "Comment",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 评论表格数据
      commentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nickname: null,
        blogId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      blogList:[]
    };
  },
  created() {
    if (this.$route.query.id){
      this.queryParams.blogId=this.$route.query.id
    }
    this.getList()
    this.getBlogListIdAndName()
  },
  methods: {
    getBlogListIdAndName(){
      getBlogListIdAndName().then(rep=>{
        this.blogList=rep.data
      })
    },
    /** 查询评论列表 */
    getList() {
      this.loading = true;
      queryComment(this.queryParams).then(response => {
        this.commentList = response.data.list;
        this.total = response.data.total
        this.loading = false;
      });
    },
    currentPage(page){
      this.queryParams.pageNum=page
      this.getList()
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        nickname: null,
        email: null,
        content: null,
        avatar: null,
        createtime: null,
        blogId: null,
        extendsCommentid: null,
        administrator: null
      }
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
         this.queryParams= {
           pageNum: 1,
           pageSize: 10,
           nickname: null,
           blogId: null
         }
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
        this.reset();
      this.open = true;
      console.log(row)
        this.form = row;

        this.title = "修改评论";
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            editComment(this.form).then(response => {
              this.$notify({
                message: '修改成功',
                type: 'success'
              });
              this.open = false;
              this.getList();
            });
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除？').then(function() {
        return removeComment(ids);
      }).then(() => {
        this.getList();
        this.$notify({
          message: '删除成功',
          type: 'success'
        });
      }).catch(() => {});
    }
  }
};
</script>
