<template>
 <div  class="common-layout">
     <el-container style="border-bottom: solid 1px #e6e6e6;height: 70px"
    >
         <!--菜单-->
         <el-header class="head">
              <el-row  class="row-style">
                  <!--logo-->
                 <el-col :span="2">
                     <el-image style="width: 50px; height: 50px;margin:0 10px"
                               :src="logo"
                     ></el-image>
                 </el-col>
                  <el-col :span="1">
                      <el-button type="text">
                       <div><a href="Layout">返回首页</a></div>
                      </el-button>
                  </el-col>
             <el-col
             :span="4">
             <h1 style="text-align: center;color: #c44f49">{{Title}}</h1>
             </el-col>
             <el-col
             :span="8">
                 <!--搜索框-->
                 <el-input style="position: relative;flex:1 auto;" v-model="input" placeholder="Please input" />
             </el-col>
                  <el-col :span='3' style="margin-left: 60px;display: flex;">
                      <el-button type="text">
                        <a href="/notification">
                      <el-icon class="icon-m" size="x-large"><message /></el-icon>
                        </a>
                      </el-button>
                      <el-button type="text">
                      <el-icon class="icon-m" size="x-large"><star/></el-icon>
                      </el-button>
                      <el-button type="text">
                      <el-icon class="icon-m" size="x-large"><clock/></el-icon>
                      </el-button>
                  </el-col>
                  <!--这里是头像框，因为我嫌头像框连带着个人中心的代码太多了，我就做成小组件了，记得导入-->
                  <el-col :span="3" style="margin-left: 70px">
                     <personal></personal>
                  </el-col>
              </el-row>
         </el-header>
         <el-container>
         <el-main class="main">
             <!--选择标签-->
             <div style="margin: 0 auto;width: 600px;padding-bottom: 20px">
             <h1  class="ul" style="font-size: x-large;">{{ CatelogName }}</h1>
                 <!--全部形式-->
             <div>
             <ul class="ul">
                 <li class="li" v-for="index in catelog" :key="index.id" :class="{bac:index[0]}" >
                     <span @mousemove="changecolor(index)" :class="{color:num===index,bac:workscateId===index.id}"
                          @click="clickchange(index) "
                          class="tag">{{index.catelogName}}</span>
                 </li>
             </ul>
             </div>
                 <!--全部类型-->
             <div class="mainTag">
                 <ul class="ul">

                     <li class="li" v-for="index in tag1" :key="index.id" :class="{bac:index[0]}">
                         <span  :class="{bac:tagid===index.id}"
                               @click="clickchange1(index)"
                                id="targetTag"
                                class="tag">{{index.tagName}}</span>
                     </li>
                 </ul>
             </div>
                 <!--国家-->
                 <div>
                     <ul class="ul">
                         <li class="li" v-for="index in Country" :key="index">
                         <span @mousemove="changecolor(index)"
                               :class="{color:num===index,bac:country===index}"
                               @click="clickchange2(index)"
                               class="tag">{{index}}</span>
                         </li>
                     </ul>
                 </div>
                 <!--时间-->
                 <div>
                     <ul class="ul">
                         <li class="li" v-for="index in time" :key="index">
                         <span @mousemove="changecolor(index)"
                               :class="{color:num===index,bac:Time===index}"
                               @click="clickchange3(index)"
                               class="tag">{{index}}</span>
                         </li>
                     </ul>
                 </div>
                 <!--
                 <div>
                     <el-select v-model="value" placeholder="选择分数区间">
                     <el-option>

                     </el-option>
                     </el-select>

                 </div>
                 -->
                 <!--可选择评分条-->

             </div>

                  <div style="width: 600px;margin:0 auto;border-bottom: 10px">
                 <div class="chioce" >
                     <div>
                     <el-button v-for="item in option"
                                :key="item.id"
                     @click="clickoption(item)">{{item.name}}</el-button>
                     </div>
                     <div class="rate">
                         <label>
                 <el-select v-model="value"  placeholder="选择分数区间" size="small"   >
                     <el-option :value="this.rate"><el-slider v-model="values" range max="10" show-stops
                                           :format-tooltip="formatTooltip"
                                           size="small"
                                           style="width: 200px"/></el-option>
                 </el-select>
                         </label>
                     </div>
             </div>
                  </div>

             <!--作品图片-->
             <div style="width:600px;margin: 0 auto;">
                 <a href="Login">
                 <span v-for="index in worksList" :key="index" style="display: inline-block;margin-left: 10px">
                     <router-link :to="{path:'about',query:{id:index.worksId}}">
                   <img :src="index.img"  class="imge"/>
                     <p>
                     <span class="name" style="text-align: center" v-html="index.worksName">

                     </span>
