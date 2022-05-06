package com.gdufe.cs.service;

import com.gdufe.cs.es.esModel;

import java.io.IOException;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/13 20:59
 **/
public interface WorksSaveService {
    boolean worksStatusUp(esModel esModel) throws IOException;

    boolean postScore(Long worksId) throws IOException;

}
