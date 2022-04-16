package com.gdufe.cs.service.impl;

import com.gdufe.cs.config.csElasticSearchConfig;
import com.gdufe.cs.controller.SearchController;
import com.gdufe.cs.service.SearchService;
import com.gdufe.cs.vo.SearchParam;
import com.gdufe.cs.vo.SearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/10 20:31
 **/
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse search(SearchParam searchParam) {
        //1、动态构建出查询需要的DSL语句
        SearchResponse result = null;

        //1、准备检索请求
        SearchRequest searchRequest = buildSearchRequest(searchParam);

        try {
            //2、执行检索请求
            org.elasticsearch.action.search.SearchResponse response = restHighLevelClient.search(searchRequest, csElasticSearchConfig.COMMON_OPTIONS);

            //3、分析响应数据，封装成我们需要的格式
            result = buildSearchResult(response,searchParam);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 准备检索请求
     * 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存），排序，分页，高亮，聚合分析
     * @return
     */
    private SearchRequest buildSearchRequest(SearchParam param) {
        return null;
    }

    /**
     * 构建结果数据
     * 模糊匹配，过滤（按照属性、分类、品牌，价格区间，库存），完成排序、分页、高亮,聚合分析功能
     * @param response
     * @return
     */
    private SearchResponse buildSearchResult(org.elasticsearch.action.search.SearchResponse response, SearchParam param) {
        return null;
    }
}
