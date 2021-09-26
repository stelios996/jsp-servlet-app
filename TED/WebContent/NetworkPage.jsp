<!DOCTYPE html>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
	<head>
		<title>Network</title>
		<link rel="stylesheet" type="text/css" href="css/styleNetworkPage.css">
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
		<div id="searchBarWrap" class="searchBar">
			<input type="text" placeholder="Search..." id="searchBarInp" onkeyup="showHint(this.value)">
			<div id="searchBarButton">
				<!-- <a href="" onclick=func() id="sBBlink">Search</a> -->
				<a href="ServletFriend?id=-1" id="sBBlink">Search</a>
			</div>
		</div>
		<div id="suggestions"> </div>
		<% 
			if(session.getAttribute("SearchStatus")=="NotOK"){
		%>
				<p class="searchWarning">No search input or search input does not exist!!!</p>
		<%
				session.setAttribute("SearchStatus","OK");
			}
		%>	
		<script>
			function showHint(str){
				var xhttp;
				while(str.indexOf(" ")==0){
					str = str.replace(/\s/, "");
				}
				if(str==""){
					document.getElementById("suggestions").style.display = "none";
					return;
				}else{
					xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
					      	var JSONlist = JSON.parse(this.responseText);
					      	var out = "";
					      	if(JSONlist.length == 0){
							  	document.getElementById("suggestions").style.display="none";
							  	document.getElementById("suggestions").innerHTML = "";
					      	}else{
					    	  	var i;
					    	  	for(i=0; i<JSONlist.length; i++){
					    	  		out += "<p id='para"+i+"' onclick=func('para"+i+"')>"+JSONlist[i].name+" "+JSONlist[i].surname+"<span>"+JSONlist[i].userID+"</span></p>";
					    	  	}
					    	  	document.getElementById("suggestions").style.display = "block";
					    	  	document.getElementById("suggestions").innerHTML = out;
					      	}
						}
					};
					xhttp.open("GET", "SearchServlet?nam="+str, true);
					xhttp.send();
				}
			}
				
			function func(str){
				var x = document.getElementById(str).innerHTML;
				var res1= x.split("<span>");
				var res2=res1[1].split("</span>");
				var id =document.getElementById("searchBarInp").value=res2[0];     	
				document.getElementById("searchBarInp").value=res1[0];				
				document.getElementById("searchBarButton").innerHTML="<a href='ServletFriend?id="+id+"' id='sBBlink'>Search</a>";
			}
		</script>
		
		<h1>Friends</h1>
		
		<div id="friendsContainer"> 
			<c:forEach items="${plist}" var="profile" varStatus="i">
				<div id="profDiv">
					<div id="photo">
						<img src="${imagelist[i.count]}" height="80px" width="70px">
						<!--<img src="css/images/generic_image.jpg" height="80px" width="70px">-->
						<!--<c:out  value="${imagelist[i.count]}" /><br>-->
					</div>
					<div id="rest">
   						<c:out  value="${profile.name}" />
   						<c:out  value="${profile.surname}" /><br><br>
   						<c:out  value="${profile.email}" /><br>
   					</div>
   					<div id="link">
						<a href="ServletFriend?id=${profile.profileID}" id="plink">Visit Profile</a>
					</div>
					<div id="chatlink">
						<a href="./ChatPage.jsp" id="clink">Chat</a>
					</div>
   				</div>
			</c:forEach>
		</div>
		
		<!--<c:forEach items="${imagelist}" var="image">
			<c:out  value="${image}" /><br>
		</c:forEach>-->
	</body>
</html>