package com.suke.servlet;

import com.suke.entity.Product;
import com.suke.service.ProductService;
import com.suke.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 修改产品
 * */
@WebServlet(name = "UpdateProductServlet", urlPatterns = "/UpdateProductServlet.do")
public class UpdateProductServlet extends HttpServlet {

	private ProductService productService = new ProductServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		try {
			Product product = productService.findProductByID(Integer.parseInt(pid));
			req.setAttribute("product", product);
			req.getRequestDispatcher("/jsp/product_modify.jsp").forward(req,resp) ;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}