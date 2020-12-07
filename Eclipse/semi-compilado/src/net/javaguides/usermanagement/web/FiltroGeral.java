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

@WebFilter("/*")
public class FiltroGeral extends HttpServlet implements Filter
{
	private static final long serialVersionUID = 1L;
	//private ProntuarioDAO prontuarioDAO;
	
	private ServletContext context;
	
	
	private void showMainPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("Showing main page");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/homePage.jsp");
		dispatcher.forward(request, response);
		
	}

	@Override
    public void doFilter(ServletRequest servReq, ServletResponse servRes, FilterChain chain) throws ServletException, IOException {

	    HttpServletRequest req = (HttpServletRequest) servReq;
	    HttpServletResponse resp = (HttpServletResponse) servRes;
	    
	    req.setCharacterEncoding("UTF-8");
	    
	    //String segredinho = WebUtils.extrairSegredoCodificado(req);
	    //Usuario logadoComo = tokenSeguroDAO.logado(segredinho);
	    //req.setAttribute("usuario", logadoComo);
	    //if (logadoComo != null) logadoComo.acessou();
	    
	    //req.setAttribute("forum", this.forum);
	    //req.setAttribute("tokens", this.tokenSeguroDAO);
	    //req.setAttribute("usuarios", this.usuarioDAO);
	    
	   
	    String path = req.getRequestURI().substring(req.getContextPath().length());
	    String easyNext = path.substring(1);
	    String qs = req.getQueryString();
	    if (qs != null && qs.length() > 0) easyNext += "?" + qs;
	    req.setAttribute("easyNext", easyNext);
	    req.setAttribute("fullPath", path);
	    
	    System.out.println("FILTRANDO");
	    
	    System.out.println("requisição a \"" + path);
	    if (path.equals("")) {
	      resp.sendRedirect(req.getContextPath() + "/");
	    } else if (path.equals("/")) {
	      showMainPage(req, resp);
	    } /*else if (path.startsWith(PaginaJSP.parent)) {
	      // servir JSP...
	      chain.doFilter(req, resp);
	    }*/ else {
	      // passar adiante (e.g. servlet controlador, ou arquivo estático)
	      req.getRequestDispatcher(path).forward(req, resp); 
	    }
    }


	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init(filterconfig) Filtro geral");
		this.context = fConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}
	
}