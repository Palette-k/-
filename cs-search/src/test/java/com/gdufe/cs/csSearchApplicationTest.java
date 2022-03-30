package com.gdufe.cs;

import org.elasticsearch.client.RestHighLevelClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/27 17:16
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class csSearchApplicationTest {
    @Resource
    private RestHighLevelClient client;

    @Test
    public void contextLoads(){
        System.out.println(client);
    }

}
