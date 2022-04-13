package com.gdufe.cs.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdufe.cs.admin.entity.Movie;
import com.gdufe.cs.admin.dto.AdminMovieDTO;
import com.gdufe.cs.admin.service.MovieService;
import com.gdufe.cs.admin.service.WorksService;
import com.gdufe.cs.entities.CommonResult;
import com.gdufe.cs.entities.Works;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/movie")//localhost:9090/movie
public class MovieController {


    @Autowired
    private MovieService MovieService;

    @Autowired
    private WorksService worksService;

    // 新增和修改
    @PostMapping
    public AdminMovieDTO save(@RequestBody Movie movie) {
        // 新增或者更新
        MovieService.saveMovie(movie);
        return new AdminMovieDTO(200,"新增成功",null);
    }

    // 查询所有数据
    @GetMapping
    public List<Movie> index() {
        return MovieService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return MovieService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return MovieService.removeByIds(ids);
    }

    @GetMapping("/page")
    public CommonResult findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String movieName)
    {
        IPage<Works> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Works> queryWrapper = new QueryWrapper<>();
        if (!"".equals(movieName)) {
            queryWrapper.like("name", movieName);
        }
        queryWrapper.orderByDesc("id");
        IPage<Works> worksIPage = worksService.page(page, queryWrapper);
        return new CommonResult(200,"查询数据成功",worksIPage);
    }


}





