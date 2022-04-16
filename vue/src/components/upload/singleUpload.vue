<template>
  <div>
    <el-upload
        action="http://commentsystem-community.oss-cn-shenzhen.aliyuncs.com"
        :data="dataObj"
        list-type="picture"
        :multiple="false" :show-file-list="showFileList"
        :file-list="fileList"
        :before-upload="beforeUpload"
        :on-remove="handleRemove"
        :on-success="handleUploadSuccess"
        :on-preview="handlePreview">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过10MB</div>
    </el-upload>
    <el-dialog v-model="dialogVisible">
      <img width="100%" :src="fileList[1].url" alt="">
    </el-dialog>
  </div>
</template>
<script>
import {policy} from './policy'
import { getUUID } from '@/utils'

export default {
  name: 'singleUpload',
  emits: ['update:modelValue'],
  props: {
    modelValue: String
  },
  computed: {
    imageUrl() {
      return this.modelValue;
    },
    imageName() {
      if (this.modelValue != null && this.modelValue !== '') {
        return this.modelValue.substr(this.modelValue.lastIndexOf("/") + 1);
      } else {
        return null;
      }
    },
    fileList() {
      return [{
        name: this.imageName,
        url: this.imageUrl
      }]
    },
    showFileList: {
      get: function () {
        return this.modelValue !== null && this.modelValue !== ''&& this.modelValue!==undefined;
      },
      set: function (newValue) {
      }
    }
  },
  data() {
    return {
      dataObj: {
        policy: '',
        signature: '',
        key: '',
        ossaccessKeyId: '',
        dir: '',
        host: '',
        // callback:'',
      },
      dialogVisible: false
    };
  },
  methods: {
    emitInput(val) {
      this.$emit('update:modelValue', val)
    },
    handleRemove(file, fileList) {
      this.emitInput('');
    },
    handlePreview(file) {
      this.dialogVisible = true;
    },
    beforeUpload(file) {
      let _self = this;
      return new Promise((resolve, reject) => {
        policy().then(response => {
          console.log("响应的数据",response);
          _self.dataObj.policy = response.data.policy;
          _self.dataObj.signature = response.data.signature;
          _self.dataObj.ossaccessKeyId = response.data.accessid;
          _self.dataObj.key = response.data.dir +getUUID()+'_${filename}';
          _self.dataObj.dir = response.data.dir;
          _self.dataObj.host = response.data.host;
          console.log("响应的数据222。。。",_self.dataObj);
          resolve(true)
        }).catch(err => {
          reject(false)
        })
      })
    },
    handleUploadSuccess(res, file) {
      console.log("上传成功...")
      this.showFileList = true;
      this.fileList.pop();
      this.fileList.push({name: file.name, url: this.dataObj.host + '/' + this.dataObj.key.replace("${filename}",file.name) });
      console.log(this.fileList);
      console.log(this.fileList[1].url)
      this.emitInput(this.fileList[1].url);
    }
  }
}
</script>
<style>

</style>
