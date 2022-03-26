package com.suke.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 新增产品
 * */
@WebServlet(name = "AddProductServlet", urlPatterns = "/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 直接转发
		req.getRequestDispatcher("/jsp/product_insert.jsp").forward(req,resp) ;
	}
}
