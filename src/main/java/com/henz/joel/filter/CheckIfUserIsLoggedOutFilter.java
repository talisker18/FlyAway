package com.henz.joel.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CheckIfUserIsLoggedOutFilter
 */
public class CheckIfUserIsLoggedOutFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckIfUserIsLoggedOutFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest requestCasted = (HttpServletRequest) request;
		HttpSession session = requestCasted.getSession();
		
		HttpServletResponse responseCasted = (HttpServletResponse) response;
		
		if(session.getAttribute("isUserLoggedIn") == null) {
			responseCasted.sendRedirect(requestCasted.getContextPath()+"/start/login");
		}else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
