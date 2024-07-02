package com.weather.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.values.WeatherResponse;
import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
public class WeatherUpdatesClient  {
	
	private static final String WEATHER_UPDATE_API="abeabd49919f4aafe92338a31ded7745"; 
	
	private WeatherUpdatesClient() {
	}
	public static WeatherResponse getParameterslatlong(String latitude, String longitude) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+ "&appid="+WEATHER_UPDATE_API;
        //https://api.openweathermap.org/data/3.0/onecall?lat=33.44&lon=-94.04&appid={API key}
        //System.out.println("API URL: " + url);
        HttpGet requestHttp = new HttpGet(url);
        WeatherResponse weatherResponse=null;
 
        try (CloseableHttpResponse response = httpClient.execute(requestHttp)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                weatherResponse = mapper.readValue(result, WeatherResponse.class);
            } else {
                System.out.println("Failed to get weather data. HTTP error code: " + response.getStatusLine().getStatusCode());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }
	public static WeatherResponse getParametersCity(String cityname) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+cityname+"&appid="+WEATHER_UPDATE_API;
        HttpGet requestHttp = new HttpGet(url);
        WeatherResponse weatherResponse=null;
 
        try (CloseableHttpResponse response = httpClient.execute(requestHttp)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                weatherResponse = mapper.readValue(result, WeatherResponse.class);
            } else {
                System.out.println("Failed to get weather data. HTTP error code: " + response.getStatusLine().getStatusCode());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;

	}
}