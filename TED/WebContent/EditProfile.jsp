<!DOCTYPE html>

<html>
	<head>
		<title>Edit Profile</title>
		<link rel="stylesheet" type="text/css" href="css/styleEditProfile.css">
	</head>
	<body>
		<div id="title">Edit your profile information</div>
		<div id="link"><a href="ServletProfile">Back</a></div>

		<div class="content">
			<form action="ServletEditProfile" method="post">
				<em>(Check the checkbox if you want the information to be public)</em><br/>
				<label for="name">Name: </label>
				<input type="text" id="name" name="name">			
				<br/>
				<label for="surname">Surname: </label>
				<input type="text" id="surname" name="surname">
				<br/>
				<label for="age">Age:</label>
				<input type="text" id="age" name="age">
				<input type="checkbox" id="ageR" name="ageR" value="selected">
				<br/>
				<label for="address">Address:</label>
				<input type="text" id="address" name="address">
				<input type="checkbox" id="addressR" name="addressR" value="selected">
				<br/>
				<label for="telephone">Telephone:</label>
				<input type="text" id="telephone" name="telephone">
				<input type="checkbox" id="telephoneR" name="telephoneR" value="selected">
				<br/>
				<label for="email">Email:</label>
				<input type="text" id="email" name="email">
				<input type="checkbox" id="emailR" name="emailR" value="selected">
				<br/>
			
				<strong>Employment History:</strong><br/>
				<em>(List in chronological order, include position details and dates)</em><br/>
				<label for="workHistory">Work History:</label><br/>
				<textarea id="workHistory" name="workHistory" rows="5" cols="65"> </textarea>
				<input type="checkbox" id="workHistoryR" name="workHistoryR" value="selected">
				<br/>
				<label for="ResearchTraining">Research/Training:</label><br/>
				<textarea id="ResearchTraining" name="ResearchTraining" rows="5" cols="65"></textarea>
				<input type="checkbox" id="reasearchR" name="reasearchR" value="selected">
				<br/>
			
				<label for="university"><strong>Education:</strong></label><br/>
				<em>(Include dates, majors, and details of degrees, training and certification)</em><br/>
				<textarea id="university" name="university" rows="5" cols="65"> </textarea>
				<input type="checkbox" id="universityR" name="universityR" value="selected">
				<br/>
			
				<label for="profqual"><strong>Professional Qualifications:</strong></label><br/>
				<em>(Certifications and Accreditations)</em><br/>
				<textarea id="profqual" name="profqual" rows="5" cols="65"> </textarea>
				<input type="checkbox" id="profqualR" name="profqualR" value="selected">
				<br/>
				
				<label for="computer"><strong>Computer skills:</strong></label><br/>
				<textarea id="computer" name="computer" rows="5" cols="65"> </textarea>
				<input type="checkbox" id="computerR" name="computerR" value="selected">
				<br/>
			
				<label for="other"><strong>Other Information:</strong></label><br/>
				<textarea id="other" name="other" rows="5" cols="65"></textarea>
				<input type="checkbox" id="otherR" name="otherR" value="selected">
				<br/>
			
				<label for="interests"><strong>Interests:</strong></label><br/>
				<textarea id="interests" name="interests" rows="5" cols="65"></textarea>
				<input type="checkbox" id="interestsR" name="interestsR" value="selected">
				<br/>
			
				<input type="submit" value="Submit" id="submitButton" name="submitButton">
				<br/><br/><br/>
			</form>
		</div>
	</body>
</html>