package com.vijeesh;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(servletNames= {"cookie", "first"})
public class MyLoginFilter  implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest re = (HttpServletRequest) request;
		HttpSession s= re.getSession();
		if (s.getAttribute("email")!=null) {
			chain.doFilter (request, response);
		}	
		else {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);		   
		}
	
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
