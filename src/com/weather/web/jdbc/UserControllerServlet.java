package com.weather.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
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
			
				String getcom=request.getParameter("command");
				if ("LOGIN".equals(getcom)) {
	              getLogin(request, response);
	            }
			} catch (Exception e) {
				throw new ServletException(e);
			}
        
    }

	private void getLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (appDBUtil.isValidUser(username, password)) {
            request.getRequestDispatcher("/welcome.jsp").forward(request, response); 
        } else if (appDBUtil.isValidUsername(username) || appDBUtil.isValidPassword(password)) {
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "The user is not registered.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
		
	}
}