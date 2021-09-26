<!DOCTYPE html>

<html>
	<head>
		<title>Welcome to myLinkedIn!!!</title>
		<link rel="stylesheet" type="text/css" href="css/styleWelcomePage.css">
	</head>
	<body>
		<div class="header1">
			<div id="logo" class="header1">
				myLinkedIn
			</div>
			<div id="signup" class="header1">
				<a href="./SignUpPage.jsp"> <br/>Sign Up </a>
			</div>
		</div>
		<div class="content">
			<fieldset id="login" class="content"><Legend> Login </Legend>
				<form action="ServletLogin" method="post">
					<label for="email">E-mail:</label>
					<input type="text" id="email" class="content" name="email">
					<br/>
					<label for="password">Password:</label>
					<input type="password" id="password" class="content" name="password">
					<br/>
					<input type="submit" id="submitButton" class="content" value="Login">
					
					<br/>
					<%	try{
							if(session.getAttribute("loginStatus1")=="NotOK"){	
					%>
								<p class="loginWarning">Give email and password!!!</p>
					<%
								session.setAttribute("loginStatus1","OK");
							}else if(session.getAttribute("loginStatus2")=="NotOK"){
					%>
								<p class="loginWarning">Wrong email or password!!!</p>
					<%		
								session.setAttribute("loginStatus2","OK");
							}
						}catch(Exception e){
					%>
						<h1>ERROR</h1>
					<%
						}
					%>
				</form>
			</fieldset>
		</div>
	</body>

</html>