package com.gdufe.cs.controller;

import com.gdufe.cs.dto.WorksDTO;
import com.gdufe.cs.entities.Works;
import com.gdufe.cs.service.SearchService;
import com.gdufe.cs.vo.SearchParam;
import com.gdufe.cs.vo.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/10 20:30
 **/
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/listPage")
    public SearchResponse listPage(SearchParam searchParam){
        SearchResponse result  = searchService.search(searchParam);

        return null;
    }
}
