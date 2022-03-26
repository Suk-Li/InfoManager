package com.suke.dao;

import com.suke.entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    //根据账号密码查询数据库，返回结果集
    List<User> findUserByUserNameAndPassWord(Connection connection, String userName, String passWord);
}
