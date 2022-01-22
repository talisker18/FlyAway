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

import com.henz.joel.other.UserRoles;

/**
 * Servlet Filter implementation class AuthFilterUser
 */
public class AuthFilterUser implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilterUser() {
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
		HttpServletRequest httpRequest = (HttpServletRequest) request;	
		HttpSession session = httpRequest.getSession();
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if(session.getAttribute("isUserLoggedIn") == null) {
			chain.doFilter(request, response);
		}else {
			
			if(session.getAttribute("role").equals(UserRoles.USER.getRole())) {
				chain.doFilter(request, response);
			}else {
				RequestDispatcher rd = httpRequest.getRequestDispatcher("/views/admin-not-permitted-info.jsp");
				rd.include(httpRequest, httpResponse);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
