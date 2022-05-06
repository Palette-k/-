<template>
  <div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          confirm-button-text="确定"
          cancel-button-text="我再想想"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      ><info-filled></info-filled>
        <template #reference>
          <el-button type="danger">批量删除</el-button>
        </template>
      </el-popconfirm>
      <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="id" width="80"></el-table-column>
      <el-table-column prop="img" label="轮播图" width="700">
        <!--    显示图片   -->
        <template #default="scope">
          <el-image
              style="width: 680px; height: 280px"
              :src="scope.row.img"

              fit="cover"
          />

        </template>
      </el-table-column>
      <el-table-column prop="showStatus" label="是否展示" width="180" align="center">
        <template #default="scope">
        <el-switch
            v-model="scope.row.showStatus"
            :active-value="1"
            :inactive-value="0"
            class="ml-2"
            active-color="#13ce66"
            inactive-color="#ff4949"
            @click="editShowStatus(scope.row.id,scope.row.showStatus )"
        />
        </template>
      </el-table-column>

      <el-table-column label="操作"   align="center">

        <template v-slot="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              confirm-button-text="确定"
              cancel-button-text="我再想想"
              icon-color="red"
              title="您确定删除这些数据吗？"
              @confirm="del(scope.row.id)"
          ><info-filled></info-filled>
            <template #reference>
              <el-button type="danger">删除</el-button>
            </template>
          </el-popconfirm>

        </template>

      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="轮播图" v-model="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="轮播图">
          <!--          <el-input  autocomplete="off"></el-input>-->
          <single-upload v-model="form.img"></single-upload>
        </el-form-item>

      </el-form>
      <!-- eslint-disable-next-line vue/no-deprecated-slot-attribute -->
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import request from "@/utils/request";
import singleUpload from "@/components/upload/singleUpload";
export default {
  name: "carousel",
  components: {singleUpload},

  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      dialogType:'',
      form: {
        id:'',
        img:'',
        userId:'',
        postId:'',
        showStatus:'',
        type:''
      },
      showStatus:true,
      dialogFormVisible: false,
      multipleSelection: [],
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: 'headerBg'
    }
  },
  created(){
    this.load()
  },
  methods:{
    submitData() {
      if (this.dialogType == "add") {
        this.save();
      }
      if (this.dialogType == "edit") {
        this.edit();
      }
    },
    load() {
       request.get("/works/carousel/page/showPostImgs?pageCurrent=" + this.pageNum + "&pageSize=" + this.pageSize ).then(res => {
          console.log(res);
          this.tableData = res.records;
          this.total = res.total;
       })
    },
    save() {
      this.form.type = 0
      this.form.showStatus = 1
      this.form.userId = 0
      this.form.postId = 0
      this.form.type = 0
     // console.log(this.form)
       request.post("/works/carousel/addPostImg",this.form).then(res =>{
         console.log(res);
         if (res.code == 200) {
           this.$message.success("保存成功")
           this.dialogFormVisible = false
           this.load()
         } else {
           this.$message.error("保存失败")
         }
      })
    },
    edit(){
      console.log(this.form)

      request.post("/works/carousel/editPostImg",this.form).then(res =>{
        console.log(res);
        if (res.code == 200) {
          this.$message.success("修改成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("修改失败")
        }
      })
    },
    editShowStatus(id,showStatus){
      this.form.id = id
      if(showStatus == true){
        this.form.showStatus = 1
      }else{
        this.form.showStatus = 0
      }
      var {id,showStatus} = this.form;
      console.log({id,showStatus});
      request.post("/works/carousel/editPostImg",{id,showStatus}).then(res =>{
        console.log(res);
        if (res.code == 200) {
          this.$message.success("图片展示状态修改成功")
          this.load()
        } else {
          this.$message.error("图片展示状态修改失败")
        }
      })
    },
    handleAdd() {
      this.dialogType = "add"
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.form.showStatus = 1
      this.form.type = 0
      this.form.userId = 0
      this.form.postId = 0
      this.dialogType = "edit"
      this.dialogFormVisible = true
    },
    del(id) {
      var ids = [id]
      console.log(ids)
      request.post("/works/carousel/deletePostImg",ids)
          .then(res => {
            if (res.code == 200) {
              this.$message.success("图片删除成功")
              this.load()
            } else {
              this.$message.error("图片删除失败")
            }
                });
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
      console.log(ids);
      request.post("/works/carousel/deletePostImg",ids)
          .then(res => {
            if (res.code == 200) {
              this.$message.success("图片删除成功")
              this.load()
            } else {
              this.$message.error("图片删除失败")
            }
          });
    },
    reset() {
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    }
  }


}

</script>

<style scoped>
.headerBg {
  background: #eee!important;
}
</style>