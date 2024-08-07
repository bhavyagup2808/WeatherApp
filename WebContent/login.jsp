<!DOCTYPE html>
<html>
  <head>
  	<title>Weather Update</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link rel="icon" type="image/x-icon" href="images/logo.png">
    <link rel="stylesheet" href="css/loginnew.css">

  </head>
  <body>
    <div class="login-page">
          <div class="app-details">
              <img class="logo" src="images/logo.png"/>
              <h2 class="app-name">Weather Update</h2>
              <h2 class="tagline">Your Personal Weather Guide</h2> 
          </div>
          <div class="login-form-page">
	          <form class="login-form" action="login" method="POST">
	              <h2 class="login-form-heading">Login</h2>
	              <h3 class="tagline1">Keep Updates Your Favorite City</h3>
	              <%
	              	String errorMessage=(String)request.getAttribute("errorMessage");
	              	if(errorMessage != null){%>
	              	<p class="error-message" style="color: red; margin: 0.5rem 0rem ; font-size: 1.25vw;">*<%= errorMessage %> </p>
	              <% } %>
	                     
			     <div class="login-attribute1">
			       <input class="login-placeholder1" placeholder="Username" type="text" name="username" />
			     </div>
	            <div class="login-attribute1">
	              <input class="login-placeholder1" placeholder="Password" type="password" name="password" />
	            </div>
	          <input type="Submit" class="submit-button" value="Login">
	        </form>
          </div>
      </div>
  </body>
</html>