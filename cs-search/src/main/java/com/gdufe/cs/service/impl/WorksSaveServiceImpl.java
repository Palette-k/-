package com.gdufe.cs.service.impl;

import com.alibaba.fastjson.JSON;
import com.gdufe.cs.config.csElasticSearchConfig;
import com.gdufe.cs.constant.EsConstant;
import com.gdufe.cs.es.esModel;
import com.gdufe.cs.service.WorksSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/13 21:00
 **/
@Service
@Slf4j
public class WorksSaveServiceImpl implements WorksSaveService {
    @Autowired
    private RestHighLevelClient esRestClient;

    @Override
    public boolean worksStatusUp(esModel esModel) throws IOException {
        //1.在es中建立好索引，保存映射关系

        //2.在es中保存好数据
       // BulkRequest bulkRequest = new BulkRequest();

        IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
        indexRequest.id(esModel.getWorksId().toString());
        String jsonString = JSON.toJSONString(esModel);
        indexRequest.source(jsonString, XContentType.JSON);

        IndexResponse index = esRestClient.index(indexRequest, csElasticSearchConfig.COMMON_OPTIONS);


        boolean hasFailure = true;

        if(index.getShardInfo().getSuccessful() == 1) hasFailure = false; //作品上架成功

        return hasFailure;
    }
    //更新评分字段
    @Override
    public boolean postScore(Long worksId) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest();


        esRestClient.update(updateRequest,csElasticSearchConfig.COMMON_OPTIONS);

        return false;
    }
}
