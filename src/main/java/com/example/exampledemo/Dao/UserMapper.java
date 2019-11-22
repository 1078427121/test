package com.example.exampledemo.Dao;

import com.example.exampledemo.bean.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from `user` where uid=#{uid}")
    user getUser(int uid);

    @Insert("insert into user values(#{uid},#{sessionid})")
    void insertUser(user u);
}
