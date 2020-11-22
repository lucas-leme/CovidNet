package net.javaguides.usermanagement.web;

import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
import java.sql.SQLException;

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

/*import net.javaguides.usermanagement.dao.ProntuarioDAO;
import net.javaguides.usermanagement.model.Leito;
import net.javaguides.usermanagement.model.Prontuario;*/

/**
 * prontuarioServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Hugo Martins
 */

@WebFilter("/semi-compilado/*")
public class FiltroGeral extends HttpServlet implements Filter
{
	private static final long serialVersionUID = 1L;
	//private ProntuarioDAO prontuarioDAO;
	
	private ServletContext context;
	
	
	/*public void init() 
	{
		//prontuarioDAO = new ProntuarioDAO();
		System.out.println("init(void) (filtrogeral)");
	}*/
	private void showMainPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		System.out.println("Showing main page (filtrogeral)");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/homePage.jsp");
		dispatcher.forward(request, response);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		//String action = request.getServletPath();
		request.setCharacterEncoding("UTF-8");

		String action = request.getServletPath();
		System.out.println("acao: " + action);
		try {
			switch (action) {
				case "/leitos":
					System.out.println("leitos no get do filtro geral");
					break;
				default:
					System.out.println("showing main page (filtro geral)");
					showMainPage(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestURI = request.getRequestURI();
        
        System.out.println("doFilter(filtrogeral): requestURI: " + requestURI);

        if (requestURI.startsWith("/leitos")) {
            //String toReplace = requestURI.substring(requestURI.indexOf("/Dir_My_App"), requestURI.lastIndexOf("/") + 1);
            //String newURI = requestURI.replace(toReplace, "?Contact_Id=");
        	System.out.println("filtrando o /leitos");
            req.getRequestDispatcher("/leito").forward(req, res);
        }
        else if (requestURI.startsWith("/prontuarios")) {
            //String toReplace = requestURI.substring(requestURI.indexOf("/Dir_My_App"), requestURI.lastIndexOf("/") + 1);
            //String newURI = requestURI.replace(toReplace, "?Contact_Id=");
        	System.out.println("filtrando o /prontuarios");
            req.getRequestDispatcher("/leito").forward(req, res);
        }
        else if (requestURI.startsWith("/relatorios")) {
            //String toReplace = requestURI.substring(requestURI.indexOf("/Dir_My_App"), requestURI.lastIndexOf("/") + 1);
            //String newURI = requestURI.replace(toReplace, "?Contact_Id=");
        	System.out.println("filtrando o /relatorios");
            req.getRequestDispatcher("/relatorios").forward(req, res);
   
        }
        else if (requestURI.startsWith("/semi-compilado")) {
            //String toReplace = requestURI.substring(requestURI.indexOf("/Dir_My_App"), requestURI.lastIndexOf("/") + 1);
            //String newURI = requestURI.replace(toReplace, "?Contact_Id=");
        	System.out.println("filtrando o projeto todo\n\n\n\n\n");
            req.getRequestDispatcher("/").forward(req, res);
   
        } else {
            chain.doFilter(req, res);
        }
    }
	/*public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("doing filter (filtrogeral)");		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		System.out.println("uri = " + uri);
		//this.context.log("Requested Resource::"+uri);
		
		HttpSession session = req.getSession(false);
		
		if(session == null && !(uri.endsWith("html") || uri.endsWith("LoginServlet"))){
			//this.context.log("Unauthorized access request");
			res.sendRedirect("login.html");
		}else{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}*/

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init(filterconfig) Filtro geral");
		this.context = fConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}
	
}