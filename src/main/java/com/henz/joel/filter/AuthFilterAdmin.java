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
 * Servlet Filter implementation class AuthFilterAdmin
 */
public class AuthFilterAdmin implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilterAdmin() {
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
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/start/");
		}else {
			
			if(session.getAttribute("role").equals(UserRoles.ADMIN.getRole())) {
				chain.doFilter(request, response);
			}else {
				RequestDispatcher rd = httpRequest.getRequestDispatcher("/views/user-not-permitted-info.jsp");
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
