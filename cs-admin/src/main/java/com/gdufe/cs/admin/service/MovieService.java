package com.gdufe.cs.admin.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.admin.entity.Movie;
import com.gdufe.cs.admin.mapper.MovieMapper;
import org.springframework.stereotype.Service;

@Service

public class MovieService extends ServiceImpl<MovieMapper,Movie>{
    public boolean saveMovie(Movie movie){
        return saveOrUpdate(movie);
    }

//    @Autowired
//    private MovieMapper MovieMapper;
//    public int save(Movie movie){
//
//        if(movie.getMovie_id() == null){//movie没有id，则表示是新增
//            return MovieMapper.insert(movie);
//        }else{//否则为更新
//            return MovieMapper.update(movie);
//        }
//
//
//    }
}
