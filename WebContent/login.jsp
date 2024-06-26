<!DOCTYPE html>
<html>
  <head>
  	<title>Weather Update</title>
  	<link rel="icon" type="image/x-icon" href="images/weather-app.png">
    <link rel="stylesheet" href="css/login.css" />

  </head>
  <body>
    <div class="login-page">

          <div class="app-details">
              <img class="logo" src="images/weather-app.png"/>
              <h2 class="app-name">Weather Update</h2>
              <h2 class="tagline">Your Personal Weather Guide</h2> 
          </div>
          <div>
	          <form class="login-form" action="UserControllerServlet" method="POST">
	              <h2 class="login-form-heading">Login</h2>
	              <h3 class="tagline1">Keep Updates Your Favorite City</h3>
	              <input type="hidden" name="command" value="LOGIN" />
	              <%
	              	String errorMessage=(String)request.getAttribute("errorMessage");
	              	if(errorMessage != null){
	              		%>
	              		<p style="color:#ffffff;"><%= errorMessage %> </p>
	              	<% } %>
	                     
		          <div class="login-attribute1">
		            <input class="login-placeholder1" placeholder="Username" type="text" />
		            <div class="inner-line"></div>
		          </div>
	            <div class="login-attribute1">
	              <input class="login-placeholder1" placeholder="Password" type="text" />
	              <div class="inner-line"></div>
	            </div>
	          <input type="Submit" class="submit-button" value="Login">
	        </form>
          </div>
        
      </div>
  </body>
</html>