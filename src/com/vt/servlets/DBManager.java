package com.vt.servlets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.PreparedStatement;

public class DBManager {

	private DBConnector conection = new DBConnector();

	private static final String INSERT_USER = "INSERT INTO users (login, password, full_name, region, gender, comment) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String GET_FULL_NAME = "SELECT full_name FROM users WHERE login = ? AND password= ?;";

	public void createBD() {
		Connection conn = null;
		conn = conection.getConnection();
		if (conn != null) {
			Statement st = null;
			try {
				st = conn.createStatement();

				st.execute(
						"CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, login VARCHAR(100) NOT NULL UNIQUE, password VARCHAR(100) NOT NULL, full_name VARCHAR(50) NOT NULL,"
								+ " region VARCHAR(30) NOT NULL, gender VARCHAR(10) NOT NULL, comment VARCHAR(200));");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("DBManager returned null conection.");
		}
	}

	public void addUserToBD(String login, String password, String fullName, String region, String gender,
			String comment) {
		Connection conn = null;
		conn = conection.getConnection();
		if (conn != null) {
			PreparedStatement st = null;
			try {

				st = conn.prepareStatement(INSERT_USER);
				st.setString(1, login);
				st.setString(2, password);
				st.setString(3, fullName);
				st.setString(4, region);
				st.setString(5, gender);
				st.setString(6, comment);
				st.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("DBManager returned null conection.");
		}
	}

	public String getFullNameByLoginAndPassword(String login, String password) {

		Connection conn = null;
		conn = conection.getConnection();

		String fullName = null;

		if (conn != null && login!=null && password!=null) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {

				st = conn.prepareStatement(GET_FULL_NAME);
				st.setString(1, login);
				st.setString(2, password);
				rs = st.executeQuery();

				if (rs.next()) {
					fullName = rs.getString("full_name");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("DBManager returned null conection.");
		}

		return fullName;
	}

}
