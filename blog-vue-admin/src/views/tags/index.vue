<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
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

    <el-table v-loading="loading" :data="tagList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="名称" align="center" prop="name" >
        <template slot-scope="scope">
          <el-tag
            type="danger">
            {{ scope.row.name }}
          </el-tag>
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


    <!-- 添加或修改标签对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
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
import  {addTags,getTagsQuery,removeTags,editTags} from "@/api/tags";
export default {
  name: "Tag",
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
      // 总条数
      total: 0,
      // 标签表格数据
      tagList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    reset() {
      this.form = {
        id: null,
        name: null,
        count: null
      }
    },
    currentPage(page){
      this.form.pageNum=page
      this.getList()
    },
    /** 查询标签列表 */
    getList() {
      this.loading = true;
      getTagsQuery(this.queryParams).then(response => {
        this.tagList = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true;
      this.title = "添加标签";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      const name =row.name
        this.form.id=id;
        this.form.name=name;
        this.open = true;
        this.title = "修改标签";
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            editTags(this.form).then(response => {
              this.$notify({
                message: '修改成功',
                type: 'success'
              });
              this.open = false;
              this.getList();
            })
          } else {
            addTags(this.form.name).then(response => {
              this.$notify({
                message: '新增成功',
                type: 'success'
              });
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除?',{
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(function() {
        return removeTags(ids);
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