<!--                         <span class="rate">{{index.name}}</span>-->
                     </p>
                     </router-link>
                     </span>

                 </a>
             </div>
             <div  style="text-align: center;margin-top: 4cm" v-show="totalnum===0">
                 <p>当前页面无搜索结果</p>
             </div>

           <!--分页条-->
           <div>


             <div   class="demo-pagination-block">

               <el-pagination
                   v-model:currentPage="pageCurrent"
                   v-model:page-size="pageSize"
                   layout="prev, pager, next, jumper"
                   :total="total"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"

               />
               <div class="demonstration">共{{totalPages}}页，共有{{total}}部电影</div>
             </div>
           </div>

         </el-main>
         </el-container>
     </el-container>
 </div>
</template>
<script>
    import {Clock, Message,  Star,} from "@element-plus/icons-vue"
    import personal from "../components/personal";
    import request from "@/utils/request";
    import {ref} from "vue";
    export default {
        name: "movie-classify",
        components:{Message,Star, Clock, personal, },

       data() {

            return {

                logo: require("../assets/gallery/2.png"),
                //标签
                tag1:[],
                Country:[],
                time:[],
                catelog:[],
              input:'',
              CatelogName:'',
              Title:'',
                num:0,
                //标签
               Tag:'',
                Tag1:'',
              worksList:[],
                //标签id
                tagid:'',
                workscateId:'',
                country:'',
                Time:'',
                //显示搜索不到结果
                totalnum:'',
                //评分条
                values:ref([0,10]),
                rate:'',
                value:ref(''),
                //近期热门、标记最多、评分最高、最新上映
                tag2:'',
                catelogId:'',
              total:'',
              pageCurrent:1,
              pageSize:9,
              totalPages:'',
              tagIndex:'',
                option:[{id:0,name:"近期热门",value:"createTime_desc"},{id:1,name:"标记最多",value: "score_asc" },
                    {id:2,name:"评分最高",value: "score_desc"},{id:3,name:"最新上映",value: "createTime_desc"}]



            }
        },
        //将自定义的body边距改为0px
        beforeCreate: function () {
            document.querySelector('body').setAttribute('style', 'margin:0;')
        },
      methods:{
            formatTooltip:function(){
                this.rate=this.values;
                this.choose()
            },

            //鼠标移动到tag，tag变色
               changecolor:function (message) {

              //console.log("message:",message)
                this.num=message;
                   },
          //鼠标点击tag变色并且获取标签id
          clickchange(index){

                this.workscateId=index.id;
                   this.choose();
          },
          clickchange1(index){

            this.$nextTick(function (){

              let mainTag = document.getElementsByClassName('mainTag');
              //console.log("mainTag:",mainTag)
              let ul = mainTag[0].getElementsByClassName('ul');
              console.log("ul:",ul)
              let li = ul[0].getElementsByClassName('li')
              // let lis=Array.from(li)

              //console.log("li22222:",li[1])
              let span = li[this.tagIndex].getElementsByClassName('tag');

              span[0].classList.remove("bac");
              //span[0].classList.add("color")
              //console.log("span",span)
            })

              console.log("index",index)
            this.tagid=index.id
            this.choose();
          },

          clickchange2(index){
              this.country=index;
              this.choose();
          },
          clickchange3(index){
            this.Time=index;
            this.choose();
          },
          clickoption(item){
                this.tag2=item.value;
                this.choose()
          },
        handleCurrentChange(page){
          this.currentPage = page;
          //  console.log(this.currentPage);
          this.choose();
        },

        handleSizeChange(size){
          this.pageSize = size;
          //  console.log(this.pageSize);
          this.choose();
        },


       choose(){

           request.get("/search/listPage?catelogId=" + this.catelogId +"&tagcatelogId="+this.tagid+
               "&workscateId="+this.workscateId+"&country="+this.country+"&createTime="+this.Time+
               "&scoreSection="+this.rate[0]+"_"+this.rate[1]+"&sort="+this.tag2 + "&pageNum=" + this.pageCurrent+
                 "&keyword=" + this.input).then(response =>{
               console.log(response)
               const res=response.worksList;
               this.worksList=res;
               this.totalPages = response.totalPages;
               this.total = response.total;
               this.pageNum = response.pageNum;
               console.log(res)
               this.totalnum=res.length
           })
       },
      Tags(){
              if(this.$route.query.catelogId){
                request.get("/works/showSearchParam/" + this.$route.query.catelogId ).then(response =>{
                  const data=response.data;
                  this.tag1=data.tagList;
                  this.Country=data.country;
                  this.time=data.createTime;
                  this.catelog=data.workscategories;

                  if(this.$route.query.tagcatelogId){

                    // console.log("tag1",this.tag1);
                    console.log("tagid:",this.tagid);
                    this.tag1.forEach((tag,index) => {
                      if(tag.id == this.tagid){
                        this.tagIndex = index;
                      }
                    })
                    console.log("index:",this.tagIndex)


               this.$nextTick(function (){

                   let mainTag = document.getElementsByClassName('mainTag');
                   //console.log("mainTag:",mainTag)
                   let ul = mainTag[0].getElementsByClassName('ul');
                   console.log("ul:",ul)
                   let li = ul[0].getElementsByClassName('li')
                   // let lis=Array.from(li)

                   //console.log("li22222:",li[1])
                    let span = li[this.tagIndex].getElementsByClassName('tag');

                     span[0].classList.add("bac");
                   //  span[0].classList.add("color")
                 //console.log("span",span)
                    })
               }

                })

              }

      },

        judge(){
              if(this.$route.query.tagcatelogId){
                this.tagid = this.$route.query.tagcatelogId

              }
              if(this.$route.query.catelogId){
                this.catelogId = this.$route.query.catelogId
                if(this.$route.query.catelogId == 40){
                  this.CatelogName = "选影视"
                  this.Title = "电影"
                }else if(this.$route.query.catelogId == 41){
                  this.CatelogName = "选音乐"
                  this.Title = "音乐"
                }else{
                  this.CatelogName = "选书籍"
                  this.Title = "书籍"
                }

              }

              if(this.$route.query.keyword){
                this.input = this.$route.query.keyword
              }
        }

      },
        mounted(){
            this.Tags();
            this.judge();
            this.choose();


        },
    }

