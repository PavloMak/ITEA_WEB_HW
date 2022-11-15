package com.vt.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vt.utils.Encoder;

public class Authorization extends HttpServlet {

	private static final long serialVersionUID = 1200711808800119310L;

	String form = "<html><center>\r\n" + "						<form action=\"\" method='post'>\r\n"
			+ "							<table border=\"0\">\r\n" + "								<tr>\r\n"
			+ "									<td width=\"100\">Login</td>\r\n"
			+ "									<td ><input type=\"text\" name=\"login\" /></td>\r\n"
			+ "								</tr>\r\n" + "								<tr>\r\n"
			+ "									<td>Password</td>\r\n"
			+ "									<td><input type=\"text\" name=\"password\" /></td>\r\n"
			+ "								</tr>\r\n" + "								<tr>\r\n"
			+ "									<td align=\"center\" colspan=\"2\"><input type=\"submit\" value=\"Send\" /></td>\r\n"
			+ "								</tr>\r\n" + "							</table>\r\n"
			+ "						</form>\r\n" + "					</center></html>";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.write(Menu.MENU);
		out.write(form);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		DBManager menager = new DBManager();
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		PrintWriter out = resp.getWriter();

		String fullName = menager.getFullNameByLoginAndPassword(login, Encoder.md5EncriptionWithSult(password));
		
		if (fullName!=null) {
			out.write("Access granted. Welcome "+ fullName);
		} else {
			out.write("Access denied");
			doGet(request, resp);
		}
		
	}

}
