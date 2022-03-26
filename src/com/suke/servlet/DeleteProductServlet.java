package com.suke.servlet;
import com.suke.service.ProductService;
import com.suke.service.impl.ProductServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 删除产品
 * */
@WebServlet(name = "DeleteProductServlet", urlPatterns = "/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {

	private ProductService productService = new ProductServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		try {
			productService.deleteProduct(Integer.parseInt(pid));
			req.getRequestDispatcher("SearchProductServlet").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}