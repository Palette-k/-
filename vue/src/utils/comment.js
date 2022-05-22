import request from "@/utils/request";
import login2 from "../components/login2"
var tp;
export default {
    name: "Comment",
    props: ['comment','mid'],
    emits:['update:comment'],
    components:{login2},
    computed: {
        comments() {
            return this.comment
        },
        id() {
            return this.mid
        }
    },
    data() {
        return {
            user:{
                id:'',
                name: " ",
                authorImg: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
            },
            content: '',
            content1: '',
            dialogVisible: false,

        }
    },
    methods: {
        //获取用户信息
        getUser(){
            if(this.$store.state.id){
                request.get("/member/user/findUserById?userId=" + this.$store.state.id).then(res =>{
                    this.user.authorImg = res.img
                    this.user.name = res.username
                    this.user.id = res.id
                    /*console.log(this.user.name)*/

                })
            }
        },


        //获取二级评论
        getComment2(index,pid,comment){
            this.comments[index].reply=[]
            let a={}
            request.get("/works/comment/" + pid).then(res =>{
                const data = res.data;
                /*console.log(data)*/
                for(let i in data) {
                    if(data[i].parentId == pid) {
                        a.img =data[i].user.img
                        a.id =data[i].user.id
                        a.from =data[i].user.username
                        a.toid =comment.find(e => e.id==pid).user.id
                        a.to =comment.find(e => e.id==pid).user.username
                        a.parentId = data[i].parentId
                        a.selfId =data[i].id
                        a.replyComment =data[i].content
                        a.time =data[i].gmtCreate
                        a.likeCount =data[i].likeCount
                        a.commentCount =data[i].commentCount
                        a.liked =false
                        this.comments[index].reply.push(a)
                        a ={}
                        /*console.log(this.comments[index].reply)*/
                    }
                }

            })
        },
        getZan(z){
            console.log(z)
            request.post('/works/auth/like', z).then(res =>{
                console.log(res)
            })
        },
        postComment(a){
            console.log(a)
            request.post('/works/auth/comment/' + this.$store.state.id ,a).then(res => {
                if(res.code == 200) {
                    console.log(res)
                    this.$message.success("评论成功！")
                    // window.location.reload();
                }else {
                    console.log(res)
                    this.$message.error("评论失败！")
                }
            })
        },
        sendComment() {
            /*写评论*/
            if(this.$store.state.id) {
                if (!this.content) {
                    this.$message({
                        showClose: true,
                        type: 'warning',
                        message: '评论不能为空！'
                    })
                } else {
                    let a = {}
                    let b = {
                        user:{
                            username:'',
                            id: '',
                            img:'',
                        },
                    }
                    let timeNow = new Date().getTime()
                    b.user.username = this.user.name
                    b.user.id = this.user.id
                    b.user.img = this.user.authorImg
                    b.content = this.content
                    b.gmtCreate = timeNow
                    b.commentCount = 0
                    b.likeCount = 0
                    this.comments.unshift(b)
                    this.$emit('update:comment',this.comments)
                    a.parentId =this.id
                    a.content = this.content
                    a.type = 1
                    this.postComment(a)
                    /*console.log(JSON.stringify(a))*/
                    this.content =''
                }
            }

        },
        sendCommentReply(index,i) {
            /*回复功能*/
            if(this.$store.state.id) {
                if (!this.content1) {
                    this.$message({
                        showClose: true,
                        type: 'warning',
                        message: '评论不能为空！'
                    })
                } else {
                    let a = {}
                    let b ={}
                    let timeNow = new Date().getTime()
                    b.from = this.from
                    b.to = this.to
                    b.replyComment = this.content1
                    b.img = this.user.authorImg
                    b.time = timeNow
                    b.commentCount = 0
                    b.likeCount = 0
                    this.comments[index].reply.unshift(b)
                    this.comments[index].commentCount++
                    a.parentId = this.commentId
                    a.content = this.content1
                    a.type = 2
                    this.postComment(a)
                    this.content1 =''
                    this.comments[index].inputShow =false
                }
            }

        },
        CountP(index) {
            /*父组件点赞*/
            if(this.$store.state.id) {
                let zan = {}
                zan.likedPostId =this.$store.state.id
                zan.likedParentId = this.comments[index].id
                zan.type = 1
                if(!this.comments[index].liked) {
                    this.comments[index].likeCount++
                    zan.status = 1
                } else {
                    this.comments[index].likeCount--
                    zan.status = 0
                }
                this.getZan(zan)
                this.comments[index].liked =!this.comments[index].liked
            }

        },
        CountC(index,i) {
            /*子组件点赞*/
            if(this.$store.state.id) {
                let zan = {}
                zan.likedPostId =this.$store.state.id
                zan.likedParentId = this.comments[index].reply[i].selfId
                zan.type = 1
                if(!this.comments[index].reply[i].liked) {
                    this.comments[index].reply[i].likeCount++
                    zan.status = 1
                }else {
                    this.comments[index].reply[i].likeCount--
                    zan.status = 0
                }
                this.getZan(zan)
                this.comments[index].reply[i].liked = !this.comments[index].reply[i].liked
            }

        },
        showReplyInput(i,name,id,commentId) {
            if(this.$store.state.id) {
                /*关闭父评论回复框*/
                if(tp || tp==0) {
                    this.comments[tp].inputShow= false
                    /*console.log(tp)*/
                }
                tp = i

                /*关闭子评论回复框*/
                this.comments[i].inputShow =false
                this.index = i
                /*显示*/
                this.comments[i].inputShow =true
                this.to =name
                this.toId = id
                this.commentId =commentId
                // document.querySelector('.reply-input').scrollIntoView(true)
            }

        },
        //展开回复
        showReply(index,pid){
            this.comments[index].show = !this.comments[index].show;
            this.comments[index].inputShow =false
            this.getComment2(index,pid,this.comments)
        },
        show(index){
            return this.comment[index].show
        },
        inputShow(index) {
            return this.comment[index].inputShow
        },
        dateStr(date) {
            //获取js 时间戳
            var time = new Date().getTime();
            //去掉 js 时间戳后三位，与php 时间戳保持一致
            time = parseInt((time - date) / 1000);
            //存储转换值
            var s;
            if (time < 60 * 10) {//十分钟内
                return '刚刚';
            } else if ((time < 60 * 60) && (time >= 60 * 10)) {
                //超过十分钟少于1小时
                s = Math.floor(time / 60);
                return s + "分钟前";
            } else if ((time < 60 * 60 * 24) && (time >= 60 * 60)) {
                //超过1小时少于24小时
                s = Math.floor(time / 60 / 60);
                return s + "小时前";
            } else if ((time < 60 * 60 * 24 * 30) && (time >= 60 * 60 * 24)) {
                //超过1天少于30天内
                s = Math.floor(time / 60 / 60 / 24);
                return s + "天前";
            } else {
                //超过30天ddd
                var date = new Date(parseInt(date));
                return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
            }
        },
        isLogin() {
            if(this.$store.state.id) {
                 this.dialogVisible=false
                return true
            }else {
                this.dialogVisible = true;
                return false
            }
        }
    },

    mounted() {
        this.getUser()
    }
}
