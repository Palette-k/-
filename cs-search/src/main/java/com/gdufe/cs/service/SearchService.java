package com.gdufe.cs.service;

import com.gdufe.cs.vo.SearchParam;
import com.gdufe.cs.vo.SearchResult;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/10 20:31
 **/
public interface SearchService {
    SearchResult search(SearchParam searchParam);
}
