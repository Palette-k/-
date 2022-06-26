<template>
 <div>


   <div style="margin: 10px 0">
     <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-search" v-model="commentator"></el-input>
     <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
     <el-button type="warning" @click="reset">重置</el-button>
   </div>



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
     <el-table-column prop="commentator" label="用户名" width="140"></el-table-column>
     <el-table-column prop="type" label="评论类型" width="120"></el-table-column>
     <el-table-column label="评论时间">
       <template v-slot="scope">
         {{timestampToTime(scope.row.gmtCreate)}}
       </template>
     </el-table-column>
     <el-table-column prop="content" label="评论内容"></el-table-column>
     <el-table-column label="操作"  width="200" align="center">
       <template v-slot="scope">
         <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
         <el-popconfirm
             confirm-button-text="确定"
             cancel-button-text="我再想想"
             icon-color="red"
             title="您确定删除这些数据吗？"
             @confirm="del(scope.row)"
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

   <el-dialog title="评论信息" v-model="dialogFormVisible" width="30%" >
     <el-form label-width="80px" size="small">
       <el-form-item label="用户名">
         <el-input v-model="form.commentator" autocomplete="off"></el-input>
       </el-form-item>
       <el-form-item label="评论类型">
         <el-input v-model="form.type" autocomplete="off"></el-input>
       </el-form-item>
       <el-form-item label="评论时间">
         <el-input v-model="form.gmtCreate" autocomplete="off"></el-input>
       </el-form-item>
       <el-form-item label="评论内容">
         <el-input v-model="form.content" autocomplete="off"></el-input>
       </el-form-item>
     </el-form>
     <!-- eslint-disable-next-line vue/no-deprecated-slot-attribute -->
     <div slot="footer" class="dialog-footer">
       <el-button @click="dialogFormVisible = false">取 消</el-button>
       <el-button type="primary" @click="save">确 定</el-button>
     </div>
   </el-dialog>


 </div>
</template>

<script>
import request from "@/utils/request";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Comment",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      form: {},
      id:"",
      commentator:"",
      type:"",
      gmtCreate:"",
      content:"",
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
    timestampToTime(timestamp) {
      var date = new Date(timestamp);
      var Y = date.getFullYear() + '-';
      var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
      var D = (date.getDate() < 10 ? '0'+date.getDate() : date.getDate()) + ' ';
      var h = (date.getHours() < 10 ? '0'+date.getHours() : date.getHours()) + ':';
      var m = (date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
      var s = (date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds());

      let strDate = Y+M+D+h+m+s;
      return strDate;
    },
    load() {
      request.get("/admin/comment/page?pageNum="+this.pageNum+"&pageSize=" + this.pageSize )
          .then(res => {
        console. log(res)
        console.log(res.records);
        this.tableData = res.records
        this.total = res.total
      })
    },
    save() {
      request.post("/admin/comment/", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    del(row) {
      request.delete("/works/auth/comment/del/" + row.id).then(res => {
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
      request.post("/admin/comment/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    reset() {
      this.commentator = ""
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

<style>
.headerBg {
  background: #eee!important;
}
</style>