package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.entities.Producer;
import com.gdufe.cs.works.mapper.ProducerMapper;
import com.gdufe.cs.works.service.ProducerService;
import org.springframework.stereotype.Service;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/4 18:48
 **/
@Service
public class ProducerServiceImpl extends ServiceImpl<ProducerMapper, Producer> implements ProducerService {
}
