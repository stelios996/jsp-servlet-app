<!DOCTYPE html>

<html>
	<head>
		<title>Settings</title>
		<link rel="stylesheet" type="text/css" href="css/styleSettingsPage.css">
	</head>
	<body>
		<div class="header3">
			<div id="logo" class="header3">
				myLinkedIn
			</div>
			<div class="navbar">
				<div id="home" class="navbar"><a href="./HomePage.jsp">Home</a></div>
				<div id="separator" class="navbar"> | </div>
				
				<div id="network" class="navbar"><a href="ServletNetwork">Network</a></div>
				<div id="separator" class="navbar"> | </div>
				
				<div id="advertisements" class="navbar"><a href="./AdsPage.jsp">Advertisements</a></div>
				<div id="separator" class="navbar"> | </div>
				
				<div id="chat" class="navbar"><a href="./ChatPage.jsp">Chat</a></div>
				<div id="separator" class="navbar"> | </div>
				
				<div id="notifications" class="navbar"><a href="./NotificationsPage.jsp">Notifications</a></div>
				<div id="separator" class="navbar"> | </div>
				
				<div id="profile" class="navbar"><a href="ServletProfile">Profile</a></div>
				<div id="separator" class="navbar"> | </div>
				
				<div id="settings" class="navbar"><a href="./SettingsPage.jsp">Settings</a></div>
				<div id="separator" class="navbar"> | </div>
				
				<div id="logout" class="navbar"><a href="ServletLogout">Logout</a></div> <!--!!!!!!!!servletlogout -->
			</div>
		</div>
		<div class="content">
			<fieldset id="changeSettings" class="content"><Legend> Fill in the fields you want to change </Legend>
				<form action="ServletChangeSettings" method="post">
					<label for="email">New E-mail:</label>
					<input type="text" id="email" class="content" name="email">
					<br/>
					<label for="password">New Password:</label>
					<input type="password" id="password" class="content" name="password">
					<br/>
					<label for="confirmPassword">Confirm Password:</label>
					<input type="password" id="confirmPass" class="content" name="confirmPassword">
					<br/>
					<input type="submit" id="submitButton" class="content" value="Submit">
					<br/>
					
					<%	try{
							if(session.getAttribute("fieldStatus")=="NotOK"){
					%>
								<p class="changeSetWarning">No input given!!!</p>
					<%
								session.setAttribute("fieldStatus", "OK");
							}else if(session.getAttribute("passStatus")=="NotOK"){
					%>
								<p class="changeSetWarning">Fill in both password fields!!!</p>
					<%
								session.setAttribute("passStatus", "OK");
							}else if(session.getAttribute("emailStatus")=="NotOK"){
					%>
								<p class="changeSetWarning">Name already exists!!!</p>
					<%
								session.setAttribute("emailStatus", "OK");
							}else if(session.getAttribute("confirmPassStatus")=="NotOK"){
					%>
								<p class="changeSetWarning">Passwords don't match!!!</p>		
					<%
								session.setAttribute("confirmPassStatus", "OK");
							}else if(session.getAttribute("changeSetStatus")=="NotOK" 
									&& session.getAttribute("printStatus")=="OK"){
					%>	
								<p class="changeSetWarning">Change unsuccessful!!!</p>
					<%
								session.setAttribute("printStatus", "NotOK");
							}else if(session.getAttribute("changeSetStatus")=="OK"
									&& session.getAttribute("printStatus")=="OK"){
					%>
								<p class="changeSetWarning">Change successful!!!</p>
					<%
								session.setAttribute("printStatus", "NotOK");
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