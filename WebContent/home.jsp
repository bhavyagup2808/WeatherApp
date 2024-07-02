<%@ page import="java.util.*,com.weather.*,java.text.SimpleDateFormat,com.weather.values.*,com.weather.client.*" %>
<%
List<String> citylist = (List<String>) request.getAttribute("User_City_List");
String cityname=(String)request.getAttribute("cityname");
WeatherResponse searchResponse=(WeatherResponse)request.getAttribute("searchResponse");
%>
     
<%-- <% if (citylist != null) { %> --%>
<%--     <% for (String tempcity : citylist) { %> --%>
<%--     <h2><%= tempcity %></h2> --%>
<%--     <% } %> --%>
<%-- <% } %> --%>

<!DOCTYPE html>
<html>
<head>
    <title>Weather Updates</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
    <div class="container">
        <div class="sidebar">
            <div class="logo">
                <img src="images/logo.png" alt="Logo">
            </div>
        </div>
        <div class="main-content">
            <div class="search-bar">
            <form action="home" method="post">
            <input type="hidden" name="command" value="SEARCH">
            <input type="text" placeholder="Search City" name="cityname">
            </form>
                
            </div>
            <div class="city-details">
                <div class="city-info">
                <% if(searchResponse !=null){%>
                    <h1><%=searchResponse.getName() %></h1>
                    <% } %>
                    <p><%
      Date currentDate = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
      String formattedDate = dateFormat.format(currentDate); %>
      <%= formattedDate %></p>
                </div>
                <div class="city-image">
                    <img src="images/jaipur.jpg" alt="Jaipur">
                </div>
                <div class="current-temp">
                <% if(searchResponse !=null){%>
                    <h2><%= (int)(searchResponse.getMain().getTemp()-273) %><sup>o</sup></h2>
                                        
                    <p><%= searchResponse.getWeather().get(0).getMain() %></p>
                    <% } %>
                </div>
            </div>
            <div class="temperature-details">
                <div class="temperature">
                    <p>Average Winter Temperature</p>
                    <h3>10°</h3>
                    <p>December - February</p>
                </div>
                <div class="temperature">
                    <p>Average Summer Temperature</p>
                    <h3>33°</h3>
                    <p>June - August</p>
                </div>
                <div class="calculate-temp">
                    <p>Calculate Average Temperature</p>
                    <div class="date-range">
                        <input type="text" placeholder="MM/YYYY">
                        <span>-</span>
                        <input type="text" placeholder="MM/YYYY">
                    </div>
                    <div class="result">
                        <h3>33°</h3>
                    </div>
                </div>
            </div>
            <div class="footer">
<%-- 			    <% if(citylist.contains(cityname))  --%>
<%-- 				    {%>   --%>
<!--  				   <p>Already in Your List</p>   -->
<%-- 			    <% }  else --%>
<%-- 				    {%> --%>
<!-- 				    <p>Add to the list</p>   -->
<%--  				<% } %>   --%>
            </div>
        </div>
        <div class="city-list">
           
                <% for(String tempCity:citylist)
                {
                	WeatherResponse carditem= WeatherUpdatesClient.getParametersCity(tempCity);
                	%>
                	 <div class="city-weather">
                	<div class="city"><%= carditem.getName() %></div>
	                <div class="temp"><%= (int)(carditem.getMain().getTemp()-273) %><sup>o</sup></div>
	                <div class="details"><%= carditem.getWeather().get(0).getMain() %></div>
	                </div>
               <% }%>
            
           
         </div>
            <form action="logout" method="Post">
            <button class="logout-button" >LogOut</button>
            </form>
        </div>
    </div>
</body>
</html>
