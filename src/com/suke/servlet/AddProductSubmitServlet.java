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
 * 新增产品
 * */
@WebServlet(name = "AddProductSubmitServlet", urlPatterns = "/AddProductSubmitServlet.do")
@MultipartConfig
public class AddProductSubmitServlet extends HttpServlet {

	private ProductService productService = new ProductServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置请求字符集为utf-8
		req.setCharacterEncoding("UTF-8");
		//从product_insert.jsp获取产品参数信息
		String name = req.getParameter("name");
		String note = req.getParameter("note");
		String price = req.getParameter("price");
		String amount = req.getParameter("amount");
		String pic = null;
		//获取/pic的物理路径
		String path = getServletContext().getRealPath("/pic");
		//获取文件
		Part part = req.getPart("pic");
		//判断是否有上传文件
		if(part.getSize() != 0) {
			String filename = part.getSubmittedFileName();
			String str[] = filename.split("\\.");
			//获取文件的扩展名,如png\jpg等
			String suffix = str[str.length-1];
			//生成唯一编码，使用唯一编码作为上传图片的名称，避免重复
			UUID uuid = UUID.randomUUID();
			pic = uuid.toString()+"."+suffix;
			String filePath = path + "\\" + pic;
			//写入文件
			part.write(filePath);
		}

		Product product = new Product();
		product.setName(name);
		product.setNote(note);
		product.setPrice(Float.parseFloat(price));
		product.setAmount(Integer.parseInt(amount));
		product.setPic(pic);

		try {
			productService.addProduct(product);
			req.getRequestDispatcher("SearchProductServlet").forward(req,resp) ;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}