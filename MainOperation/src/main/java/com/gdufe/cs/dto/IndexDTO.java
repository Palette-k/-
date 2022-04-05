package com.gdufe.cs.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdufe.cs.entities.Movie;
import lombok.Data;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/24 9:22
 **/
@Data
public class IndexDTO {

 //  private Page<Movie> moviePage;
    private Integer pageCurrent;
    private Integer pageSize;
    private Long pageTotal;
    private Long pages;
   private List<Movie> movies;
}
