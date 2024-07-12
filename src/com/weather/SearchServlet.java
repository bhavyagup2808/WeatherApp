package com.weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.weather.client.AvgWeatherUpdateClient;
import com.weather.client.WeatherUpdatesClient;
import com.weather.values.WeatherResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regex = "-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((request.getParameter("cityname")));
        WeatherResponse weatherResponse=null;
        ArrayList<Integer> monthData=null;
        HttpSession session = request.getSession(false);
        if (matcher.find()) {
            String latLong = matcher.group();
            String[] parts = latLong.split(",");
            String latitude = parts[0].trim();
            String longitude = parts[1].trim();
            weatherResponse=WeatherUpdatesClient.getParameterslatlong(latitude,longitude);
            if(weatherResponse !=null)monthData=AvgWeatherUpdateClient.getParameter(weatherResponse.getName());
            else
        	{
        		RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
        		dispatcher.forward(request,response);
        	}
        }
        else
        {
        	weatherResponse=WeatherUpdatesClient.getParametersCity(request.getParameter("cityname"));
        	if(weatherResponse !=null) monthData=AvgWeatherUpdateClient.getParameter(weatherResponse.getName());
        	else
        	{
        		RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
        		dispatcher.forward(request,response);
        	}
        }
        if(monthData !=null) {
        	double avgtemp=0; 
        	double count=0;
            for(int i=11;i%12>10 || i%12<2;i++)
            {
            	avgtemp+= monthData.get(i%12);
            	count++;
           	}
            avgtemp/=count;
            session.setAttribute("AvgWinter",avgtemp );
         }
        if(monthData !=null)
        { 
        	double avgtemp=0;
        	double count=0;
            for(int i=5;i<8;i++)
            {
	          	avgtemp+= monthData.get(i%12);
	         	count++;
	         }
            avgtemp/=count;
            session.setAttribute("AvgSummer",avgtemp );
        }
		session.setAttribute("searchResponse", weatherResponse);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
		dispatcher.forward(request,response);
	}

}