</script>

<style scoped>
    .common-layout{
       min-width: 1300px;
        max-width: 1300px;
        position: relative;
    }
    .head{
       width: 1280px;
        position: relative;

    }
    .head .row-style{
        position: relative;
        display: flex;
        align-items: center;
        margin: 0 auto;
    }

.icon-m{
    margin: 0 5px;
    color: black;
}

    .ul{
        display: inline-block;
        margin-bottom: 17px;
        width: 600px;
    }
    .tag{
        zoom: 1;
        cursor: pointer;
        font-size: 14px;
        padding: 2px 10px;
        line-height: 20px;
        border-radius: 2px;
        text-align: -webkit-match-parent;


    }
    .li{
        display: inline-block;

    }
  .el-main{
      width: 1024px;
      margin: 0 auto;
      flex: none;
  }
img{
    width:115px;
    padding-right: 20px;
 }
    p{
        overflow: hidden;
        line-height: 18px;
        padding-right: 15px;
        margin: 8px 0 20px;
    }
    .name{
        margin-right: 5px;
    }
    .rate{
        color: darkorange;
    }
    ul {
        display: block;
        list-style-type: disc;
       margin: 0;
        padding: 0;
    }
    .color:hover{

        color: #3a8ee6;
    }
    .bac{
        background-color: #c45656;
        color: white;
    }
    a{
        text-decoration: none;
        color: black;
    }
    .chioce{
        display: inline-flex;
        box-sizing: border-box;
        margin: 0 auto;
       align-items: center;
        float: right;
        width: 600px;
    }
    .rate{
        margin:0 auto;

    }
    .demo-pagination-block .demonstration {
      margin-top: 10px;
      margin-bottom: 16px;
      text-align: center;
    }

    .demo-pagination-block{
      margin: auto;
      width: 40%;

      bottom: 0;

    }


</style>
<style lang="scss">
.highlights-text {
  color: #ff5134;
}
</style>
