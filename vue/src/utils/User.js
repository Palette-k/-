const axios = require("axios");

window.onload=function (){
    var vue = new Vue({
        el:"#loginSubmit",
        data:function() {
return {
          user:{}
        };
},
        methods:{
            getUser:function (){
                axios({
                    method:"POST",
                    url:"login",
                    params:{
                        operate:'UserInfo'
                    }
                }).then(function (value){
                    console.log(value);
                }).catch(function (reason){});
            }
        },
        mounted:function () {
            this.getUser();
        }
    });
}