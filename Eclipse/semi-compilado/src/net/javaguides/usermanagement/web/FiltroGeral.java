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
import javax.servlet.http.Cookie;
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
	private boolean logged;
	
	//private String teste = "";
	//private User user;

	private ServletContext context;
	
	public FiltroGeral()
	{
		super();
		loginDAO = new LoginDAO();
		
		
		System.out.println("INCIANDO FILTRO");
		logged = false;
		//user = null;
	}

	@Override
    public void doFilter(ServletRequest servReq, ServletResponse servRes, FilterChain chain) throws ServletException, IOException {

	    HttpServletRequest req = (HttpServletRequest) servReq;
	    HttpServletResponse resp = (HttpServletResponse) servRes;
	    
	    req.setCharacterEncoding("UTF-8");
	    
	    Cookie c = new Cookie("TESTE", "123");
	    ((HttpServletResponse) servRes).addCookie(c);
	    
	   
	    String path = req.getRequestURI().substring(req.getContextPath().length());
	    String easyNext = path.substring(1);
	    String qs = req.getQueryString();
	    if (qs != null && qs.length() > 0) easyNext += "?" + qs;
	    req.setAttribute("easyNext", easyNext);
	    req.setAttribute("fullPath", path);
	    
	    System.out.println("FILTRANDO");
	    
	    System.out.println("requisição a " + path);

	    //System.out.println("\nuser: " + user + "\n");
	    //boolean logged = false;//, goLogin = true;
	    //boolean validate = false;
	    
	    if(!this.logged) {
		   
		    	//logged = Boolean.parseBoolean(req.getParameter("logged"));//(boolean) servReq.getAttribute("validate");
		    	//System.out.println("logged1: " + validate);
		    	
		    	Cookie[] cookies = req.getCookies();	
		    	for(Cookie cookie : cookies)
		    	{
		    		System.out.println(cookie.getName() + " : " + cookie.getValue());
		    		if(cookie.getName().equals("autorizacao"))
		    		{
		    			System.out.println("Achamos cookie de autorizacao");
		    			String value = cookie.getValue();
		    			if(value != null)
	    				{
		    				if(value.equals("medico")) this.logged = true;
		    				//else this.logged = false;
	    				}
		    		}
		    	}
	    }
		    	
    	//this.logged = (boolean) req.getAttribute("logged");
    	
    	System.out.println("logged2: " + this.logged);
 
		    
	    boolean achouCookie = false;
		
    	/*Cookie[] cookies = req.getCookies();	
    	System.out.println("COOKIES:{");
    	if(cookies != null) {
	    	for(Cookie cookie : cookies)
	    	{
	    		System.out.println("\t" + cookie.getName() + " : " + cookie.getValue());
	    		if(cookie.getName().equals("autorizacao"))
	    		{
	    			String value = cookie.getValue();
	    			if(value != null)
					{
	        			achouCookie = true;
	        			System.out.println("achou o cookieEBom");
	        			
	    				if(value.equals("medico")) this.logged = true;
	    				else this.logged = false;
					}
	    		}
		    	
		    }
    	}*/
    	System.out.println("}\n");
    
	    
	    System.out.println("\nlogged: " + this.logged);// + "; validate: " + validate);
	    //if(this.logged) logged = false;
	    
	    if(!this.logged && !(path.equals("/login/signin")))
	    {
	    	req.getRequestDispatcher("/login").forward(req, resp);
	    	//showFormSignin(request, response);
	    	
	    	//resp.sendRedirect(req.getContextPath() + "/login/signin");
	    	//return;
	    }
	    else if (path.equals("")) resp.sendRedirect(req.getContextPath() + "/");
	    else if (path.equals("/")) showMainPage(req, resp);
	    else req.getRequestDispatcher(path).forward(req, resp); 
    }
	
	/*private void showFormSignin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("\nFORM DE SIGNIN");
		
		//if(!logged) request.setAttribute("validate", true);
		request.setAttribute("logged", true);
		request.setAttribute("validate", true);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
		
		System.out.println("Se isso aqui so printar depoisque apertar obotao, tudo certo");
	}

	private void doSignin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String psswd = request.getParameter("psswd");
		
		System.out.println("\nemail: " + email + "; senha: " + psswd);
		
		if(true)
		{
			System.out.println("\nFazendo o signin");
			//logged = true;
			
			request.setAttribute("logged", true);
		}
		
		response.sendRedirect("/");
	}
	*/
	
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