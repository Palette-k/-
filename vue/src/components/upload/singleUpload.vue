<template>
  <div>
    <el-upload
      action="http://commentsystem-community.oss-cn-shenzhen.aliyuncs.com"
      :data="dataObj"
      list-type="picture"
      :multiple="false"
      :file-list="fileList" :show-file-list="showFileList"
      :before-upload="beforeUpload"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过10MB</div>
      </el-upload>

    <el-dialog v-model="dialogVisible">
      <el-image width="10%" :src="imageUrl" alt="" />
    </el-dialog>

  </div>
</template>
<script>
   import {policy} from './policy'
   import { getUUID } from '@/utils'
   import { ref , computed} from 'vue'
   import { getCurrentInstance } from "vue"
  // import type { UploadProps, UploadUserFile } from 'element-plus'

   export default {
    name: 'singleUpload',
    props :{
      value:{
        type:String
      }
    },

    setup (props,context){

      let dialogVisible = ref(false);
      const datab = getCurrentInstance();


      let  imageUrl = computed(()=>{
        return props.value;
      })


      let imageName= computed(()=>{
          if (props.value != null && props.value !== '') {
            return props.value.substr(props.value.lastIndexOf("/") + 1);
          } else {
            return null;
          }
        })
      let  fileList = computed(() => {
          return [{
            name: imageName,
            url: imageUrl
          }]
        })
      let showFileList= computed(() =>{
        get:{
          return props.value !== null && props.value !== ''&& props.value!==undefined;
        }
        })


     const emitInput = (val) => {

         context.emit('input',val);
        console.log(props.value);
      }


      const handleRemove = (file, fileList) => {

        emitInput('');
      }

      const handlePreview = (file) => {
        dialogVisible = true;
        console.log(file);
      }

      function beforeUpload (file) {
        //let _self = this;
        return new Promise((resolve, reject) => {
          policy().then(response => {
            console.log("响应的数据",response);
            datab.data.dataObj.policy = response.data.policy;
            datab.data.dataObj.signature = response.data.signature;
            datab.data.dataObj.ossaccessKeyId = response.data.accessid;
            datab.data.dataObj.key = response.data.dir +getUUID()+'_${filename}';
            datab.data.dataObj.dir = response.data.dir;
            datab.data.dataObj.host = response.data.host;
            console.log("响应的数据222。。。",datab.data.dataObj);
            resolve(true)
          }).catch(err => {
            reject(false)
          })
        })
      }

      const handleUploadSuccess = (res, file) =>  {
        console.log("上传成功...")
        showFileList = true;

       fileList.value.pop();
       fileList.value.push({name: file.name, url: datab.data.dataObj.host + '/' + datab.data.dataObj.key.replace("${filename}",file.name)})

       // console.log(fileList.value[1].url);

        emitInput(fileList.value[1].url);


      }
      return{
        imageUrl,
        imageName,
        emitInput,
        fileList,
        showFileList,
        handleRemove,
        handlePreview,
        beforeUpload,
        handleUploadSuccess

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
       // dialogVisible: false
      };
    },

  }
</script>
<style>

</style>


