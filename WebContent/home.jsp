<%@ page import="java.util.*,com.weather.*,java.text.SimpleDateFormat,com.weather.values.*,com.weather.client.*, javax.servlet.*" %>
<%
HttpSession mysession = request.getSession(false);
if(mysession == null ||(String)mysession.getAttribute("username") == null) {
    response.sendRedirect("login.jsp");}
List<String> citylist = (List<String>) mysession.getAttribute("User_City_List");
WeatherResponse searchResponse=(WeatherResponse)mysession.getAttribute("searchResponse");
Double avgWinter=(Double)mysession.getAttribute("AvgWinter");
Double avgSummer=(Double)mysession.getAttribute("AvgSummer");
Double avgCustom=(Double)request.getAttribute("AvgCustom");
String error =request.getParameter("errorMessageCustom");
 %>

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
            <input type="text" placeholder="Search City by Name or Latitude,Longitude" name="cityname">
            <input type="submit" value="Search" style="padding:10px;  border: none; border-radius: 5px; background:#0D2E50; color: white;"> 
            </form>  
            </div>
            <div class="details">
            <% if(searchResponse == null){  %> 
             		<div class="city-details" style="background: url('images/Clear.jpg') no-repeat center center ;background-size: cover">
            <% }
            else if(searchResponse.getWeather().get(0).getMain().equals("Clear")){%>
                  <div class="city-details" style="background: url('images/Clear.jpg') no-repeat center center ; background-size: cover">
             <% }
            else if(searchResponse.getWeather().get(0).getMain().equals("Clouds")){  %> 
             		<div class="city-details" style="background: url('images/Cloudy.jpg') no-repeat center center; background-size: cover">
       		<% }
            else if(searchResponse.getWeather().get(0).getMain().equals("Mist")){  %> 
       				<div class="city-details" style="background: url('images/Mist.jpg') no-repeat center center; background-size: cover">
       		<% }else if(searchResponse.getWeather().get(0).getMain().equals("Rain")){  %> 
             		<div class="city-details" style="background: url('images/Rain.jpg') no-repeat center center; background-size: cover">
             <% }
       		else if(searchResponse.getWeather().get(0).getMain().equals("Sunny")){  %> 
             		<div class="city-details" style="background: url('images/Sunny.jpg') no-repeat center center;background-size: cover">
             <% }
       		else{  %> 
             		<div class="city-details" style="background: url('images/Clear.jpg') no-repeat center center; background-size: cover">
             <% } %>
                <div class="city-info">
                <% if(searchResponse !=null){%>
                    <h1><%=searchResponse.getName() %></h1>
                    <p><%
      Date currentDate = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
      String formattedDate = dateFormat.format(currentDate); %>
      <%= formattedDate %></p>
                </div>
                <div class="current-temp">
                    <h2><%= (int)(searchResponse.getMain().getTemp()-273) %><sup>o</sup></h2>
                                        
                    <p><%= searchResponse.getWeather().get(0).getMain() %></p>
                    <% }
                else{ %>
                    <p>Search the city</p>
                    <% } %>
                </div>
            </div>
            
            <div class="temperature-details">
                <div class="temperature">
                    <p>Average Winter Temperature</p>
                    <% if(avgWinter != null){%>
                     <p><%= avgWinter %><sup>o</sup></p> 
                     <%} %>
                    <p>December - February</p>
                </div>
                <div class="temperature">
                    <p>Average Summer Temperature</p>
                    <% if(avgSummer != null){%>
                    <p><%= avgSummer %><sup>o</sup></p> 
                    <%} %>
                    <p>June - August</p>
                </div>
                <div class="calculate-temp">
                    <p>Calculate Average Temperature</p>
                    <div class="date-range">
                    <form action="avg" method="POST">
                    	<input type="text" placeholder="MM/YYYY" name="startdate">
                        <span>-</span>
                        <input type="text" placeholder="MM/YYYY" name="enddate">
                        <button>Go</button>
                    </form>  
                    </div>
                    <% 	if(error !=null){%>
                    <p><%=error %></p>
                    <% }%>
                    <div class="result">
                        <% if(avgCustom !=null){ 
                      %><h3><%= avgCustom.intValue() %><sup>o</sup></h3>
                        <% } %>
                    </div>
                </div>
                <div class="footer">
            <% if(searchResponse !=null){
			     if(citylist.contains(searchResponse.getName().toLowerCase()))
				    {%>   
			   <button>Already in Your List</button>  
			     <%}else {%> 
			     <form action="add" method="Post" >
			     <button>Add to the list</button> 
			     </form>
			    
  				<%}
			 } %> 
            </div>
            </div>
            </div>            
        </div>
        <div class="city-list">
         <%   if(citylist !=null){
                for(String tempCity:citylist)
                {
                	WeatherResponse carditem= WeatherUpdatesClient.getParametersCity(tempCity);
                	%>
                	 <div class="city-weather">
                	<div class="city"><%= carditem.getName() %></div>
	                <div class="temp"><%= (int)(carditem.getMain().getTemp()-273) %><sup>o</sup></div>
	                <div class="details"><%= carditem.getWeather().get(0).getMain() %></div>
	                </div>
               <% } 
               }%>
            
           <form action="logout" method="Post">
            <button class="logout-button" >LogOut</button>
            </form>
         </div>
            
        </div>
</body>
</html>
