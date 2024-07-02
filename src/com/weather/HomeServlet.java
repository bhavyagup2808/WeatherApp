package com.weather;

import java.io.IOException;
import java.sql.SQLException;
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

import com.weather.client.WeatherUpdatesClient;
import com.weather.values.WeatherResponse;

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
		String command=request.getParameter("command");
		if(command==null){
			command="LIST";
		}
		switch (command)
		{
			case "LIST":
				getlist(request,response);
				break;
			case "SEARCH":
				getsearch(request,response);
				break;
		}
		
	}

	private void getsearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("cityname")+ request.getParameter("command"));
		String regex = "[+-]?(90(\\.0+)?|[1-8]?\\d(\\.\\d+)?),\\s*[+-]?(180(\\.0+)?|1[0-7]\\d(\\.\\d+)?|[1-9]?\\d(\\.\\d+)?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((request.getParameter("cityname")));
        WeatherResponse weatherResponse=null;
        if (matcher.find()) {
            String latLong = matcher.group();
            System.out.println("Found latitude and longitude: " + latLong);

            String[] parts = latLong.split(",");
            String latitude = parts[0].trim();
            String longitude = parts[1].trim();
            weatherResponse=WeatherUpdatesClient.getParameterslatlong(latitude,longitude);
        }
        else
        {
        	weatherResponse=WeatherUpdatesClient.getParametersCity(request.getParameter("cityname"));
        }
        HttpSession session = request.getSession(false);
        String username=(String)session.getAttribute("username");
		List<String> citylist = null;
		try {
			citylist = cityDBUtil.getCity(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Found latitude and longitude: " + username);
		request.setAttribute("User_City_List",citylist);
		request.setAttribute("searchResponse", weatherResponse);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
		dispatcher.forward(request,response);
	}

	private void getlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username= request.getParameter("username");
		List<String> citylist = null;
		try {
			citylist = cityDBUtil.getCity(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("User_City_List",citylist);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
		dispatcher.forward(request,response);
	}


}
