package com.gdufe.cs.dto;

import com.gdufe.cs.entities.Works;
import lombok.Data;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/24 9:22
 **/
@Data
public class IndexDTO {


    private Integer pageCurrent;
    private Integer pageSize;
    private Long movieTotal;
    private Long moviePages;
   private List<Works> movie;

    private Long bookTotal;
    private Long bookPages;
    private List<Works> book;

    private Long musicTotal;
    private Long musicPages;
    private List<Works> music;
}
