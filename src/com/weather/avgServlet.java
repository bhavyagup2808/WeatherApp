package com.weather;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.weather.client.AvgWeatherUpdateClient;
import com.weather.values.*;
@WebServlet("/avg")
public class avgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		WeatherResponse weatherResponse=(WeatherResponse)session.getAttribute("searchResponse");
		String startdate=request.getParameter("startdate");
		String enddate=request.getParameter("enddate");
		ArrayList<Integer> monthData=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		if(weatherResponse==null)
		{
			request.setAttribute("errorMessageCustom", "First Search the city");
		}
		else if(isValidDate(enddate)==false || isValidDate(startdate)==false || isStartDateEarlier(startdate,enddate)==false)
		{
			request.setAttribute("errorMessageCustom", "Enter the duration correctly");
		}
		else
		{
			 double avgcustom=0;
			 monthData=AvgWeatherUpdateClient.getParameter(weatherResponse.getName());
			 try {
				Date start = dateFormat.parse(startdate);
				Date end = dateFormat.parse(enddate);
				Calendar startCalendar = Calendar.getInstance();
	            startCalendar.setTime(start);

	            Calendar endCalendar = Calendar.getInstance();
	            endCalendar.setTime(end);
	            int startYear = startCalendar.get(Calendar.YEAR);
	            int startMonth = startCalendar.get(Calendar.MONTH);

	            int endYear = endCalendar.get(Calendar.YEAR);
	            int endMonth = endCalendar.get(Calendar.MONTH);
	            int  count=(endYear - startYear) * 12 + (endMonth - startMonth);
	            int n=1;

	            for(int x=startMonth;n<=count;x++)
	            {
	            	avgcustom += monthData.get(x%12);
	            	n++;
	            }
	            avgcustom /= count;
	            request.setAttribute("AvgCustom", avgcustom);
			} 
			catch (ParseException e) {
				e.printStackTrace();
			}
	         
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
		dispatcher.forward(request,response);
	}
	public static boolean isStartDateEarlier(String startdate, String enddate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        try {
            Date start = dateFormat.parse(startdate);
            Date end = dateFormat.parse(enddate);
            return start.before(end);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	public static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        dateFormat.setLenient(false); 
        try {
            dateFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
