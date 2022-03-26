package com.suke.service;

public interface UserService {
    //判断登录成功与否
    boolean checkUser(String userName, String passWord);
}
