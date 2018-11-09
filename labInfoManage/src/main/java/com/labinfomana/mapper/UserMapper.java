package com.labinfomana.mapper;

import com.labinfomana.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findByName(@Param("username") String username);

    @Select("select * from user where username=#{username} and password=#{password}")
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
