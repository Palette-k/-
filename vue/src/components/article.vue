<template>
  <div>
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <el-form-item style="margin-bottom: 40px" prop="title">
        <MdInput v-model="postForm.title" :maxlength="30" required>
          标题
        </MdInput>
      </el-form-item>
      <el-form-item style="margin-bottom: 40px;" label-width="70px" label="Summary:">
        <el-input v-model="postForm.content_short" :rows="1" type="textarea" class="article-textarea" autosize placeholder="Please enter the content" />
        <span v-show="contentShortLength" class="word-counter">{{ contentShortLength }}words</span>
      </el-form-item>
      <el-form-item prop="content" >
        <tinymce ref="editor" v-model="postForm.content" ></tinymce>
      </el-form-item>
      <el-button v-loading="loding" type="success" @click="submitForm">
        发布
      </el-button>
    </el-form>

  </div>
</template>

<script>
import Tinymce from "@/components/Tinymce"
import MdInput from "@/components/MDinput";

const defaultForm = {
  title:'',
  content:'',
  content_short:'',
  image_url: '',
  id: '',
  type: 1,
  score:'',
}
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
      postForm: Object.assign({},defaultForm),
      rules: {
        /*image_uri: [{ validator: validateRequire }],*/
        title: [{ validator: validateRequire }],
        content: [{ validator: validateRequire }],
       /* source_uri: [{ validator: validateSourceUri, trigger: 'blur' }]*/
      },
    }
  },
  computed: {
    contentShortLength() {
      return this.postForm.content_short.length
    }
  },
  methods:{
    submitForm() {
      console.log(this.postForm)
    }
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


</style>