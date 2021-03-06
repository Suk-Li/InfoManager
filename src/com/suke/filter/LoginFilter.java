package com.suke.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*.do")
public class LoginFilter implements Filter {

    public LoginFilter() {}

	@Override
    public void destroy() {}

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse)response;
		//获取上下文路径,如“/InfoManagerDemo”
		String ctxPath = request.getServletContext().getContextPath();
		//获取请求的地址路径,如“/InfoManagerDemo/LoginServlet”
		String url = req.getRequestURI();
        //将请求LoginServlet和login.jsp不进行拦截过滤
		if(url.contains("LoginServlet")) {
			chain.doFilter(request, response);
		}
		else {
			//如果有session，表示已经登录，则通过，否则跳转至登录页面
			if(req.getSession().getAttribute("info") != null) {
				chain.doFilter(request, response);
			}else {
				rep.sendRedirect(req.getContextPath());
			}
		}
	}


	@Override
    public void init(FilterConfig fConfig) throws ServletException {}
}
