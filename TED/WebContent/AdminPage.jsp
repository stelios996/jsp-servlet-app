<!DOCTYPE html>

<html>
	<head>
		<title>Administrator</title>
		<link rel="stylesheet" type="text/css" href="css/styleAdmin.css">
	</head>
	<body>
		<div id="logo" class="header3">
			myLinkedIn
		</div>
		<div class="navbar">
			<div id="logout" class="navbar"><a href="ServletLogout" id="l">Logout</a></div>
		</div>
	
		<div id="message">Created files are stored in C:\UsersXML</div>
		<div id="mainDiv"> </div>
		
		<script>
			
			(
				function showAll(){
					var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							var JSONlist = JSON.parse(this.responseText);
							var out = "";
							
					      	if(JSONlist.length == 0){
							  	document.getElementById("mainDiv").style.display = "none";
							  	document.getElementById("mainDiv").innerHTML = "";
					      	}else{
					      		var i;
					    	  	for(i=0; i<JSONlist.length; i++){
					    	  		if(JSONlist[i].userID == 1){
					    	  			continue;
					    	  		}else{
					    	  			var visit = "ServletFriend?id="+JSONlist[i].userID;
					    	  			out += "<div id='user'>"
			    								+ "<div id='text'>"
													+ "<p><strong>"+JSONlist[i].name+" "+JSONlist[i].surname+"</strong></p>"
												+ "</div>"
												+"<a href='ServletFriend?id="+JSONlist[i].userID+"' id='visitProfile'>Visit</a>"
												+"<a href='CreateXML?id="+JSONlist[i].userID+"' id='downloadXML'>InfoXML</a>"
											+ "</div>";
					    	  		}
					    	  	}
					      		document.getElementById("mainDiv").style.display = "block";
					    	  	document.getElementById("mainDiv").innerHTML = out;
					      	}
						}
					};
					xhttp.open("GET", "AdminServlet", true);
					xhttp.send();
				}	
			)();
		
		</script>
		
	</body>
</html>