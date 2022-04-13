package com.gdufe.cs.admin.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.admin.entity.Director;
import com.gdufe.cs.admin.mapper.DirectorMapper;
import org.springframework.stereotype.Service;

@Service

public class DirectorService extends ServiceImpl<DirectorMapper, Director>{

    public boolean saveDirector(Director director){
        return saveOrUpdate(director);
    }


//    @Autowired
//   private com.example.demo.mapper.DirectorMapper DirectorMapper;
//   public int save(Director director){

//       if(director.getDirectorId() == null){//movie没有id，则表示是新增
//           return DirectorMapper.insert(director);
//       }else{//否则为更新
//           return DirectorMapper.update(director);
//       }


//   }
}
