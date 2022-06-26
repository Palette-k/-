package com.gdufe.cs.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.member.mapper.UserMapper;
import com.gdufe.cs.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/6/4 22:49
 **/
@RequestMapping("/member/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    // 新增和修改
    @PostMapping("/save")
    public ResultDTO save(@RequestBody User user) {
        // 新增或者更新
        userService.saveOrUpdate(user);
        return  ResultDTO.ok();

    }

    // 查询所有数据
    @GetMapping("/list")
    public List<User> index() {
        return userService.list();
    }

    @DeleteMapping("/del/{id}")
    public boolean delete(@PathVariable Long id) {

        return userService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Long> ids) { // [1,2,3]
        return userService.removeByIds(ids);
    }

    //分页查询
    //接口路径：/user/page?pageNum=1pageSize=10
    //@RequestParam接受
    //limit第一个参数 = (pageNum - 1) * pageSize
    //pageSize
       @GetMapping("/page")
       public IPage<User> findPage(@RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize){
           IPage<User> page = new Page<>(pageNum, pageSize);
           QueryWrapper<User> queryWrapper = new QueryWrapper<>();

           queryWrapper.orderByDesc("id");
           return userService.page(page, queryWrapper);
       }
}
