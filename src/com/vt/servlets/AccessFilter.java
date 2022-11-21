package com.vt.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccessFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain arg2)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;	
		HttpSession session = request.getSession();
		
		if (session.getAttribute(Authorization.USER_ACCESS_GRANTED) == null) {
			HttpServletResponse response = (HttpServletResponse) resp;
			response.sendRedirect("/forbid");
		}
		
		
	}

}
