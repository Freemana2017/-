package com.labinfomana.service;

import com.labinfomana.entity.User;
import com.labinfomana.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    public UserMapper userMapper;
    @Override
    public User findByName(String name) {
        User user=userMapper.findByName(name);
        return user;
    }

    @Override
    public boolean verifyUser(User user) {
       User userVery = userMapper.findByUsernameAndPassword(user.getUsername(),user.getPassword());
       System.out.println("数据库获取用户名"+userVery.getUsername());
        if(userVery!=null){
          return true;
      }else{
          return false;
      }

    }


    @Override
    public void insertUser(User user) {

    }
}
