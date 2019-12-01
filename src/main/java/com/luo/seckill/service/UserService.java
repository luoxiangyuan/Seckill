package com.luo.seckill.service;

import com.luo.seckill.bean.Store;
import com.luo.seckill.bean.User;
import com.luo.seckill.dao.StoreMapper;
import com.luo.seckill.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    StoreMapper storeMapper;

    public User getUser(String username){
        return userMapper.selectByPrimaryKey(username);
    }

    public Store getStore(String tel){
        return storeMapper.selectByTel(tel);
    }
}
