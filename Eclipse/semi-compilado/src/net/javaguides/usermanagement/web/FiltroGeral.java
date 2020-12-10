package net.javaguides.usermanagement.web;

import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.javaguides.usermanagement.dao.LoginDAO;
import net.javaguides.usermanagement.model.User;

/**
 * prontuarioServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Hugo Martins
 */

@WebFilter("/*")
public class FiltroGeral extends HttpServlet implements Filter
{
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDAO;

	//private String teste = "";
	//private User user;

	private ServletContext context;
	
	public FiltroGeral()
	{
		super();
		loginDAO = new LoginDAO();
		
		//user = null;
	}

	@Override
    public void doFilter(ServletRequest servReq, ServletResponse servRes, FilterChain chain) throws ServletException, IOException {

	    HttpServletRequest req = (HttpServletRequest) servReq;
	    HttpServletResponse resp = (HttpServletResponse) servRes;
	    
	    req.setCharacterEncoding("UTF-8");
	   
	    String path = req.getRequestURI().substring(req.getContextPath().length());
	    String easyNext = path.substring(1);
	    String qs = req.getQueryString();
	    if (qs != null && qs.length() > 0) easyNext += "?" + qs;
	    req.setAttribute("easyNext", easyNext);
	    req.setAttribute("fullPath", path);
	    
	    System.out.println("FILTRANDO");
	    
	    System.out.println("requisição a " + path);

	    //System.out.println("\nuser: " + user + "\n");
	    boolean logged = false;//, goLogin = true;
	    boolean validate = false;
	    
	    try{
	    	validate = Boolean.parseBoolean(servReq.getParameter("validate"));//(boolean) servReq.getAttribute("validate");
	    	System.out.println("validate nao e null: " + validate);
	    }
	    catch(NullPointerException npe)
	    {
	    	System.out.println("validate e null");
	    }
	    
	    try{
	    	logged = (boolean) servReq.getAttribute("logged");
	    	System.out.println("logged nao e null: " + logged);
	    }
	    catch(NullPointerException npe)
	    {
	    	System.out.println("logged ainda e null");
	    }
	    
	    System.out.println("\nlogged: " + logged + "; validate: " + validate);
	    
	    if(!logged && !validate)
	    {
	    	req.getRequestDispatcher("/login").forward(req, resp);
	    	//resp.sendRedirect(req.getContextPath() + "/login/signin");
	    	//return;
	    }
	    else if (path.equals("")) resp.sendRedirect(req.getContextPath() + "/");
	    else if (path.equals("/")) showMainPage(req, resp);
	    else req.getRequestDispatcher(path).forward(req, resp); 
    }
	
	private void showMainPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("Showing main page");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/homePage.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init(filterconfig) Filtro geral");
		this.context = fConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}
	
}