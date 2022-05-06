package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.entities.Tagcategory;
import com.gdufe.cs.works.mapper.TagcategoryMapper;
import com.gdufe.cs.works.service.TagcategoryService;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 20:25
 **/
@Service
public class TagcategoryServiceImpl extends ServiceImpl<TagcategoryMapper, Tagcategory> implements TagcategoryService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<Tagcategory> showCateTree() {
       //查询出所有分类
        List<Tagcategory> tagcategories = super.baseMapper.selectList(null);

        List<Tagcategory> levelMenus = tagcategories.stream()
                .filter(tagcategory -> tagcategory.getCatelogId() == 0)
                .map((menu) -> {
                    menu.setChildren(getChildrens(menu,tagcategories));
                    return menu;
                })
                .collect(Collectors.toList());

        return levelMenus;
    }

    /**
     * 级联更新所有关联的数据
     *
     * @CacheEvict:失效模式
     * @CachePut:双写模式，需要有返回值
     * 1、同时进行多种缓存操作：@Caching
     * 2、指定删除某个分区下的所有数据 @CacheEvict(value = "category",allEntries = true)
     * 3、存储同一类型的数据，都可以指定为同一分区
     * @param tagcategory
     */
    @CacheEvict(value = "tagCategory",allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCate(Tagcategory tagcategory) {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("tagCatalogJson-lock");
        //创建写锁
        RLock rLock = readWriteLock.writeLock();

        try {
            rLock.lock();
            this.baseMapper.updateById(tagcategory);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }

        //同时修改缓存中的数据
        //删除缓存,等待下一次主动查询进行更新
    }

    @Override
    public void deleteMenuByIds(List<Long> deleteIds) {
          this.baseMapper.deleteBatchIds(deleteIds);
    }

    private List<Tagcategory> getChildrens(Tagcategory root, List<Tagcategory> all) {

        List<Tagcategory> children = all.stream().filter(tagcategory -> {
            return tagcategory.getCatelogId().equals(root.getId());
        }).map(tagcategory -> {
            //递归
            tagcategory.setChildren(getChildrens(tagcategory,all));
            return tagcategory;
        }).collect(Collectors.toList());

        return children;
    }

    private List<Tagcategory> getParent_cid(List<Tagcategory> selectList,Long catelogId){
        List<Tagcategory> tagcategoryList =
                selectList.stream().filter(item -> item.getCatelogId().equals(catelogId)).collect(Collectors.toList());
        return tagcategoryList;
    }
}
