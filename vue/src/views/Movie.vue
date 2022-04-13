<template>
  <div>


    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入电影名称" suffix-icon="el-icon-search" v-model="movieName"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>



    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="id" width="80"></el-table-column>
      <el-table-column prop="name" label="名称" width="140"></el-table-column>
      <el-table-column prop="intro" label="简介" width="300"></el-table-column>
      <el-table-column prop="path" label="海报" width="80">
              <!--    显示图片   -->
      </el-table-column>
      <el-table-column prop="country" label="制作国家" width="100"></el-table-column>
      <el-table-column prop="createTime" label="发行时间" width="120"></el-table-column>
      <el-table-column prop="catelogName" label="作品类型" width="120"></el-table-column>
      <el-table-column prop="tagList" label="作品标签" width="120"></el-table-column>
      <el-table-column prop="producerName" label="创作者" width="120"></el-table-column>
      <el-table-column label="操作"   align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
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

    <el-dialog title="电影信息" v-model="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="form.intro" :rows="2" placeholder="请输入简介" type="textarea" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="海报">
<!--          <el-input  autocomplete="off"></el-input>-->
       <single-upload v-model:value="form.path"></single-upload>
        </el-form-item>
        <el-form-item label="制作国家">
          <el-input v-model="form.country" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="发行时间">
          <el-input v-model="form.createTime" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="作品类型">
          <el-dropdown @command="handleCommand1" v-model="form.catelogName">
       <span class="el-dropdown-link">
        {{form.catelogName}}<el-icon class="el-icon--right"><arrow-down /></el-icon>
        </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="影视">影视</el-dropdown-item>
                <el-dropdown-item command="音乐">音乐</el-dropdown-item>
                <el-dropdown-item command="书籍">书籍</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-form-item>

        <el-form-item label="作品形式">
          <el-select placeholder="请选择" v-model="form.worksCate" filterable clearable
                     @change="handleCommand2">
            <el-option
                v-for="item in form.worksCate"
                :label="item"
                :value="item"
                x
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="作品标签">
          <el-tag v-model="form.tagList"
              v-for="tag in form.tagList"
              :key="tag"
              class="mx-1"
              closable
              :type="tag.type"
              @close="handleClose(tag)"
          >
            {{ tag }}
          </el-tag>
        </el-form-item>

        <el-form-item label="创作者">
          <el-input v-model="form.producerName" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import request from "@/utils/request";

import singleUpload from "@/components/upload/singleUpload";
import {ref,reactive} from "vue";
/*
const form = reactive({
  name:'',
  intro:'',
  path:'',
  country:'',
  createTime:'',
  tagList:[],
  producerName:'',
  catelogName:'未选择',
  worksCate:[]
})
*/


export default {
  name: "Movie",
  components: {singleUpload},

  setup() {
  /*  let msgFather = form.path;
    msgFather = ref('') // ref的作用是实现响应式， 如果没有ref则不能实现响应式（引用数据类型用reactive）
    let listenToChildren = (val) => {
      children_msg.value = val // 使用ref包裹的数据，需要通过.value的形式访问他的值
    }
    return {

      listenToChildren,
    }*/

  },

  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      form: {},
     // catelogName:"未选择",
      dialogFormVisible: false,
      multipleSelection: [],
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: 'headerBg'
    }
  },
  mounted(){
    this.load()
  },
  methods:{
    //去除标签
      handleClose(tag) {
        this.form.tagList.splice(this.form.tagList.indexOf(tag), 1)
    },
    //查询出作品类型下的作品分区（标签）
     handleCommand1 (value){
       this.form.catelogName = value;
       request.post("/admin/works/selectTag?tagName=" + this.form.catelogName).then(res =>{
         res = res.data;
        // console.log(res);
         this.form.tagList = res.tagList;
         this.form.worksCate = res.workscateList;
         console.log( this.form.tagList);
         console.log( this.form.worksCate);
       });
    },
    //查询出作品类型下的作品形式
    handleCommand2 (value){
        console.log("进入选择作品形式方法");
      this.form.catelogName = value;
      request.post("/admin/works/selectWorksCate?WorkscateName=" + this.form.worksCate).then(res =>{
       // res = res.data;
         console.log(res);

      });
    },
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
      request.get("/admin/movie/page?pageNum="+this.pageNum+"&pageSize=" + this.pageSize )
          .then(res => {
       // console. log(res)
        console.log(res.data);
        res = res.data
         this.tableData = res.records;
        console.log(res.records);
         this.total = res.total;
      })
    },
    save() {

      request.post("/admin/works/insert", this.form).then(res => {
        if (res.code === 200) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd() {
      console.log("进入新增方法");
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    del(id) {
      request.delete("admin/movie/" + id).then(res => {
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
      request.post("admin/movie/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    reset() {
      this.movieName = ""
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
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>