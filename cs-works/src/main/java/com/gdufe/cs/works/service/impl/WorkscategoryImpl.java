package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.dto.WorkscategoryDTO;
import com.gdufe.cs.entities.Tagcategory;
import com.gdufe.cs.entities.Works;
import com.gdufe.cs.entities.Workscategory;
import com.gdufe.cs.works.mapper.TagcategoryMapper;
import com.gdufe.cs.works.mapper.WorkscategoryMapper;
import com.gdufe.cs.works.service.WorksService;
import com.gdufe.cs.works.service.WorkscategoryService;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/13 19:59
 **/
@Service
public class WorkscategoryImpl extends ServiceImpl<WorkscategoryMapper, Workscategory> implements WorkscategoryService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private TagcategoryMapper tagcategoryMapper;

    @Autowired
    private WorksService worksService;

    @Override
    public List<WorkscategoryDTO> showCateTree() {

        //查询出所有的作品形式
        List<Workscategory> workscategoryList = this.baseMapper.selectList(null);

        Set<Long> tagcatelogIds = workscategoryList.stream().map(workscategory -> workscategory.getTagcatelogId()).collect(Collectors.toSet());

        List<Tagcategory> tagcategories = new ArrayList<>();
        for (Long tagcatelogId : tagcatelogIds) {
            QueryWrapper<Tagcategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",tagcatelogId);
            Tagcategory tagcategory = tagcategoryMapper.selectOne(queryWrapper);
            tagcategories.add(tagcategory);
        }

        List<WorkscategoryDTO> workscategoryDTOS = new ArrayList<>();

        for (Tagcategory tagcategory : tagcategories) {
            WorkscategoryDTO workscategoryDTO = new WorkscategoryDTO();
            workscategoryDTO.setShowStatus(1);
            workscategoryDTO.setCatelogName(tagcategory.getTagName());
            workscategoryDTO.setTagcatelogId(0L);
            workscategoryDTO.setId(tagcategory.getId());

            workscategoryDTO.setChildren(getChildrens(tagcategory,workscategoryList));

            workscategoryDTOS.add(workscategoryDTO);
        }



        return workscategoryDTOS;
    }

    @CacheEvict(value = "worksCategory",allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCate(Workscategory workscategory) {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("worksCatalogJson-lock");
        //创建写锁
        RLock rLock = readWriteLock.writeLock();

        try {
            rLock.lock();
            this.baseMapper.updateById(workscategory);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }

        //同时修改缓存中的数据
        //删除缓存,等待下一次主动查询进行更新
    }

    private List<Workscategory> getChildrens(Tagcategory root, List<Workscategory> all) {

        List<Workscategory> children = all.stream().filter(workscategory -> {
            return workscategory.getTagcatelogId().equals(root.getId());
        }).collect(Collectors.toList());

        return children;
    }

    @Override
    public void deleteMenuByIds(List<Long> deleteIds) {

        this.baseMapper.deleteBatchIds(deleteIds);

        //找到含有该被删除的作品形式的作品，将其作品形式的id置为null
        for (Long deleteId : deleteIds) {
            QueryWrapper<Works> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("workscate_id",deleteId);
            Works works = worksService.getOne(queryWrapper);
            works.setWorkscateId(null);
        }

    }
}
