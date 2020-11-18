package com.jcg.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp);
	}

	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		resp.setContentType("text/html");

		// Building & Printing The HTML Response Code
		PrintWriter out = resp.getWriter();
		out.write("<html><body><div id='servletResponse' style='text-align: center;'>");
		out.write("<h2>Java Sevlet Filter Example</h2>");
		out.write("<p style='color: green; font-size: large;'>Welcome, Administrator!</p>");
		out.write("</div></body></html>");
		out.close();		
	}
}