<%@ page import="java.util.*,com.weather.*,java.text.SimpleDateFormat" %>
<%
List<String> citylist = (List<String>) request.getAttribute("User_City_List");
String cityname=(String)request.getAttribute("cityname");
%>
     
<% if (citylist != null) { %>
    <% for (String tempcity : citylist) { %>
    <h2><%= tempcity %></h2>
    <% } %>
<% } %>

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
                <input type="text" placeholder="Search City">
            </div>
            <div class="city-details">
                <div class="city-info">
                    <h1>Jaipur, Rajasthan</h1>
                    <p><%
      Date currentDate = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
      String formattedDate = dateFormat.format(currentDate); %>
      <%= formattedDate%></p>
                </div>
                <div class="city-image">
                    <img src="images/jaipur.jpg" alt="Jaipur">
                </div>
                <div class="current-temp">
                    <h2>33°</h2>
                    <p>Sunny</p>
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
			    <% if(citylist.contains(cityname)) 
				    {%> 
 				   <p>Already in Your List</p>  
			    <% } 
				    else
				    {%> 
				    <p>Add to the list</p>  
 				<% } %>  
            </div>
        </div>
        <div class="city-list">
            <div class="city-weather">
                <div class="city">Jaipur</div>
                <div class="temp">33°</div>
                <div class="details">Sunny</div>
            </div>
            <div class="city-weather">
                <div class="city">NewDelhi</div>
                <div class="temp">28°</div>
                <div class="details">Mostly Cloudy</div>
            </div>
            <div class="city-weather">
                <div class="city">Kolkata</div>
                <div class="temp">26°</div>
                <div class="details">Heavy Rain</div>
            </div>
            <div class="city-weather">
                <div class="city">Bangalore</div>
                <div class="temp">30°</div>
                <div class="details">Sunny</div>
            </div>
            <button class="logout-button">LogOut</button>
        </div>
    </div>
</body>
</html>
