<template>
  <div class="app-container" style="padding-top: 33px">
    <el-row :gutter="10" class="mb8" style="display: flex;align-items: center">
      <el-col :span="1.5" >
        <el-checkbox :indeterminate="isIndeterminate"  @change="selectAll"  v-model="checkedAll" :true-label="1" :false-label="0">全选</el-checkbox>
      </el-col>
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
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>
    <div style="display: flex;flex-wrap: wrap;margin: 30px 0;justify-content: center">
      <div   v-for="(item,index)  of imgList" style="position: relative;margin: 5px;">
        <el-image
          style="width: 350px;height: 200px"
          :src="item.url">
        </el-image>
        <el-checkbox @change="radioSelect(index)"  style="position: absolute;top: 5%;left: 5%" v-model="item.checkbox" :true-label="1" :false-label="0"></el-checkbox>
      </div>
    </div>
    <el-pagination
      style="display: flex;justify-content: right;margin:30px 0;"
      layout="prev, pager, next"
      v-if="total>0"
      :total="total"
      :page-size="params.pageSize"
      @current-change="currentPage"
    ></el-pagination>
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <div style="display: flex;justify-content: center">
        <el-upload
          class="upload-demo"
          drag
          :action="url+uploadImg"
          multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">退 出</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import request from '@/utils/request'
import {getImage,deleteImage} from "@/api/img";
export  default {
  name:'imgs',
  data(){
    return{
      url:request.URL,
      uploadImg:'/admin/imageUpload',
      imgList:[],
      ids:[],
      params:{
        pageNum:1,
        limit:12
      },
      isIndeterminate:false,
      checkedAll:0,
      total:null,
      title:'',
      open:false,
      multiple:true,
    }
  },
  created(){
      this.getImage()
  },
  methods:{
    // 取消按钮
    cancel() {
      this.open = false;
    },
    currentPage(page){
      this.params.pageNum=page
      this.getImage()
    },
    getImage(){
      getImage(this.params).then(rep=>{
        this.imgList=rep.data.list
        this.imgList=this.imgList.map(item=>{
          item.checkbox=0
          return item
        })
        this.total=rep.data.total
      })
    },
    handleSelectionChange(val) {
      this.ids = val.map(item => item.id)
      this.multiple = !val.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.open = true;
      this.title = "添加照片";
    },
    /** 删除按钮操作 */
    handleDelete() {
      const ids = this.ids;
      this.$confirm('是否确认删除?',{
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(function() {
        return deleteImage(ids);
      }).then(() => {
        this.getImage()
        this.checkedAll=0
        this.isIndeterminate=false
        this.$notify({
          message: '删除成功',
          type: 'success'
        });
      }).catch(() => {});
    },
    /** 单选按钮操作 */
    radioSelect(index){
      this.checkedAll=1
      let num=0
      this.imgList.forEach(item=>{
        if (item.checkbox==0){
           this.checkedAll=0
        }else {
          num++
        }
      })
      this.isIndeterminate = num > 0 && num < this.imgList.length;
      this.selectIds()
    },
    /** 全选按钮操作 */
    selectAll(){
      this.imgList.forEach(item=>{
        item.checkbox=this.checkedAll
      })
      this.isIndeterminate=false
      this.selectIds()
    },
    selectIds(){
      this.ids=[]
      this.multiple=true
      this.imgList.forEach(item=>{
        if (item.checkbox==1){
          this.ids.push(item.id)
          this.multiple=false
        }
      })
    }
  }
}
</script>
<style  lang="scss" scoped>

</style>
