package com.suke.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/*
    JDBC工具类
 */
public class JDBCUtils {
    // 声明驱动的路径
    static String driverClass;
    static String url;
    static String user;
    static String password;

    /*
        静态代码块只会在加载class文件的时候执行一次，
        将注册驱动的代码由静态代码块来实现
     */
    static {
        // 创建属性集对象
        Properties prop = new Properties();
        // 将文件中的数据读取到属性集中
        try {
            //prop.properties
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("prop.properties");
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取key对应的value
        driverClass = prop.getProperty("driverClassName");
        url = prop.getProperty("urlName");
        user = prop.getProperty("userName");
        password = prop.getProperty("passwordName");
        try {
            // 加载驱动
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
        将获取资源的方法进行封装: Connection连接接口对象
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // 获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /*
        封装方法，用于释放资源
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 调用已经实现功能的方法直接使用
        close(connection, statement);
    }

    /*
        重载一个释放资源的代码，用来释放两个资源
     */
    public static void close(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
