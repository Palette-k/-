<template>
  <div>
    <!--    <el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽"></el-switch>
        <el-button v-if="draggable" @click="batchSave">批量保存</el-button>-->
    <el-button type="danger" @click="batchDelete">批量删除</el-button>
    <el-tree
        :data="menus"
        :props="defaultProps"
        :expand-on-click-node="false"
        show-checkbox
        node-key="id"
        :default-expanded-keys="expandedKey"
        :draggable="draggable"
        ref="menuTree"
    >
      <template #default="{ node, data }">
      <span class="custom-tree-node" >
        <span>{{ node.label }}</span>
        <span>
          <el-button
              v-if="node.level < 2"
              type="text"
              size="mini"
              @click="() => append(data)"
          >Append</el-button>
          <el-button type="text" size="mini" @click="edit(data)">edit</el-button>
          <el-button
              v-if="node.childNodes.length==0"
              type="text"
              size="mini"
              @click="() => remove(node, data)"
          >Delete</el-button>
        </span>
      </span>
      </template>
    </el-tree>


    <el-dialog
        :title="title"
        v-model="dialogVisible"
        width="30%"
        :close-on-click-modal="false"
    >
      <el-form :model="workscategory">
        <el-form-item label="作品形式名称">
          <el-input v-model="workscategory.catelogName" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import request from '../../../../新建文件夹/CommentSystem-dev/vue/src/utils/request'
export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},
  data() {
    return {
      pCid: [],
      draggable: false,
      updateNodes: [],
      maxLevel: 0,
      title: "",
      dialogType: "", //edit,add
      workscategory: {
        catelogName: "",
        tagcatelogId: 0,
        //level: 0,
        showStatus: 1,
        id: null
      },
      dialogVisible: false,
      menus: [],
      expandedKey: [],
      defaultProps: {
        children: "children",
        label: "catelogName"
      }
    };
  },

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    getMenus() {
      request.get("/works/workscategory/showCateTree").then(res =>{
        console.log(res);
        this.menus = res.data;
      })
    },
    batchDelete() {
      let catIds = [];
      let checkedNodes = this.$refs.menuTree.getCheckedNodes();
      console.log("被选中的元素", checkedNodes);
      for (let i = 0; i < checkedNodes.length; i++) {
        catIds.push(checkedNodes[i].id);
      }
      this.$confirm(`是否批量删除【${catIds}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
          .then(() => {
            console.log("即将要被删除的元素", catIds);
            request.post("/works/workscategory/delete",catIds).then(({ data }) => {
              this.$message({
                message: "菜单批量删除成功",
                type: "success"
              });
              this.getMenus();

            });
          })
          .catch(() => {});
    },

    edit(data) {
      console.log("要修改的数据", data);
      this.dialogType = "edit";
      this.title = "修改分类";
      this.dialogVisible = true;

      //发送请求获取当前节点最新的数据
      request.get("/works/workscategory/getInfo/" + data.id).then(({ data }) => {
        //请求成功
        console.log("要回显的数据", data);
        this.workscategory.catelogName = data.catelogName;
        this.workscategory.id = data.id;
        this.workscategory.tagcatelogId = data.tagcatelogId;
       // this.workscategory.level = data.level;
        this.workscategory.showStatus = data.showStatus;

      });
    },
    append(data) {
      console.log("append", data);
      this.dialogType = "add";
      this.title = "添加分类";
      this.dialogVisible = true;
      this.workscategory.tagcatelogId = data.id;
     // this.workscategory.level = data.level * 1 + 1;

      this.workscategory.id = null;
      this.workscategory.catelogName = "";
      this.workscategory.showStatus = 1;
    },

    submitData() {
      if (this.dialogType == "add") {
        this.addCategory();
      }
      if (this.dialogType == "edit") {
        this.editCategory();
      }
    },
    //修改三级分类数据
    editCategory() {
      var { id, catelogName } = this.workscategory;
      request.post("/works/workscategory/update",{ id, catelogName }).then(({ data }) => {
        this.$message({
          message: "菜单修改成功",
          type: "success"
        });
        //关闭对话框
        this.dialogVisible = false;
        //刷新出新的菜单
        this.getMenus();
        //设置需要默认展开的菜单
        this.expandedKey = [this.workscategory.tagcatelogId];
      });
    },
    //添加三级分类
    addCategory() {
      console.log("提交的三级分类数据", this.workscategory);
      request.post("/works/workscategory/save",this.workscategory).then(({ data }) => {
        this.$message({
          message: "菜单保存成功",
          type: "success"
        });
        //关闭对话框
        this.dialogVisible = false;
        //刷新出新的菜单
        this.getMenus();
        //设置需要默认展开的菜单
        this.expandedKey = [this.workscategory.tagcatelogId];
      });
    },

    remove(node, data) {
      var ids = [data.id];
      this.$confirm(`是否删除【${data.catelogName}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
          .then(() => {
            request.post("/works/workscategory/delete",ids)
                .then(({ data }) => {
                  this.$message({
                    message: "菜单删除成功",
                    type: "success"
                  });
                  //刷新出新的菜单
                  this.getMenus();
                  //设置需要默认展开的菜单
                  this.expandedKey = [node.parent.data.id];
                });
          })
          .catch(() => {});

      console.log("remove", node, data);
    }
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.getMenus();
  },
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {} //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>