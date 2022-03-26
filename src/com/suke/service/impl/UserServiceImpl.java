package com.suke.service.impl;

import com.suke.dao.UserDao;
import com.suke.dao.impl.UserDaoImpl;
import com.suke.entity.User;
import com.suke.service.UserService;
import com.suke.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean checkUser(String userName, String passWord) {
        // 连接对象
        Connection connection = null;
        try {
            //1.获取数据库连接
            connection = JDBCUtils.getConnection();
            //2.调用dao方法查询数据(根据用户名和密码查找用户信息)
            List<User> users = userDao.findUserByUserNameAndPassWord(connection, userName, passWord);
            //3.根据查询结果返回成功失败标志
            if (users.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.close(connection, null);
        }
    }
}
