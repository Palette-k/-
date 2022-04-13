package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.dto.AttrDTO;
import com.gdufe.cs.dto.IndexDTO;
import com.gdufe.cs.dto.ProducerDTO;
import com.gdufe.cs.dto.WorksDTO;
import com.gdufe.cs.entities.Attr;
import com.gdufe.cs.entities.Producer;
import com.gdufe.cs.entities.Tagcategory;
import com.gdufe.cs.entities.Works;
import com.gdufe.cs.works.cache.TagCache;
import com.gdufe.cs.works.mapper.AttrMapper;
import com.gdufe.cs.works.mapper.ProducerMapper;
import com.gdufe.cs.works.mapper.TagcategoryMapper;
import com.gdufe.cs.works.mapper.WorksMapper;
import com.gdufe.cs.works.service.TagcategoryService;
import com.gdufe.cs.works.service.WorksService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/15 17:40
 **/
@Service
public class WorksServiceImpl extends ServiceImpl<WorksMapper, Works> implements WorksService {

    @Autowired
    private WorksMapper worksMapper;

    @Autowired
    private AttrMapper attrMapper;


    @Override
    public IndexDTO indexPage(Integer pageCurrent,Integer pageSize) {
        IndexDTO indexDTO = new IndexDTO();
        Page<Works> page = new Page<>(pageCurrent,pageSize);
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("catelog_id",1);
        worksMapper.selectPage(page,queryWrapper1);
        indexDTO.setPageSize(pageSize);
        indexDTO.setPageCurrent(pageCurrent);

        indexDTO.setMovieTotal(page.getTotal());
        indexDTO.setMovie(page.getRecords());
        indexDTO.setMoviePages(page.getPages());

        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("catelog_id",2);
        worksMapper.selectPage(page,queryWrapper2);
        indexDTO.setMusicTotal(page.getTotal());
        indexDTO.setMusic(page.getRecords());
        indexDTO.setMusicPages(page.getPages());

        QueryWrapper queryWrapper3 = new QueryWrapper();
        queryWrapper3.eq("catelog_id",3);
        worksMapper.selectPage(page,queryWrapper3);
        indexDTO.setBookTotal(page.getTotal());
        indexDTO.setBook(page.getRecords());
        indexDTO.setBookPages(page.getPages());
        return indexDTO;

    }




    //根据标签查询对应作品
    @Override
    public List<Works> selectWorksByTagId(long tagId,Integer pageCurrent,Integer pageSize) {

       // IPage<Map<String, Object>> page = new Page<>(pageCurrent,pageSize);

        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id",tagId);
        List<Attr> attrs = attrMapper.selectList(queryWrapper);

        //IPage<Map<String, Object>> mapIPage = attrMapper.selectMapsPage(page, queryWrapper);


        List<Long> worksIds = attrs.stream().map(attr -> attr.getWorksId()).collect(Collectors.toList());

        List<Works> worksList = new ArrayList<>();
       worksIds.forEach(worksId ->{
           Works works = worksMapper.selectById(worksId);
           worksList.add(works);
       });

       return worksList;

    }

    private List<Tagcategory> getParent_cid(List<Tagcategory> selectList,Long catelogId){
        List<Tagcategory> tagcategoryList =
                selectList.stream().filter(item -> item.getCatelogId().equals(catelogId)).collect(Collectors.toList());
        return tagcategoryList;
    }

}
