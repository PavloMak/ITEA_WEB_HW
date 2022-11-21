package com.vt.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vt.utils.Encoder;

public class Authorization extends HttpServlet {

	private static final long serialVersionUID = 1200711808800119310L;
	public static final String USER_ACCESS_GRANTED = "access";

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

		HttpSession session = request.getSession();
		session.getAttribute(USER_ACCESS_GRANTED);

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String key = request.getParameter("key");

		if (key != null) {
			session.invalidate();

		}
		session = request.getSession(true);
		out.write(Menu.MENU);
		if (session.getAttribute(USER_ACCESS_GRANTED) == null) {
			
			out.write(form);
		} else {
			out.write("You have already authorized");

			// out.write("<a href='/login?key'>Log out</a>");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		DBManager menager = new DBManager();
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		PrintWriter out = resp.getWriter();

		String fullName = menager.getFullNameByLoginAndPassword(login, Encoder.md5EncriptionWithSult(password));

		if (fullName != null) {

			HttpSession session = request.getSession();
			session.setAttribute(USER_ACCESS_GRANTED, true);
			out.write(Menu.MENU);
			//out.write("You have already authorized");
			out.write("Access granted. Welcome " + fullName + "	<br>");
			
			out.write("<a href='/secr'>secret page</a>");
			
		} else {
			out.write("Access denied");
			doGet(request, resp);
		}

	}

}
