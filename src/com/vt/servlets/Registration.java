package com.vt.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {

	private static final long serialVersionUID = -5870330710990233204L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		String fullName = request.getParameter("fullName");
		String region = request.getParameter("region");
		String gender = request.getParameter("gender");
		String comment = request.getParameter("comment");
		String agreement = request.getParameter("agreement");
		
		PrintWriter out = resp.getWriter();
		
		String form = "<html><div style=\"width: 50%; height: 100px; float: left;\"> \r\n"
				+ "            <form action=\"\" method='post'>\r\n"
				+ "			   <table border=\"0\">\r\n"
				+ "				  <tr>\r\n"
				+ "					 <td width=\"100\">Login</td>\r\n"
				+ "					 <td ><input type=\"email\" name=\"login\" value=\"" + (login!=null ? login:" ") + "\"/></td>\r\n"
				+ "				  </tr>\r\n"
				+ "				  <tr>\r\n"
				+ "					 <td>Password</td>\r\n"
				+ "					 <td><input type=\"password\" name=\"password\" /></td>\r\n"
				+ "				  </tr>\r\n"
				+ "				  <tr>\r\n"
				+ "					 <td>Re-Password</td>\r\n"
				+ "					 <td><input type=\"password\" name=\"rePassword\" /></td>\r\n"
				+ "				  </tr>\r\n"
				+ "				  <tr>\r\n"
				+ "					 <td>Full name</td>\r\n"
				+ "					 <td><input type=\"text\" name=\"fullName\" value=\""+(fullName!=null ? fullName:" ")+"\"/></td>\r\n"
				+ "				  </tr>\r\n"
				+ "				  <tr>\r\n"
				+ "					 <td>Region</td>\r\n"
				+ "					 <td>\r\n"
				+ "						 <select name = \"region\">\r\n"
				+ "						 <option value = \"Lviv\"" + ("Lviv".equals(region) ? "selected":" ") + ">Lviv region</option>\r\n"
				+ "						 <option value = \"Kyiv\"" + ("Kyiv".equals(region) ? "selected":" ") + ">Kyiv region</option>\r\n"
				+ "						 <option value = \"Kharkiv\"" + ("Kharkiv".equals(region) ? "selected":" ") + ">Kharkiv region</option>\r\n"
				+ "						 </select>\r\n"
				+ "					 </td>\r\n"
				+ "				  </tr>\r\n"
				+ "				  <tr>\r\n"
				+ "					 <td>Gender</td>\r\n"
				+ "						<td>\r\n"
				+ "							F <input type=\"radio\" value = \"F\" name=\"gender\""+ ("F".equals(gender) ? "checked":" ")+"/>\r\n"
				+ "							M <input type=\"radio\" value = \"M\" name=\"gender\""+ ("M".equals(gender) ? "checked":" ")+"/>\r\n"
				+ "						</td>\r\n"
				+ "				  </tr>\r\n"
				+ "				  <tr>\r\n"
				+ "					 <td>Comment</td>\r\n"
				+ "						<td>\r\n"
				+ "							<textarea cols = \"10\" rows = \"5\" name = \"comment\">" + (comment!=null ? comment:" ") + "</textarea>\r\n"
				+ "						</td>\r\n"
				+ "				  </tr>\r\n"
				+ "				  <tr>\r\n"
				+ "					 <td>I gree for personal</td>\r\n"
				+ "						<td>\r\n"
				+ "							<input type=\"checkbox\" name=\"agreement\"/>\r\n"
				+ "						</td>\r\n"
				+ "				  </tr>\r\n"
				+ "				  <tr>\r\n"
				+ "					 <td align=\"center\" colspan=\"2\"><input type=\"submit\" value=\"Send\" /></td>\r\n"
				+ "				  </tr>\r\n"
				+ "			   </table>\r\n"
				+ "		   </form>\r\n"
				+ "        </div></html>";
		
		out.write(Menu.MENU);
		
		out.write(form);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		String fullName = request.getParameter("fullName");
		String region = request.getParameter("region");
		String gender = request.getParameter("gender");
		String comment = request.getParameter("comment");
		String agreement = request.getParameter("agreement");

		boolean isError = false;

		String errorText = "<ul>";
		if (login == null || login.isEmpty()) {
			isError = true;
			errorText += "<li>login is empty</li>";
		} else if (!login.matches(".*@.*\\.com")) {
			isError = true;
			errorText += "<li>incorect login</li>";
		}

		if (password == null || password.isEmpty()) {
			isError = true;
			errorText += "<li>password is empty</li>";
		} else {
			if (password.length() < 8) {
				isError = true;
				errorText += "<li>password is too short</li>";
			}
			if (!password.matches(".*[0-9].*")) {
				isError = true;
				errorText += "<li>password has to contain at least one digit</li>";
			}
			if (!password.matches(".*[A-Z].*")) {
				isError = true;
				errorText += "<li>password has to contain at least capital letter</li>";
			}
		}

		if (rePassword == null || rePassword.isEmpty()) {
			isError = true;
			errorText += "<li>rePassword is empty</li>";
		} else if (password != null && !password.isEmpty()) {
			if (!rePassword.equals(password)) {
				isError = true;
				errorText += "<li>rePassword and password are different</li>";
			}
		}

		if (fullName == null || fullName.isEmpty()) {
			isError = true;
			errorText += "<li>fullName is empty</li>";
		}
		if (gender == null || gender.isEmpty()) {
			isError = true;
			errorText += "<li>gender is not chosen</li>";
		}
		if (agreement == null || agreement.isEmpty()) {
			isError = true;
			errorText += "<li>agreement is not checked</li>";
		}

		errorText += "</ul>";

		PrintWriter out = resp.getWriter();

		out.write("<div style=\"width: 100%;\">");

		if (isError) {
			doGet(request, resp);
		}

		out.write("<div style=\"margin-left: 50%; height: 100px;\"> ");
		if (region != null) {
			if (isError) {
				out.write(errorText);
			} else {
				out.write("Registration successful");
			}
		}
		out.write("</div>");
		out.write("</div>");
	}

}
