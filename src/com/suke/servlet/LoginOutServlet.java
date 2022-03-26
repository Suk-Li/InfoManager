package com.suke.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 用户退出
 * */
@WebServlet(name = "LoginOutServlet", urlPatterns = "/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
	}
}