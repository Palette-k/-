<template>
  <div class="article">
    <h2>写影评 <span>|{{this.$route.query.title}}</span> </h2>

    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container" v-if="display">
      <el-form-item style="margin-bottom: 40px" prop="title">
        <MdInput v-model="postForm.title" :maxlength="30" required>
          标题
        </MdInput>
      </el-form-item>
      <el-form-item prop="score" v-if="showRate">
        <span style="color: #5f5f60;vertical-align: middle">给个评价吧：</span>
        <el-rate style="vertical-align: middle"
                 v-model="postForm.score"
                 :colors="colors" :texts="texts" show-text></el-rate>
      </el-form-item>
<!--      <el-form-item style="margin-bottom: 40px;" label-width="70px" label="摘要:">
        <el-input v-model="postForm.content_short" :rows="1" type="textarea" class="article-textarea" autosize placeholder="Please enter the content" />
        <span v-show="contentShortLength" class="word-counter">{{ contentShortLength }}words</span>
      </el-form-item>-->
      <el-form-item prop="content" >
        <tinymce ref="editor" v-model="postForm.content" ></tinymce>
      </el-form-item>
      <el-button v-loading="loading" type="success" @click="submitForm">
        发布
      </el-button>
      <el-button v-loading="loading" type="info" plain @click="backToAbout">
        取消
      </el-button>
    </el-form>

  </div>
</template>

<script>
import Tinymce from "@/components/Tinymce"
import MdInput from "@/components/MDinput";

const defaultForm = {
  id:'',
  title:'',
  content:'',
  type: 1,
  score:'',
}
import {useRoute} from 'vue-router';
import request from "@/utils/request";
export default {
  name: "article",
  components: {MdInput, Tinymce},
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === '') {
        this.$message({
          message: rule.field + '为必传项',
          type: 'error'
        })
        callback(new Error(rule.field + '为必传项'))
      } else {
        callback()
      }
    }
    return {
      display:false,
      showRate:false,
      colors: ['#99A9BF','#F7BA2A','#FF9900'],
      texts: ['极差','较差','一般','较好','极好'],
      /*Object.assign({},defaultForm),*/
      postForm: {
        parentId: this.$route.query.id,
        title:'',
        content:'',
        type: 1,
        score:null,
      },
      rules: {
        /*image_uri: [{ validator: validateRequire }],*/
        title: [{validator: validateRequire}],
        content: [{validator: validateRequire}],
        score:[{validator: validateRequire}],
       /* source_uri: [{ validator: validateSourceUri, trigger: 'blur' }]*/
      },
      loading:false,
    }
  },
  methods:{
    submitForm() {
      // console.log(this.postForm)
      this.$refs.postForm.validate(valid => {
        if (valid) {
          request.post("/works/article/auth/longComment/"+ this.$store.state.id, this.postForm).then(res =>{
            if(res.code ===200) {
              console.log(res)
              this.loading = true
              this.$notify({
                title: '成功',
                message: '发布文章成功',
                type: 'success',
                duration: 2000
            })
              this.postForm.status = 'published'
              this.loading = false
            }else {
              console.log(res)
            }

          })

        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    backToAbout() {
      this.$router.push("/about")
    },
    isLogin() {
      if(!this.$store.state.id) {
        this.display=false
        let count =3
        let timer = setInterval(()=> {
          count--
          this.$message.warning("你没有权限访问该页面！\n"+
              count +"秒后返回上一页！")
          if(count <=0) {
            clearInterval(timer)
            timer =null
            this.$router.push("/about");
          }

        },1000)
      }else {
        this.display =true
      }
    },
    show() {
      let a={}
      a.userId =this.$store.state.id
      a.worksId =this.$route.query.id
      // console.log(a)
      request.post("/works/article/auth/judgeScore",a).then(res => {
        // console.log(res)
        if(!res) {
          this.showRate =true
        }else {
          this.showRate =false
        }

      })
    }
  },
  created() {
    this.isLogin()
  },
  mounted() {
    this.show()
  }
}
</script>

<style lang="less" scoped>
/deep/ .article-textarea {
      padding-right: 40px;
      resize: none;
      border: none;
      border-radius: 0px;
      border-bottom: 1px solid #bfcbd9;
    }
.article {
  width: 70%;
  margin: 0 auto;
  h2 {
    //color: #a0cfff;
    background-color:   #ecf5ff;
    padding: 5px 5px 5px 5px;
  }
  span {
    margin-left: 10px;
    color: #5f5f60;
    font-size: 16px;
  }
}
.material-input__component {
  width: 50%;
}

</style>