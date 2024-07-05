package com.weather.client;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.values.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;


public class AvgWeatherUpdateClient {
	static public ArrayList<Integer> getParameter(String CityName) throws JsonParseException, JsonMappingException, IOException
	{
		String apiUrl = "http://localhost:3000/cities";
        URL url = new URL(apiUrl);
        ArrayList<Integer> temp=new ArrayList<Integer>();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, MonthData> citiesData = objectMapper.readValue(
                connection.getInputStream(),
                new TypeReference<Map<String, MonthData>>() {}
        );
        String string=null;
         if(CityName.length()>0) {
        	 string=Character.toString(CityName.toUpperCase().charAt(0));
         MonthData monthsData = citiesData.get(string);
         
         if(monthsData !=null)
         {
         	temp.add(monthsData.getJanuary());
         	temp.add(monthsData.getFebruary());
         	temp.add(monthsData.getMarch());
         	temp.add(monthsData.getApril());
         	temp.add(monthsData.getMay());
         	temp.add(monthsData.getJune());
         	temp.add(monthsData.getJuly());
         	temp.add(monthsData.getAugust());
         	temp.add(monthsData.getSeptember());
         	temp.add(monthsData.getOctober());
         	temp.add(monthsData.getNovember());
         	temp.add(monthsData.getDecember());
         }
         }
         return temp;
            
	}
}
