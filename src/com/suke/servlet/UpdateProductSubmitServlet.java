package com.suke.servlet;
import com.suke.dao.IProductDAO;
import com.suke.entity.Product;
import com.suke.service.ProductService;
import com.suke.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

/*
 * 修改产品
 * */
@WebServlet(name = "UpdateProductSubmitServlet", urlPatterns = "/UpdateProductSubmitServlet")
@MultipartConfig
public class UpdateProductSubmitServlet extends HttpServlet {

	private ProductService productService = new ProductServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置请求字符集为utf-8
		req.setCharacterEncoding("UTF-8");
		//从product_modify.jsp获取产品参数信息
		String name = req.getParameter("name");
		String note = req.getParameter("note");
		String price = req.getParameter("price");
		String amount = req.getParameter("amount");
		String pid = req.getParameter("pid");
		String pic = req.getParameter("pic");

		//获取物理路径
		String path = getServletContext().getRealPath("/pic");
		//获取上传的文件
		Part part = req.getPart("newpic");

		String newpic = null;
		if(part.getSize() != 0) {
			String filename = part.getSubmittedFileName();
			String str[] = filename.split("\\.");
			String suffix = str[str.length-1];
			UUID uuid = UUID.randomUUID();
			newpic = uuid.toString()+"."+suffix;
			String filePath = path + "\\" + newpic;
			//写入文件
			part.write(filePath);
		}

		Product product = new Product();
		product.setPid(Integer.parseInt(pid));
		product.setName(name);
		product.setNote(note);
		product.setPrice(Float.parseFloat(price));
		product.setAmount(Integer.parseInt(amount));

		//如果有新上传的图片，则使用新的，否则使用原来的
		if(newpic != null)
			product.setPic(newpic);
		else
			product.setPic(pic);

		try {
			productService.updateProduct(product);
			req.getRequestDispatcher("SearchProductServlet").forward(req,resp) ;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}