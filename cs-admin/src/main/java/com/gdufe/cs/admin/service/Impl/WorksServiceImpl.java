package com.gdufe.cs.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.gdufe.cs.admin.dto.AdminTagDTO;
import com.gdufe.cs.admin.dto.AdminWorksDTO;
import com.gdufe.cs.admin.feign.searchFeignService;
import com.gdufe.cs.admin.mapper.*;
import com.gdufe.cs.admin.service.WorksService;
import com.gdufe.cs.entities.*;
import com.gdufe.cs.enums.WorksStatusEnum;
import com.gdufe.cs.es.esModel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/5 14:18
 **/
@Service
@Slf4j
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


   @Autowired
   private searchFeignService searchFeignService;

    private Long catelogId = null;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveWorksList(AdminWorksDTO adminWorksDTO) {

        Works works = new Works();
        QueryWrapper<Producer> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("name",adminWorksDTO.getProducerName());
        Producer producer = producerMapper.selectOne(queryWrapper1);
        if(null == producer){

         //  log.info("创作者名字{}",adminWorksDTO.getProducerName());
            Producer producer1 = new Producer();
            producer1.setName(adminWorksDTO.getProducerName());


            //给创作者表插入一条数据
            int producerInsert = producerMapper.insert(producer1);
            if(producerInsert != 1){
                throw new RuntimeException("producer插入失败");
            }
            works.setPid(producer1.getId());
        }else{
            works.setPid(producer.getId());
        }



        works.setCountry(adminWorksDTO.getCountry());
        works.setIntro(adminWorksDTO.getIntro());
        works.setCommentCount(0);
        works.setName(adminWorksDTO.getName());
        works.setCreateTime(adminWorksDTO.getCreateTime());
        works.setHaveCount(0);
        works.setWantCount(0);
        works.setScore(0);
        works.setCatelogId(catelogId);
        works.setWorkscateId(adminWorksDTO.getWorkscateId());
        works.setPath(adminWorksDTO.getPath());
        works.setStatus(WorksStatusEnum.NEW.getCode());
        works.setScoreCount(0);

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

    @Override
    public void up(Long worksId)  {
        Works works = worksMapper.selectById(worksId);

        /* 查出该作品的形式 */
        Long catelogId = works.getCatelogId();
        Long workscateId = works.getWorkscateId();
        QueryWrapper<Workscategory> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", workscateId);
        Workscategory workscategory = workscategoryMapper.selectOne(queryWrapper1);
        String catelogName = workscategory.getCatelogName();

        /* 查出作品的标签 */
        QueryWrapper<Attr> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("works_id",worksId);
        List<Attr> attrs = attrMapper.selectList(queryWrapper2);
        List<Long> tagIds = attrs.stream().map(attr -> attr.getTagId()).collect(Collectors.toList());

        List<Tagcategory> tagcategories1 = new ArrayList<>();
        for (Long tagId : tagIds) {
            QueryWrapper<Tagcategory> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("id",tagId);
            Tagcategory tagcategory = tagcategoryMapper.selectOne(queryWrapper3);

            tagcategories1.add(tagcategory);
        }

        List<esModel.Tagcategory> tagcategories =
                tagcategories1.stream().map(item -> {
                    esModel.Tagcategory tagcategory = new esModel.Tagcategory();
                    tagcategory.setId(item.getId());
                    tagcategory.setTagName(item.getTagName());
                    return tagcategory;
                }).collect(Collectors.toList());

        QueryWrapper<Producer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",works.getPid());
        Producer producer = producerMapper.selectOne(queryWrapper);
        String producerName = producer.getName();


        esModel esModel = new esModel();
        esModel.setCountry(works.getCountry());
        esModel.setImg(works.getPath());
        esModel.setCreateTime(works.getCreateTime());
        esModel.setWorksId(worksId);
        esModel.setWorksName(works.getName());
        esModel.setCatelogId(works.getCatelogId());
        esModel.setTagList(tagcategories);
        esModel.setWorkscateId(workscateId);
        esModel.setCatelogName(catelogName);
        esModel.setScore(works.getScore());
        esModel.setHotScore(0L);
        esModel.setProducerName(producerName);

        //把封装好的数据发送给es进行保存，作品发布成功
        CommonResult result = searchFeignService.worksStatusUp(esModel);
        if(result.getCode() == 200){
            //更新作品发布状态
            worksMapper.updateStatus(worksId, WorksStatusEnum.UP.getCode());
        }

    }

    @Override
    public List<AdminWorksDTO> selectWorks() {

        List<AdminWorksDTO> adminWorksDTOList = new ArrayList<>();

        QueryWrapper<Works> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.orderByDesc("id");
        List<Works> worksList = worksMapper.selectList(queryWrapper2);

        for (Works works : worksList) {
            AdminWorksDTO adminWorksDTO = new AdminWorksDTO();
            adminWorksDTO.setId(works.getId());
            adminWorksDTO.setName(works.getName());
            adminWorksDTO.setCreateTime(works.getCreateTime());
            adminWorksDTO.setWorkscateId(works.getWorkscateId());
            adminWorksDTO.setPath(works.getPath());
            adminWorksDTO.setIntro(works.getIntro());
            adminWorksDTO.setCountry(works.getCountry());

            if(works.getCatelogId() != null){
                adminWorksDTO.setCatelogId(works.getCatelogId());
                Tagcategory tagcategory = tagcategoryMapper.selectById(works.getCatelogId());
                adminWorksDTO.setTagName(tagcategory.getTagName());
            }


            if(works.getWorkscateId()!=null){
                adminWorksDTO.setWorkscateId(works.getWorkscateId());
                Workscategory workscategory = workscategoryMapper.selectById(works.getWorkscateId());
                adminWorksDTO.setCatelogName(workscategory.getCatelogName());
            }

            if(works.getPid() != null){
                adminWorksDTO.setPid(works.getPid());
                Producer producer = producerMapper.selectById(works.getPid());
                adminWorksDTO.setProducerName(producer.getName());
            }


            adminWorksDTO.setStatus(works.getStatus());

            QueryWrapper<Attr> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("works_id",works.getId());
            List<Attr> attrs = attrMapper.selectList(queryWrapper);
            List<Long> tagIds = attrs.stream().map(attr -> attr.getTagId()).collect(Collectors.toList());

            List<String> tagList = new ArrayList<>();
            for (Long tagId : tagIds) {
                QueryWrapper<Tagcategory> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id",tagId);
                Tagcategory tagcategory1 = tagcategoryMapper.selectOne(queryWrapper1);
                String tagName = tagcategory1.getTagName();
                tagList.add(tagName);
            }

            adminWorksDTO.setTagList(tagList);

            adminWorksDTOList.add(adminWorksDTO);
        }

        return adminWorksDTOList;
    }

    @Override
    public void postScore(Long worksId) {

        

    }

    @Override
    @Transactional
    public void del(Long id){
        QueryWrapper<Works> worksQueryWrapper = new QueryWrapper<>();
        worksQueryWrapper.eq("id", id);
        Works works = worksMapper.selectOne(worksQueryWrapper);
        if (works == null){
            throw new RuntimeException("works不存在");
        }

        worksMapper.deleteById(id);
        QueryWrapper<Attr> attrQueryWrapper = new QueryWrapper<>();
        attrQueryWrapper.eq("works_id", works.getId());
        attrMapper.delete(attrQueryWrapper);
    }

    @Override
    public void update(AdminWorksDTO dto){
        Works works=new Works();
        BeanUtils.copyProperties(dto,works);

        QueryWrapper<Producer> pr=new QueryWrapper<>();
        pr.eq("name", dto.getProducerName());
        Producer one = producerMapper.selectOne(pr);
        if(one == null){
            Producer producer=new Producer();
            producer.setName(dto.getProducerName());
            int producerInsert = producerMapper.insert(producer);
            if(producerInsert != 1){
                throw new RuntimeException("producer插入失败");
            }
            QueryWrapper<Producer> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("name", dto.getProducerName());
            Producer producer1 = producerMapper.selectOne(queryWrapper1);
            works.setPid(producer1.getId());
        }else{
            works.setPid(one.getId());
        }


        worksMapper.updateById(works);

        //删除workId对应的attr
        QueryWrapper<Attr> attrQueryWrapper = new QueryWrapper<>();
        attrQueryWrapper.eq("works_id", works.getId());
        attrMapper.delete(attrQueryWrapper);

        //新增新的attr
        List<String> tagList = dto.getTagList();
        if (tagList != null && !tagList.isEmpty()){
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
        }


    }


    private List<Tagcategory> getParent_cid(List<Tagcategory> selectList,Long catelogId){
        List<Tagcategory> tagcategoryList =
                selectList.stream().filter(item -> item.getCatelogId().equals(catelogId)).collect(Collectors.toList());
        return tagcategoryList;
    }
}
