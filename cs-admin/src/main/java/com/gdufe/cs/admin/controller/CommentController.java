package com.gdufe.cs.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdufe.cs.admin.entity.Comment;
import com.gdufe.cs.admin.dto.AdminCommentDTO;
import com.gdufe.cs.admin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/comment")
public class CommentController {


    @Autowired
    private CommentService CommentService;

    // 新增和修改
    @PostMapping
    public AdminCommentDTO save(@RequestBody Comment comment) {
        // 新增或者更新
        CommentService.saveComment(comment);
        return new AdminCommentDTO(200, "新增成功", null);
    }

    // 查询所有数据
    @GetMapping
    public List<Comment> index() {
        return CommentService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {

        return CommentService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return CommentService.removeByIds(ids);
    }


    //分页查询
    //接口路径：/user/page?pageNum=1pageSize=10
    //@RequestParam接受
    //limit第一个参数 = (pageNum - 1) * pageSize
    //pageSize
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
//        pageNum = (pageNum - 1) * pageSize;
//        List<Comment> data = CommentMapper.selectPage(pageNum,pageSize);
//        Integer total = CommentMapper.selectTotal();
//        Map<String, Object> res = new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return res;
//}
    // 分页查询 - mybatis-plus的方式
    @GetMapping("/page")
    public IPage<Comment> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String commentator)
                                    {
        IPage<Comment> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        if (!"".equals(commentator)) {
            queryWrapper.like("commentator", commentator);
        }
        queryWrapper.orderByDesc("id");
        return CommentService.page(page, queryWrapper);
    }

}
