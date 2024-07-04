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

import com.weather.values.WeatherResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
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
		HttpSession session = request.getSession(false);
		WeatherResponse searchResponse=(WeatherResponse)session.getAttribute("searchResponse");
		String username=(String)session.getAttribute("username");
		String cityname=searchResponse.getName();
		System.out.println(username+cityname);
		try {
			cityDBUtil.addCity(cityname,username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("home");
		dispatcher.forward(request,response);
	}

}
