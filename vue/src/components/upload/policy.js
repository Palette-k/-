import request from "@/utils/request.js";
export function policy() {
   return  new Promise((resolve,reject)=>{
       request.get("/thirdparty/oss/policy").then( data => {
            resolve(data);
        })
    });
}