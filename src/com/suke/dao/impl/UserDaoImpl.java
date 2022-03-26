package com.suke.dao.impl;

import com.suke.dao.UserDao;
import com.suke.entity.User;
import com.suke.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    /*
     * 根据账号密码查询数据库，返回结果集
     * */
    @Override
    public List<User> findUserByUserNameAndPassWord(Connection connection, String userName, String passWord) {
        ArrayList<User> userList = new ArrayList<>();
        // 发送sql语句对象
        PreparedStatement statement = null;
        try {
            connection = JDBCUtils.getConnection();
            // 获取Statement对象
            statement = connection.prepareStatement("SELECT * FROM users WHERE userid = ? AND password = ?");
            //第一个？内容: 设置登录页面输入的账户名userName
            statement.setString(1, userName);
            //第二个？内容: 设置登录页面输入的密码passWord
            statement.setString(2, passWord);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                //columnIndex获取第1列id信息
                int id = rs.getInt(1);
                String userid = rs.getString(2);
                String name = rs.getString(3);
                String password = rs.getString(4);
                user.setId(id);
                user.setUserid(userid);
                user.setName(name);
                user.setPassword(password);
                //列表添加用户信息
                userList.add(user);
            }
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            return userList;
        } finally {
            JDBCUtils.close(null, statement, null);
        }
    }
}
