<!DOCTYPE html>

<html>
	<head>
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="css/styleHomePage.css">
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
		<div class="leftside">
			<div id="photoProf"> </div>
		
			<div id="upperleft"> </div>
			
			<div id="friends"> </div>
		</div>
		
		<div id="articlePost">
			<a href="javascript:void(0)" id="postLink" onclick="toggle_visibility('popup-post')"><em>Post new article...</em></a>
		</div>
		
		<div id="timeline">	
			
			<!-- <p><%= session.getAttribute("apicName") %></p><br> -->
			<!--<p><%= session.getAttribute("exceptionImage") %></p><br>-->
			<!--<p><%= session.getAttribute("avideoName") %></p><br>-->
			<!--<p><%= session.getAttribute("exceptionVideo") %></p><br>-->
			<!--<p><%= session.getAttribute("a_picName") %></p><br>-->
			<!--<p><%= session.getAttribute("a_videoName") %></p><br>-->
			<!--<p><%= session.getAttribute("example") %></p><br>-->
			<!--<p><%= session.getAttribute("1") %></p><br>-->
			<!--<p><%= session.getAttribute("2") %></p><br>-->
		</div>
		
		<div id="showUserLikes" style="display:none;"> </div>
		
		<div id="popup-post">
			<div id="popup-wrapper">
				<div id="popup-container">
					 <p style="text-align:right;margin:0;">
						<a href="javascript:void(0)" id="popupClose" onclick="toggle_visibility('popup-post')">x</a>
					</p>
					<p id="postTitle"><em>Create New Article</em></p>
					<form action="ArticlePostServlet" method="post" enctype="multipart/form-data">
						<label for="postText">Text: </label><br/>
						<textarea id="postText" name="postText" rows="5" cols="65"> </textarea><br/>

						<label for="postImage">Image:</label><br/>
						<input type="file" id="postImage" name="postImage" accept="image/*"><br/>

						<label for="postVideo">Video:</label><br/>
						<input type="file" id="postVideo" name="postVideo" accept="video/*"><br/>
						
						<input type="submit" value="Submit" id="submitPost" name="submitButton">
						<br/><br/>
					</form>
				</div>
			</div>
		</div>
		
		
	
		<script>
			(
				function photoProf(){
					var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							var out ="<img src='"+this.responseText+"' height='180px' width='150px'>";
							
							document.getElementById("photoProf").style.display = "block";
				    	  	document.getElementById("photoProf").innerHTML = out;
						}
					};
					var id = +document.getElementById("iduser").value;
					xhttp.open("GET", "HomeImageProf?id="+id, true);
					xhttp.send();
				}	
			)();
		
		
			(
				function upperleft(){
					var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							var JSONlist = JSON.parse(this.responseText);
						    var out = //"<div id='photoProf'>"
									//		+"photo here"
											//+"<img src='css/images/generic_image.jpg' height='180px' width='150px'>"
									//	+"</div>"
									//+
										"<div id='infoProf'>"
										+"<div id='name'>"
											+"<em>Name:</em>"
									 		+JSONlist[0].name
										+"</div>"
								
										+"<div id='surname'>"
											+"<em>Surname:</em>"
											+JSONlist[0].surname
										+"</div>"
								
										+"<div id='age'>"
											+"<em>Age:</em>"
											+JSONlist[0].age
										+"</div>"
								
										+"<div id='address'>"
											+"<em>Address:</em>"
											+JSONlist[0].address
										+"</div>"
								
										+"<div id='telephone'>"
											+"<em>Telephone:</em>"
											+JSONlist[0].telephone
										+"</div>"
								
										+"<div id='email'>"
											+"<em>Email:</em>"
											+JSONlist[0].email
										+"</div>"
									+"</div>"
									+"<div id='linkProf'>"
										+"<a href='ServletProfile' id='myProf'>My Profile</a>"
									+"</div>"
									+"<div id='linkNet'>"
										+"<a href='ServletNetwork' id='myNet'>My Network</a>"
									+"</div>";
						    document.getElementById("upperleft").style.display = "block";
				    	  	document.getElementById("upperleft").innerHTML = out;
						}
					};
					var id = +document.getElementById("iduser").value;
					xhttp.open("GET", "UpperLeftHome?id="+id, true);
					xhttp.send();
				}
			)();
			
			(
				function lowerleft(){
					var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							var JSONlist = JSON.parse(this.responseText);
						    var out = "<p id='ftitle'>Friends</p><hr>"
									+"<div id='friendlist'>";
									+"<ul>"
							var i;
							for(i=0; i<JSONlist.length; i++){
								out += "<li>"+JSONlist[i].name +" "+JSONlist[i].surname+"</li>";
							}		
							out += "</ul>";
							out += "</div>";
						    document.getElementById("friends").style.display = "block";
				    	  	document.getElementById("friends").innerHTML = out;
						}
					};
					var id = +document.getElementById("iduser").value;
					xhttp.open("GET", "LowerLeftHome?id="+id, true);
					xhttp.send();
				}		
			)();
			
			
			(
				function timeline(){
					var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							var JSONlist = JSON.parse(this.responseText);
							var id = +document.getElementById("iduser").value;
							
							var out = "";
							var i;
							for(i=JSONlist.length-1; i>=0; i--){
								/*out += "articleID = "+JSONlist[i].articleID+" <br>"
										+"textFlag = "+JSONlist[i].textFlag+" <br>"
										+"text = "+JSONlist[i].text+" <br>"
										+"imageFlag = "+JSONlist[i].imageFlag+" <br>"
										+"image = "+JSONlist[i].image+" <br>"
										+"videoFlag = "+JSONlist[i].videoFlag+" <br>"
										+"video = "+JSONlist[i].video+" <br>"
										+"userID = "+JSONlist[i].userID+" <br>"
										+"userName = "+JSONlist[i].userName+" <br>"
										+"userSurname = "+JSONlist[i].userSurname+" <br><br>";*/
								out += "<div id='article'>"
											+"<div id='user'>"
												+"<p id='username'>"+JSONlist[i].userName+" "+JSONlist[i].userSurname+"</p>"
												+"<input type='hidden' name='articleid' id='articleid' value='"+JSONlist[i].articleID+"'>"
											+"</div>"
											+"<div id='articleContent'>";
											if(JSONlist[i].textFlag==1){
												out+="<div id='text'>"
														+JSONlist[i].text
													+"</div>";
											}
											if(JSONlist[i].imageFlag==1){
												out+="<img src='"+JSONlist[i].image+"' height='300px' width='510px'>";
											}
											if(JSONlist[i].videoFlag==1){
												out+="<video height='300px' width='510px' controls>"
														+"<source src='"+JSONlist[i].video+"' type='video/mp4'>"
													+"</video>";
											}
									out += 	"</div>"
											+"<div id='likes' name='article"+i+"Div'>";
												//+"<a href='LikeServlet?userID="+id+"&articleID="+JSONlist[i].articleID+"' id='likebutton'>"
												//+"<a href='javascript:;' onclick='likeArticle("+id+" , "+JSONlist[i].articleID+")' id='likebutton'>"
												if(JSONlist[i].likeFlag == 0){
													out+="<a href='javascript:;' id='likebutton' style='color:black;' onclick='likeArticle("+id+" , "+JSONlist[i].articleID+" , "+i+", 1)'>";
												}else if(JSONlist[i].likeFlag == 1){
													out+="<a href='javascript:;' id='likebutton' style='color:blue;' onclick='likeArticle("+id+" , "+JSONlist[i].articleID+" , "+i+", 2)'>";
												}
													out+="<p id='like' name='article"+i+"Like'>Like</p>"
												+"</a>"
											+"</div>"
											+"<div id='likeno' onmouseover='hoverOverLikes("+i+")' onmouseout='hoverOverLikes("+i+")'>"
												+"<img id='pic' src='css/images/LIKE.png' height='20px' width='20px'>"
												+"<p id='number' name='article"+i+"Number'>"+JSONlist[i].likes+"</p>"
											+"</div>"
											+"<div id='userslike' name='ar"+i+"' style='display:none;'>"
												+"<span>"
													+JSONlist[i].likerFullNames
												+"</span>"
											+"</div>"
									+"</div><br>"
									+"<div id='space'><p> <br></p></div>";
									
							}
							document.getElementById("timeline").style.display = "block";
				    	  	document.getElementById("timeline").innerHTML = out;
						}
					};
					var id = +document.getElementById("iduser").value;
					xhttp.open("GET", "TimelineHome?id="+id, true);
					xhttp.send();
				}	
				
			)();
			
			function likeArticle(userID, articleID, id, color){
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						var nameLike = "article"+id+"Like";
						var nameNumber = "article"+id+"Number";
						var nameDiv = "article"+id+"Div";
						
						var divInner = document.getElementsByName(nameDiv)[0];
						var number = document.getElementsByName(nameNumber)[0];
						if(color == 1 ){ //black->blue
							document.getElementsByName(nameLike)[0].style.color = "blue";
							divInner.innerHTML = "<a href='javascript:;' id='likebutton' style='color:blue;' onclick='likeArticle("+userID+" , "+articleID+" , "+id+", 2)'>"
												+"<p id='like' name='article"+id+"Like'>Like</p>"
												+"</a>";
							number.innerHTML = parseInt(number.innerHTML)+1;		
												
						}else if(color == 2 ){ //blue->black
							document.getElementsByName(nameLike)[0].style.color = "black";
							divInner.innerHTML = "<a href='javascript:;' id='likebutton' style='color:black;' onclick='likeArticle("+userID+" , "+articleID+" , "+id+", 1)'>"
												+"<p id='like' name='article"+id+"Like'>Like</p>"
												+"</a>";
							number.innerHTML = parseInt(number.innerHTML)-1;
						}
					}
				};
				xhttp.open("GET", "LikeServlet?userID="+userID+"&articleID="+articleID, true);
				xhttp.send();
			}
			
			
			function toggle_visibility(id){
				var x = document.getElementById(id);
				if(x.style.display=="block"){
					x.style.display="none";
				}else{
					x.style.display="block";
				}
			}
			
			function hoverOverLikes(number){
				var name = "ar"+number;
				var content = document.getElementsByName(name)[0].innerHTML;
				var elem = document.getElementById("showUserLikes");
				elem.innerHTML = content;
				if(elem.style.display == "none"){	
					elem.style.display= "block";
				}else {
					elem.style.display = "none";
				}
			}
		
		</script>
	</body>
</html>