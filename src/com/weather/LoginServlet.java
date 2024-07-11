package com.weather;

import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AppDBUtil appDBUtil;

    @Resource(name="jdbc/weather_app")
    private DataSource dataSource;

    public void init() throws ServletException {
        super.init();
        try {
            appDBUtil = new AppDBUtil(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			getLogin(request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
    }

	private void getLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.trim()=="" || password.trim()=="")
        {
        	request.setAttribute("errorMessage", "Enter username and password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
        else if (appDBUtil.isValidUser(username, password)) {
        	 HttpSession session = request.getSession();
        	 session.setAttribute("username", username);
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("home");
	         dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
		
	}
}