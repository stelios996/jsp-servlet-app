<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>User Profile</title>
		<link rel="stylesheet" type="text/css" href="css/styleProfilePage.css">
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
		
		<div class="leftInfo">
			<div id="photo">
				<img src='<%= (String)session.getAttribute("p_FriendImagePath") %>' height="180px" width="150px">
			</div>
			<div id="info">
				<div id="name">
					<em>Name:</em>
					${p_name}	
				</div>
				
				<div id="surname">
					<em>Surname:</em>
					${p_surname}	
				</div>
				
				<div id="age">
					<em>Age:</em>
					${p_age}
				</div>
				
				<div id="address">
					<em>Address:</em>
					${p_address}	
				</div>
				
				<div id="telephone">
					<em>Telephone:</em>
					${p_telephone}	
				</div>
				
				<div id="email">
					<em>Email:</em>
					${p_email}	
				</div>

			</div>
		
		</div>
		
		<%
			try{
				String buttonStatus = (String) session.getAttribute("buttonStatus");
				
				if(buttonStatus=="Add_Friend"){
					//int id = (int) session.getAttribute("id");
					//String servl = "AddFriend?id="+id;
		%>
					<div id="addFriend"><a href= ${Servl} id="addFriendButton"> Add Friend </a></div>
		<%
				}else if(buttonStatus=="Pending"){
		%>
					<div id="pending">Pending</div>
		<%
				}else if(buttonStatus=="Friends"){
		%>
					<div id="friends">Friends</div>
		<%
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		%>

		<div class="rightInfo">
			<div id="employHistory">
				<strong>Employment History</strong><br/>
			</div>
			<pre>${p_workHistory}</pre> <br/>
			<pre>${p_resTraining}</pre> <br/>	
				
			<div id="education">
				<strong>Education</strong> <br/>
			</div>
			<pre>${p_education}</pre> <br/>	
				
			<div id="profqual">
				<strong>Professional Qualifications</strong><br/>	
			</div>
			<pre>${p_profqual}</pre><br/>
				
			<div id="computer">
				<strong>Computer Skills</strong><br/>
			</div>
			<pre>${p_computer}</pre> <br/>	
				
			<div id="other">
				<strong>Other Information</strong><br/>	
			</div>
			<pre>${p_other}</pre> <br/>
				
			<div id="interests">
				<strong>Interests</strong><br/>	
			</div>
			<pre>${p_interests}</pre>
				
		</div>
	</body>
</html>