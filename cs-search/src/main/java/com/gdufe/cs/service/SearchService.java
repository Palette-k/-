package com.gdufe.cs.service;

import com.gdufe.cs.vo.SearchParam;
import com.gdufe.cs.vo.SearchResponse;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/10 20:31
 **/
public interface SearchService {
    SearchResponse search(SearchParam searchParam);
}
