package com.gdufe.cs.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.admin.cache.TagCache;
import com.gdufe.cs.admin.dto.AdminTagDTO;
import com.gdufe.cs.admin.dto.AdminWorksDTO;
import com.gdufe.cs.admin.mapper.*;
import com.gdufe.cs.admin.service.WorksService;
import com.gdufe.cs.entities.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/5 14:18
 **/
@Service
public class WorksServiceImpl extends ServiceImpl<WorksMapper, Works> implements WorksService {

    @Autowired
    private WorksMapper worksMapper;

    @Autowired
    private ProducerMapper producerMapper;

    @Autowired
    private AttrMapper attrMapper;

    @Autowired
    private TagcategoryMapper tagcategoryMapper;

    @Autowired
    private WorkscategoryMapper workscategoryMapper;

    private Long catelogId = null;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveWorksList(AdminWorksDTO adminWorksDTO) {

        Producer producer = new Producer();
        producer.setName(adminWorksDTO.getProducerName());


        //给创作者表插入一条数据
        int producerInsert = producerMapper.insert(producer);
        if(producerInsert != 1){
            throw new RuntimeException("producer插入失败");
        }


        Works works = new Works();

        works.setPid(producer.getId());
        works.setCountry(adminWorksDTO.getCountry());
        works.setIntro(adminWorksDTO.getIntro());
        works.setCommentCount(0);
        works.setName(adminWorksDTO.getName());
        works.setCreateTime(adminWorksDTO.getCreateTime());
        works.setLikeCount(0);
        works.setScore(0);
        works.setCatelogId(catelogId);
       // works.setWorkscateId(adminWorksDTO.getWorkscateId());
        works.setPath(adminWorksDTO.getPath());


        //给作品表插入一条数据
        int worksInsert = worksMapper.insert(works);
        //判断是否插入了一条数据
        if(worksInsert != 1){
            throw new RuntimeException("works插入失败");
        }


        List<String> tagList = adminWorksDTO.getTagList();


        for (int i = 0; i < tagList.size(); i++) {

            Attr attr = new Attr();
            attr.setWorksId(works.getId());

            String tag = tagList.get(i);
            QueryWrapper<Tagcategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("id").eq("tag_name",tag);
            Tagcategory tagcategory = tagcategoryMapper.selectOne(queryWrapper);
            attr.setTagId(tagcategory.getId());
            int attrInsert = attrMapper.insert(attr);

            //判断是否插入了一条数据
            if(attrInsert != 1){
                throw new RuntimeException("attr插入失败");
            }
        }

        return 1;
    }

    @Override
    public AdminTagDTO selectTag(String tagName) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tag_name",tagName);


        Tagcategory tagcategory = tagcategoryMapper.selectOne(queryWrapper);
        catelogId = tagcategory.getId();

       QueryWrapper<Tagcategory> queryWrapper2 = new QueryWrapper();
       queryWrapper2.eq("catelog_id",catelogId);
        List<Tagcategory> tagcategories = tagcategoryMapper.selectList(queryWrapper2);
        List<String> tagList = tagcategories.stream().map(tag -> tag.getTagName()).collect(Collectors.toList());

        QueryWrapper<Workscategory> queryWrapper3 = new QueryWrapper();
        queryWrapper3.eq("tagcatelog_id",catelogId);
        List<Workscategory> workscategories = workscategoryMapper.selectList(queryWrapper3);
        List<String> workscateList = workscategories.stream().map(workscate -> workscate.getCatelogName()).collect(Collectors.toList());


        AdminTagDTO adminTagDTO = new AdminTagDTO();
        adminTagDTO.setTagList(tagList);
        adminTagDTO.setWorkscateList(workscateList);

        return adminTagDTO;
    }



    private List<Tagcategory> getParent_cid(List<Tagcategory> selectList,Long catelogId){
        List<Tagcategory> tagcategoryList =
                selectList.stream().filter(item -> item.getCatelogId().equals(catelogId)).collect(Collectors.toList());
        return tagcategoryList;
    }
}
