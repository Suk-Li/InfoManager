package com.suke.servlet;

import com.suke.common.Pager;
import com.suke.dao.IProductDAO;
import com.suke.entity.Product;
import com.suke.service.ProductService;
import com.suke.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/*
 * 查询产品
 * */
@WebServlet(name = "SearchProductServlet", urlPatterns = "/SearchProductServlet.do")
public class SearchProductServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求字符集为utf-8
        req.setCharacterEncoding("utf-8");
        //获取可能传送过来的查询条件和页码，但不一定会传送过来。
        //比如在点击左边菜单的“查询产品"时，是没有传送查询条件和页码的，所以后面代码中要判断是否有这些数据
        String search_name = req.getParameter("search_name");
        String currentPage = req.getParameter("currentPage");
        //判断是否接收到页码，如果没有，则默认设置为第一页
        int iCurrentPage = currentPage == null ? 1 : Integer.parseInt(currentPage);
        Pager pager = null;
        ArrayList<Product> productList = null;

        try {
            //如果没有查询条件提交上来，则查询全部，否则基于条件查询
            if (search_name == null) {
                int count = productService.getCount();
                //构建pager对象，设置当前页、总记录数、每页显示条数
                pager = new Pager(iCurrentPage, count, 5);
                productList = productService.findProduct(pager.getStartResults(), pager.getPageSize());
            } else {
                int count = productService.getCount(search_name);
                pager = new Pager(iCurrentPage, count, 5);
                productList = productService.findProduct(search_name, pager.getStartResults(), pager.getPageSize());
            }

            //如果有条件，则将条件设置到请求中，传送至jsp页面中，保证jsp的页面在刷新后，查询条件的值一直保留在文本框。
            if (search_name != null) {
                req.setAttribute("search_name", search_name);
            }
            //将Pager对象和查询出的结果设置到请求中，传送至jsp页面
            req.setAttribute("pager", pager);
            req.setAttribute("productList", productList);
            req.getRequestDispatcher("/jsp/product.jsp").forward(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}