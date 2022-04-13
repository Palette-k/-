package com.gdufe.cs.admin.controller;


import com.gdufe.cs.admin.entity.Director;
import com.gdufe.cs.admin.dto.AdminDirectorDTO;
import com.gdufe.cs.admin.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/director")
public class DirectorController {


    @Autowired
    private DirectorService DirectorService;

    // 新增和修改
    @PostMapping
    public AdminDirectorDTO save(@RequestBody Director director) {
        // 新增或者更新
        DirectorService.saveDirector(director);
        return new AdminDirectorDTO(200,"新增成功",null);
    }

    // 查询所有数据
    @GetMapping
    public List<Director> index() {
        return DirectorService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {

        return DirectorService.removeById(id);
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return DirectorService.removeByIds(ids);
    }


}
