package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.dto.*;
import com.gdufe.cs.entities.*;
import com.gdufe.cs.es.esModel;
import com.gdufe.cs.works.cache.TagCache;
import com.gdufe.cs.works.enums.CommentTypeEnum;
import com.gdufe.cs.works.mapper.*;
import com.gdufe.cs.works.service.*;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.math.ec.custom.sec.SecT113Field;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
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

    @Autowired
    private WorkscategoryMapper workscategoryMapper;

    @Autowired
    private TagcategoryMapper tagcategoryMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProducerMapper producerMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ThreadPoolExecutor executor;

    @Override
    public IndexDTO indexPage(Integer pageCurrent,Integer pageSize) throws ExecutionException, InterruptedException {
        IndexDTO indexDTO = new IndexDTO();
        Page<Works> page = new Page<>(pageCurrent,pageSize);

        CompletableFuture<Void> movieFuture = CompletableFuture.runAsync(() -> {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("catelog_id", 40);
            queryWrapper1.eq("status", 1);
            worksMapper.selectPage(page, queryWrapper1);
            indexDTO.setPageSize(pageSize);
            indexDTO.setPageCurrent(pageCurrent);

            indexDTO.setMovieTotal(page.getTotal());
            indexDTO.setMovie(page.getRecords());
            indexDTO.setMoviePages(page.getPages());
        }, executor);

        CompletableFuture<Void> musicFuture = CompletableFuture.runAsync(() -> {
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("catelog_id", 41);
            queryWrapper2.eq("status", 1);
            worksMapper.selectPage(page, queryWrapper2);
            indexDTO.setMusicTotal(page.getTotal());
            indexDTO.setMusic(page.getRecords());
            indexDTO.setMusicPages(page.getPages());
        }, executor);


        CompletableFuture<Void> bookFuture = CompletableFuture.runAsync(() -> {
            QueryWrapper queryWrapper3 = new QueryWrapper();
            queryWrapper3.eq("catelog_id", 42);
            queryWrapper3.eq("status", 1);
            worksMapper.selectPage(page, queryWrapper3);
            indexDTO.setBookTotal(page.getTotal());
            indexDTO.setBook(page.getRecords());
            indexDTO.setBookPages(page.getPages());
        }, executor);

        CompletableFuture.allOf(movieFuture,musicFuture,bookFuture).get();
        return indexDTO;

    }




    @Override
    public SearchParamDTO showSearchParam(Long catelogId) throws ExecutionException, InterruptedException {

        SearchParamDTO searchParamDTO = new SearchParamDTO();

        CompletableFuture<Void> tagListFuture = CompletableFuture.runAsync(() -> {
            List<Tagcategory> tagcategories = tagcategoryMapper.selectList(null);
            List<Tagcategory> tagList = getParent_cid(tagcategories, catelogId);
            searchParamDTO.setTagList(tagList);

        }, executor);


        CompletableFuture<Void> worksCateFuture = CompletableFuture.runAsync(() -> {
            QueryWrapper<Workscategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tagcatelog_id", catelogId);
            List<Workscategory> workscategories = workscategoryMapper.selectList(queryWrapper);
            searchParamDTO.setWorkscategories(workscategories);
        }, executor);

        CompletableFuture<List<Works>> workListInfo = CompletableFuture.supplyAsync(() -> {
            QueryWrapper<Works> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("catelog_id", catelogId);
            List<Works> worksList = worksMapper.selectList(queryWrapper1);

            return worksList;
        }, executor);

        CompletableFuture<Void> countryFuture = workListInfo.thenAcceptAsync((res) -> {
            Set<String> country = res.stream().map(works ->
                    works.getCountry()
            ).collect(Collectors.toSet());
            searchParamDTO.setCountry(country);
        }, executor);


        CompletableFuture<Void> createTimeFuture = workListInfo.thenAcceptAsync((res) -> {
            Set<Long> createTime = res.stream().map(works ->
                    works.getCreateTime()
            ).collect(Collectors.toSet());
            searchParamDTO.setCreateTime(createTime);
        }, executor);


        //等到所有任务都完成
        CompletableFuture.allOf(tagListFuture,worksCateFuture,countryFuture,createTimeFuture).get();

        return searchParamDTO;
    }

    @Override
    public WorksDTO showTargetWorks(Long id) throws ExecutionException, InterruptedException {
        WorksDTO worksDTO = new WorksDTO();

        CompletableFuture<Works> worksFuture = CompletableFuture.supplyAsync(() -> {
            Works works = worksMapper.selectById(id);
            List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.MOVIE);//罗列影评
            QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",id);
            List<Article> articles = articleService.list(queryWrapper);
            List<ArticleDTO> articleDTOList = articleService.listArticles(articles);
            worksDTO.setId(works.getId());
            worksDTO.setPid(works.getPid());
            worksDTO.setName(works.getName());
            worksDTO.setPath(works.getPath());
            worksDTO.setIntro(works.getIntro());
            worksDTO.setCreatTime(works.getCreateTime());
            worksDTO.setScore(works.getScore());
            worksDTO.setCountry(works.getCountry());
            worksDTO.setLikeCount(works.getLikeCount());
            worksDTO.setCommentCount(works.getCommentCount());
            worksDTO.setCommentDTOList(comments);
            worksDTO.setArticleDTOList(articleDTOList);

            return works;
        }, executor);

        CompletableFuture<Void> producerFuture = worksFuture.thenAcceptAsync((res) -> {
            if (res.getPid() != null) {
                Producer producer = producerMapper.selectById(res.getPid());
                worksDTO.setProducerName(producer.getName());
            }
        }, executor);


        CompletableFuture<Void> worksCateFuture = worksFuture.thenAcceptAsync((res) -> {
            //查出该作品的形式
            // Long catelogId = res.getCatelogId();
            Long workscateId = res.getWorkscateId();
            QueryWrapper<Workscategory> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("id", workscateId);
            Workscategory workscategory = workscategoryMapper.selectOne(queryWrapper1);
            //  String catelogName = workscategory.getCatelogName();

        }, executor);


        CompletableFuture<Void> tagListFuture = worksFuture.thenAcceptAsync((res) -> {
            // 查出作品的标签
            QueryWrapper<Attr> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("works_id", res.getId());
            List<Attr> attrs = attrMapper.selectList(queryWrapper2);
            List<Long> tagIds = attrs.stream().map(attr -> attr.getTagId()).collect(Collectors.toList());

            List<String> tagList = new ArrayList<>();
            for (Long tagId : tagIds) {
                QueryWrapper<Tagcategory> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("id", tagId);
                Tagcategory tagcategory = tagcategoryMapper.selectOne(queryWrapper3);

                tagList.add(tagcategory.getTagName());
            }
            worksDTO.setTagList(tagList);

        }, executor);

        //等到所有任务都完成
        CompletableFuture.allOf(worksCateFuture,producerFuture,tagListFuture).get();



        return worksDTO;
    }


    private List<Tagcategory> getParent_cid(List<Tagcategory> selectList,Long catelogId){
        List<Tagcategory> tagcategoryList =
                selectList.stream().filter(item -> item.getCatelogId().equals(catelogId)).collect(Collectors.toList());
        return tagcategoryList;
    }

}
