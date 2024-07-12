package com.weather;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.weather.client.*;
import com.weather.values.*;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CityDBUtil cityDBUtil;

    @Resource(name="jdbc/weather_app")
    private DataSource dataSource;

	public void init() throws ServletException {
        super.init();
        try {
            cityDBUtil= new CityDBUtil(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getlist(request,response);
	
	}
	private void getlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        String username=(String)session.getAttribute("username");
		List<String> citylist = null;
		try {
			citylist = cityDBUtil.getCity(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("User_City_List",citylist);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
		dispatcher.forward(request,response);
	}


}
