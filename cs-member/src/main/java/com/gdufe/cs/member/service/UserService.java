package com.gdufe.cs.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.cs.entities.User;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/8 21:09
 **/
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.member.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service

public class UserService extends ServiceImpl<UserMapper, User>{
    public boolean saveUser(User user){
        return saveOrUpdate(user);
    }

//    @Autowired
//    private com.example.demo.mapper.UserMapper UserMapper;
//    public int save(User user){
//
//        if(user.getId() == null){//user没有id，则表示是新增
//            return UserMapper.insert(user);
//        }else{//否则为更新
//            return UserMapper.update(user);
//        }
//
//
//    }
}
