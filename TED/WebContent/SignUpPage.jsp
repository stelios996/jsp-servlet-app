<!DOCTYPE html>

<html>
	<head>
		<title>Sign Up </title>
		<link rel="stylesheet" type="text/css" href="css/styleSignUpPage.css">
	</head>
	<body>
		<div class="header2">
			<div id="logo" class="header2">
				myLinkedIn
			</div>
			<div id="return" class="header2">
				<a href="./WelcomePage.jsp"> <br/>Back </a>
			</div>
		</div>
		<div class="content">
			<fieldset id="signup" class="content"><Legend> Sign Up </Legend>
				<form action="ServletSignUp" method="post" enctype="multipart/form-data">
					<label for="name">Name:</label>
					<input type="text" id="name" class="content" name="name">
					<br/>
					<label for="surname">Surname:</label>
					<input type="text" id="surname" class="content" name="surname">
					<br/>
					<label for="email">E-mail:</label>
					<input type="text" id="email" class="content" name="email">
					<br/>
					<label for="password">Password:</label>
					<input type="password" id="password" class="content" name="password">
					<br/>
					<label for="validation">Validate Password:</label>
					<input type="password" id="validation" class="content" name="validation">
					<br/>
					<label for="telephone">Telephone Number:</label>
					<input type="tel" id="telephone" class="content" name="telephone">
					<br/>
					<label for="picture">Profile Picture:</label>
			  		<input type="file" id="profilepic" class="content" name="pic" accept="image/*">
					<br/>
					<input type="submit" id="signupButton" class="content" value="Sign Up">
					<br/>
					<%	try{
							if(session.getAttribute("signupStatus")=="NotOK"){	
					%>
								<p class="signupWarning">Fill all fields!!!</p>
					<% 
								session.setAttribute("signupStatus","OK");
							}else if(session.getAttribute("passvalidStatus")=="NotOK"){
					%>
								<p class="signupWarning">Passwords don't match!!!</p>
					<% 
								session.setAttribute("passvalidStatus","OK");
							}else if(session.getAttribute("existStatus")=="NotOK"){
					%>
								<p class="signupWarning">Email already exists!!!</p>
					<%		
								session.setAttribute("existStatus","OK");					
							}
						}catch(Exception e){
					%>
						<h1>ERROR2</h1>
					<%
						}
					%>
				</form>
			</fieldset>
		</div>
	</body>

</html>