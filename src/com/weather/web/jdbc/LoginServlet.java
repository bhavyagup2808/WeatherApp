package com.weather.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Login")
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

        if (appDBUtil.isValidUser(username, password)) {
        	 request.setAttribute("username",username);
        	 getList(request,response);
//	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
//	         dispatcher.forward(request, response);
        } else if (appDBUtil.isValidUsername(username)) {
            request.setAttribute("errorMessage", "Invalid password. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "The user is not registered.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
		
	}
	private void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String username= request.getParameter("username");
		List<String> citylist= appDBUtil.getCity(username);
		request.setAttribute("User_City_List",citylist);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
		dispatcher.forward(request,response);
	}
	
	
}