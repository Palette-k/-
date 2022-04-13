package com.gdufe.cs.admin.controller;

import com.gdufe.cs.admin.dto.AdminUserDTO;
import com.gdufe.cs.admin.entity.User;
import com.gdufe.cs.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController {


    @Autowired
    private UserService UserService;

    // 新增和修改
    @PostMapping
    public AdminUserDTO save(@RequestBody User user) {
        // 新增或者更新
         UserService.saveUser(user);
         return new AdminUserDTO(200,"新增成功",null);

    }

    // 查询所有数据
    @GetMapping
    public List<User> index() {
        return UserService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {

        return UserService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return UserService.removeByIds(ids);
    }

    //分页查询
    //接口路径：/user/page?pageNum=1pageSize=10
    //@RequestParam接受
    //limit第一个参数 = (pageNum - 1) * pageSize
    //pageSize
 //   @GetMapping("/page")
 //   public Map<String, Object> findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
 //       pageNum = (pageNum - 1) * pageSize;
 //       List<User> data = UserMapper.selectPage(pageNum,pageSize);
 //       Integer total = UserMapper.selectTotal();
 //       Map<String, Object> res = new HashMap<>();
 //       res.put("data",data);
 //       res.put("total",total);
 //       return res;
 //   }
}

