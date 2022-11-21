package com.vt.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecretServlet extends HttpServlet{

	private static final long serialVersionUID = 1492554131356107811L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.write(Menu.MENU);
		//out.write("<a href='/login?key'>Log out</a><br>");
		out.write("Mmmm this is a secret page. I hope you are authorized)");
	}

}
