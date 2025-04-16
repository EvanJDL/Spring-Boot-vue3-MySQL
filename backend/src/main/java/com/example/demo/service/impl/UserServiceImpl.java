package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String userName){
        User u = userMapper.findByUserName(userName);
        return u;
    }

    @Override
    public void register(String userName, String password){
        String encrypted = MD5Util.md5(password);
        userMapper.add(userName,encrypted);
    }
}
