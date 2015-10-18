package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String insert = "INSERT INTO users (username, password)";
		
		Connection conn = null;
		Statement st = null;
		PrintWriter out = response.getWriter();
		int rs;
		StringBuffer sb = new StringBuffer();
		ArrayList<User> users = new ArrayList<>();
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/registration");

			// This works too
			//Context envCtx = (Context) ctx.lookup("java:comp/env");
			//DataSource ds = (DataSource) envCtx.lookup("jdbc/registration");
			conn = ds.getConnection();

			st = conn.createStatement();
			rs = st.executeUpdate(insert + "values" + "('" + name + "','" + pass + "')");
			
			response.setContentType("text/html");
			out.println("<title>Registered succesfully</title>");
			out.println("Good job adding new user!</br>");
			out.println("<a href = '/WebApp/'>Click to back</a>");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("TestJndiServlet says hi");
		out.println("<br/>");
		out.println("----------------");
	}

	public ArrayList<User> testJndiDataSource() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		ArrayList<User> users = new ArrayList<>();
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/registration");

			// This works too
			//Context envCtx = (Context) ctx.lookup("java:comp/env");
			//DataSource ds = (DataSource) envCtx.lookup("jdbc/web");
			System.out.println("Connecting");
			conn = ds.getConnection();
			System.out.println("Connected");

			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM users");

			while (rs.next()) {
				System.out.println("1");
				int id = rs.getInt("id");
				String name = rs.getString("username");
				String pass = rs.getString("password");
				User user = new User(id, name, pass);
				users.add(user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (st != null) st.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return users;
	}

}
