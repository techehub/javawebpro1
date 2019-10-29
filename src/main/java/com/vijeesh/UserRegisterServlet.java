package com.vijeesh;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserRegisterServlet extends HttpServlet {

	static final String DB_URL = "jdbc:mysql://localhost/javaweb";

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		
		
		
		ServletContext ctx= req.getServletContext();
		if (ctx.getAttribute("counter")!=null) {
			int c =  (Integer)ctx.getAttribute("counter");
			ctx.setAttribute("counter", c+1);
		}
		else {
			ctx.setAttribute("counter", 1);
		}
		
		
		
		ServletConfig cfg = getServletConfig();
		
		
		
		String uname = req.getParameter("username");
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		String cpwd = req.getParameter("cpwd");
		
		
		session.setAttribute("name",uname);
		session.setAttribute("email", email);

		boolean isErr= false;
		if (!email.contains("@")) {
			req.setAttribute("invalidemail", "Invalid Email");
			isErr= true;
		}
		
		if (! pwd .equals(cpwd)) {
			req.setAttribute("pwdmiscatch", "Invalid Password");
			isErr= true;
		}
		if (isErr) {
			RequestDispatcher rd = req.getRequestDispatcher("newuser.jsp");
			rd.forward(req, resp);
		}
		
		
		/*
		if (!email.contains("@")) {
			RequestDispatcher rd = req.getRequestDispatcher("newuser.jsp");
			req.setAttribute("invalidemail", "Invalid Email");
			rd.forward(req, resp);
		}
		
		if (! pwd .equals(cpwd)) {
			
			RequestDispatcher rd = req.getRequestDispatcher("newuser.jsp");
			req.setAttribute("pwdmiscatch", "Invalid Password");
			rd.forward(req, resp);
		}
		*/
		try {
			Class.forName("com.mysql.jdbc.Driver");

			
			String dbusername= getServletConfig().getInitParameter("dbuser");
			String dbpwd= getServletConfig().getInitParameter("dbpassword");
			
			
			Connection con = DriverManager.getConnection(DB_URL, dbusername, dbpwd);
			PreparedStatement p = con.prepareStatement("insert into user values(?,?,?)");
			p.setString(1, uname);
			p.setString(2, email);
			p.setString(3, pwd);
			p.execute();
		} catch (Exception e) {
			e.printStackTrace();
			

		}

		RequestDispatcher rd = req.getRequestDispatcher("user_reg_success.jsp");
		rd.forward(req, resp);
		//resp.getWriter().println(" Thanks for registering. Your user name is -" + uname);

	}

}
