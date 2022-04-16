package com.gdufe.cs.admin.feign;

import com.gdufe.cs.entities.CommonResult;
import com.gdufe.cs.es.esModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/13 21:53
 **/
@FeignClient("cs-search")
public interface searchFeignService {
    @PostMapping("/search/save/works")
    public CommonResult worksStatusUp(@RequestBody esModel esModel);
}
