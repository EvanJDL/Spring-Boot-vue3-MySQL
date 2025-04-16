package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //    这里的 @Select 注解是告诉 MyBatis 执行一个 SQL 查询，
//    查询结果将自动映射到 User 类的字段
    @Select("select * from user where username = #{username}")
    User findByUserName(String userName);

    @Insert("INSERT INTO user(username,password,create_time,update_time) " +
"VALUES(#{userName},#{encrypted},NOW(),NOW())")
    void add(String userName, String encrypted);
}
