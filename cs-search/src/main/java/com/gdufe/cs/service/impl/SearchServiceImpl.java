package com.gdufe.cs.service.impl;

import com.alibaba.fastjson.JSON;
import com.gdufe.cs.config.csElasticSearchConfig;
import com.gdufe.cs.constant.EsConstant;
import com.gdufe.cs.es.esModel;
import com.gdufe.cs.service.SearchService;
import com.gdufe.cs.vo.SearchParam;
import com.gdufe.cs.vo.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.TotalHits;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/10 20:31
 **/
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResult search(SearchParam searchParam) {
        //1、动态构建出查询需要的DSL语句
        SearchResult result = null;

        //1、准备检索请求
        SearchRequest searchRequest = buildSearchRequest(searchParam);

        try {
            //2、执行检索请求
            SearchResponse response = restHighLevelClient.search(searchRequest, csElasticSearchConfig.COMMON_OPTIONS);

            //3、分析响应数据，封装成我们需要的格式
            result = buildSearchResult(response,searchParam);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    /**
     * 构建结果数据
     * 模糊匹配，过滤，完成排序、分页、高亮,聚合分析功能
     * @param response
     * @return
     */
    private SearchResult buildSearchResult(SearchResponse response, SearchParam param) {

        SearchResult result = new SearchResult();

        SearchHits hits = response.getHits();
        //1.返回所有查询到的作品信息

        List<esModel> esModelList = new ArrayList<>();
        //遍历所有商品信息
        if(hits.getHits() != null && hits.getHits().length > 0){
            for (SearchHit hit : hits.getHits()) {
                String sourceAsString = hit.getSourceAsString();
                esModel esModel = JSON.parseObject(sourceAsString, esModel.class);

                //判断是否按关键字检索，若是就显示高亮，否则不显示
                if(!StringUtils.isEmpty(param.getKeyword())){
                    //拿到高亮显示作品名
                    HighlightField worksName = hit.getHighlightFields().get("worksName");
                    String worksNamevalue = worksName.getFragments()[0].string();
                    esModel.setWorksName(worksNamevalue);
                }
                esModelList.add(esModel);
            }
        }
        result.setWorksList(esModelList);

        //2.返回所有查询到的作品形式信息
        List<SearchResult.workscatelogVo> workscatelogVos = new ArrayList<>();


        //3.返回所有查询到的作品标签信息
        List<SearchResult.tagcatelogVo> tagcatelogVos = new ArrayList<>();


        ParsedLongTerms catelog_agg = response.getAggregations().get("catelog_agg");

        for (Terms.Bucket bucket : catelog_agg.getBuckets()) {

            ParsedNested tagcatelog_agg = bucket.getAggregations().get("tagcatelog_agg");
            ParsedLongTerms tagcatelog_id_agg = tagcatelog_agg.getAggregations().get("tagcatelog_id_agg");

            for (Terms.Bucket tagcatelog_id_aggBucket : tagcatelog_id_agg.getBuckets()) {

                SearchResult.tagcatelogVo tagcatelogVo = new SearchResult.tagcatelogVo();
                //设置作品标签Id
                tagcatelogVo.setTagId((Long)tagcatelog_id_aggBucket.getKeyAsNumber());

                ParsedStringTerms tagcatelog_name_agg = tagcatelog_id_aggBucket.getAggregations().get("tagcatelog_name_agg");
                for (Terms.Bucket tagcatelog_name_aggBucket : tagcatelog_name_agg.getBuckets()) {
                    //设置作品标签名
                    tagcatelogVo.setTagName(tagcatelog_name_aggBucket.getKeyAsString());

                }
                tagcatelogVos.add(tagcatelogVo);
            }
            result.setTagcatelogVos(tagcatelogVos);


            ParsedLongTerms workscate_agg = bucket.getAggregations().get("workscate_agg");
            for (Terms.Bucket workscate_aggBucket : workscate_agg.getBuckets()) {
                SearchResult.workscatelogVo workscatelogVo = new SearchResult.workscatelogVo();
               //设置作品形式id
                workscatelogVo.setWorkscateId((Long) workscate_aggBucket.getKeyAsNumber());

                ParsedStringTerms workscate_name_agg = workscate_aggBucket.getAggregations().get("workscate_name_agg");
                for (Terms.Bucket workscate_name_aggBucket : workscate_name_agg.getBuckets()) {
                    //设置作品形式名
                    workscatelogVo.setWorkscateName(workscate_name_aggBucket.getKeyAsString());

                }
                workscatelogVos.add(workscatelogVo); //加到作品形式list中
            }
            result.setWorkscatelogVos(workscatelogVos); //加到作品形式

           //返回国家信息
            List<String> country = new ArrayList<>();
            ParsedStringTerms country_agg = bucket.getAggregations().get("country_agg");
            for (Terms.Bucket country_aggBucket : country_agg.getBuckets()) {
                country.add( country_aggBucket.getKeyAsString());
            }
            result.setCountry(country);

            //返回创作时间信息
            List<String> createTime = new ArrayList<>();
            ParsedStringTerms createTime_agg = bucket.getAggregations().get("createTime_agg");
            for (Terms.Bucket createTime_aggBucket : createTime_agg.getBuckets()) {
                createTime.add(createTime_aggBucket.getKeyAsString());
            }
            result.setCreateTime(createTime);
        }



        //4.返回分页信息-页码
        result.setPageNum(param.getPageNum());
        //4.返回分页信息-总记录数
        long totalHits = hits.getTotalHits().value;
        result.setTotal(totalHits);
        //4.返回分页信息-总页数
        int totalPages = (int)totalHits%EsConstant.PRODUCT_PAGESIZE == 0 ? (int)totalHits/EsConstant.PRODUCT_PAGESIZE : (int)totalHits/EsConstant.PRODUCT_PAGESIZE + 1;
        result.setTotalPages(totalPages);


        return result;

    }

    /**
     * 准备检索请求
     * 模糊匹配，过滤，排序，分页，高亮，聚合分析
     * @return
     */
    private SearchRequest buildSearchRequest(SearchParam param) {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /**
        * 模糊匹配，过滤
        * */
        //1. 构建bool-query
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        //1.1 bool-must
        if(!StringUtils.isEmpty(param.getKeyword())){
            boolQueryBuilder.must(QueryBuilders.matchQuery("worksName",param.getKeyword()));
        }

        //1.2 bool-filter
        //catelogId
        if(null != param.getCatelogId()){
            boolQueryBuilder.must(QueryBuilders.matchQuery("catelogId",param.getCatelogId()));
        }

        //workscateId
        if(null != param.getWorkscateId()){
            boolQueryBuilder.must(QueryBuilders.matchQuery("workscateId",param.getWorkscateId()));
        }
        //country
        if(!StringUtils.isEmpty(param.getCountry())){
            boolQueryBuilder.must(QueryBuilders.matchQuery("country",param.getCountry()));
        }

        //createTime
       if(!StringUtils.isEmpty(param.getCreateTime())){
           boolQueryBuilder.must(QueryBuilders.matchQuery("createTime",param.getCreateTime()));
       }

       //tagcatelogId
      if(null != param.getTagcatelogId()){
          BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
          boolQuery.must(QueryBuilders.matchQuery("tagList.id",param.getTagcatelogId()));

          NestedQueryBuilder tagList = QueryBuilders.nestedQuery("tagList", boolQuery, ScoreMode.None);

          boolQueryBuilder.filter(tagList);
      }

      //scoreSection
        if(!StringUtils.isEmpty(param.getScoreSection())){
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("score");
            String[] score = param.getScoreSection().split("_");
            if(score.length == 2){
                rangeQueryBuilder.gte(score[0]).lte(score[1]);
            }else if(score.length == 1){
                if(param.getScoreSection().startsWith("_")){
                    rangeQueryBuilder.lte(score[1]);
                }
                if(param.getScoreSection().endsWith("_")){
                    rangeQueryBuilder.gte(score[0]);
                }
            }
            boolQueryBuilder.filter(rangeQueryBuilder);
        }

        //封装所有的查询条件
        searchSourceBuilder.query(boolQueryBuilder);

        /**
         * 排序，分页，高亮
         */
        //排序
        //形式为 sort = hotScore_asc/desc
        if(!StringUtils.isEmpty(param.getSort())){
            String sort = param.getSort();
            String[] sortFileds = sort.split("_");

            SortOrder sortOrder = "asc".equalsIgnoreCase(sortFileds[1])? SortOrder.ASC:SortOrder.DESC;

            searchSourceBuilder.sort(sortFileds[0],sortOrder);
        }

        //分页
        searchSourceBuilder.from((param.getPageNum()-1)*EsConstant.PRODUCT_PAGESIZE);
        searchSourceBuilder.size(EsConstant.PRODUCT_PAGESIZE);

        //高亮
        if(!org.springframework.util.StringUtils.isEmpty(param.getKeyword())){

            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("worksName");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");

            searchSourceBuilder.highlighter(highlightBuilder);
        }

        //聚合分析
        //1.根据作品大分类进行聚合  catelog_agg
        //以下所有属性都是在catelog下的子聚合
        TermsAggregationBuilder catelog_agg = AggregationBuilders.terms("catelog_agg");
        catelog_agg.field("catelogId").size(10);

        //2.根据作品形式进行聚合   workscate_agg
        TermsAggregationBuilder workscate_agg = AggregationBuilders.terms("workscate_agg")
                .field("workscateId").size(100);
        //2.1 作品形式的子聚合-作品名的聚合
        workscate_agg.subAggregation(AggregationBuilders.terms("workscate_name_agg")
                .field("catelogName").size(1));

        catelog_agg.subAggregation(workscate_agg);

        //3.根据作品国家进行聚合   country_agg
       catelog_agg.subAggregation(AggregationBuilders.terms("country_agg")
                .field("country").size(100));


        //4.根据作品创作时间进行聚合 createTime_agg
        catelog_agg.subAggregation(AggregationBuilders.terms("createTime_agg")
                .field("createTime").size(10));


        //5.根据作品标签进行聚合   tagcatelog_agg
        NestedAggregationBuilder tagcatelog_agg = AggregationBuilders.nested("tagcatelog_agg","tagList");
        // 5.1 根据作品标签id进行聚合
        TermsAggregationBuilder tagcatelog_id_agg = AggregationBuilders.terms("tagcatelog_id_agg").field("tagList.id");
        tagcatelog_agg.subAggregation(tagcatelog_id_agg);
        // 5.2 根据作品标签名进行聚合
        TermsAggregationBuilder tagcatelog_name_agg = AggregationBuilders.terms("tagcatelog_name_agg").field("tagList.tagName");
        tagcatelog_id_agg.subAggregation(tagcatelog_name_agg);

        catelog_agg.subAggregation(tagcatelog_agg);

        searchSourceBuilder.aggregation(catelog_agg);




        log.info("构建的DSL语句 {}",searchSourceBuilder.toString());

        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX},searchSourceBuilder);



        return searchRequest;
    }


}
