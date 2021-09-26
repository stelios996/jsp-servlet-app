<!DOCTYPE html>

<html>
	<head>
		<title>Notifications</title>
		<link rel="stylesheet" type="text/css" href="css/styleNotificationsPage.css">
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

		<input type="hidden" name="idusername" id="iduser" value='<%=(int)session.getAttribute("user_id")%>'>		
		<div id="showFriendRequests"> </div>
		
		<div id="showLikes"> </div>

		<script>
			(
				function showFReq(){
					var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							var JSONlist = JSON.parse(this.responseText);
					      	var out = "";
					      	if(JSONlist.length == 0){
							  	document.getElementById("showFriendRequests").style.display="none";
							  	document.getElementById("showFriendRequests").innerHTML = "";
					      	}else{
					      		var userid = +document.getElementById("iduser").value;
					      		var i;
					    	  	for(i=0; i<JSONlist.length; i++){
					    	  		out += "<div id='notific'>"
					    						+ "<div id='text'>"
													+ "<p>Friend Request from <strong>"+JSONlist[i].name+" "+JSONlist[i].surname+"</strong></p>"
												+ "</div>"
												+ "<button type='button' id='accept' onclick=reqHandler("+JSONlist[i].userID+","+userid+",0)>Accept</button>"
												+ "<button type='button' id='decline' onclick=reqHandler("+JSONlist[i].userID+","+userid+",1)>Refuse</button>"
											+ "</div>";
					    	  	}
					    	  	document.getElementById("showFriendRequests").style.display = "block";
					    	  	document.getElementById("showFriendRequests").innerHTML = out;
					      	}
						}
					};
					var id = +document.getElementById("iduser").value;
					xhttp.open("GET", "NotificationServlet?id="+id, true);
					xhttp.send();
					//document.getElementById("showFriendRequests").innerHTML = "THE ID IS : "+id1;
				}	
			)();
			
			function reqHandler(user1, user2, reqStatus){
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						window.location = "./NotificationsPage.jsp";
					}
				};
				xhttp.open("GET", "FriendRequestHandler?user1="+user1+"&user2="+user2+"&status="+reqStatus, true);
				xhttp.send();
			}
		
			(
				function showLikes(){
					var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							var JSONlist = JSON.parse(this.responseText);
					      	var out = "";
					      	if(JSONlist.length == 0){
							  	document.getElementById("showLikes").style.display="none";
							  	document.getElementById("showLikes").innerHTML = "";
					      	}else{
					      		var userid = +document.getElementById("iduser").value;
					      		var i;
					    	  	for(i=0; i<JSONlist.length; i++){
					    	  		if(JSONlist[i].userID != userid){
					    	  			out += "<div id='notific'>"
			    								+ "<div id='text'>"
													+ "<p>User <strong>"+JSONlist[i].name+" "+JSONlist[i].surname+"</strong> liked your post</p>"
												+ "</div>"
											+ "</div>";
					    	  		}
					    	  	}
					      		document.getElementById("showLikes").style.display = "block";
					    	  	document.getElementById("showLikes").innerHTML = out;
					      	}
						}
					};
					var id = +document.getElementById("iduser").value;
					xhttp.open("GET", "GetLikeNotificationServlet?id="+id, true);
					xhttp.send();
				}
			)();
			
		</script>
	</body>
</html>