package com.suke.servlet;

import com.suke.service.UserService;
import com.suke.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 账户登录
* */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置请求字符集为utf-8
        req.setCharacterEncoding("utf-8");
        //2.获取请求参数method,根据值调用不同方法处理业务
        String method = req.getParameter("method");
        if (method.equals("login")) {
            this.login(req, resp);
        }
    }

    //登录方法
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取账号和密码
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        //2.调用service的方法处理登录
        boolean result = userService.checkUser(userName, passWord);
        if (result) {
            //true表示登录成功
            System.out.println("登录成功！");
            //将用户信息保存在session域对象中
            req.getSession().setAttribute("info", userName);
            //转发跳转成功页面
            req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
        } else {
            //false登录失败，返回登录页面
            req.setAttribute("info", "账号密码不匹配!");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
