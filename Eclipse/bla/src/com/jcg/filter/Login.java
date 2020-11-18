package com.jcg.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Login implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chainObj) throws IOException, ServletException {

		RequestDispatcher rdObj = null;
		PrintWriter out = resp.getWriter();
		out.write("<html><body><div id='servletResponse' style='text-align: center;'>");

		String password = req.getParameter("password");
		System.out.println("Password Is?= " + password);

		if(password != null && password.equals("admin")) {
			// Send Request To Next Resource
			chainObj.doFilter(req, resp);
		} else {
			out.print("<p id='errMsg' style='color: red; font-size: larger;'>Username Or Password Is Invalid. Please Try Again ....!</p>");  
			rdObj = req.getRequestDispatcher("/index.jsp");  
			rdObj.include(req, resp);  
		}

		out.write("</div></body></html>");
		out.close();
	}

	public void destroy() {	}
}