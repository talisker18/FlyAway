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
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CheckIfUserIsLoggedInFilter
 */
public class CheckIfUserIsLoggedInFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckIfUserIsLoggedInFilter() {
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
		
		if(session.getAttribute("isUserLoggedIn") == null) {
			chain.doFilter(request, response);
		}else {
			RequestDispatcher rd = requestCasted.getRequestDispatcher("/views/already-logged-in.jsp");
			rd.include(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
