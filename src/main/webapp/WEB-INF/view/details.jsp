<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<style>
label{
	margin:0 0 3px 0;
	padding:0px;
	display:block;
	font-weight: bold;
}
table, td, th {  
  border: 1px solid #ddd;
  text-align: left;
  
}

table {
  border-collapse: collapse;
  width: 35%;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
  background-color: #4CAF50;
  color: white;
}

.button {
  background-color: #555555;
  border: none;
  color: white;
  padding: 12px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 13px;
  margin: 4px 2px;
  cursor: pointer;
}
</style>
<body>
	<div>
		 
		        <h2>Weather Details in  ${weather.cityName} </h2>
		    
		        <hr> 
		    
		    <table>
				  <tr>
				    <td><label><b>Date:</b></label></td>
				    <td><output name="currentDate">${weather.currentDate}</output></td> 
				  </tr>
				  <tr>
				    <td><label><b>City:</b></label></td>
				    <td><output name="city">${weather.cityName}</output></td>
				  </tr>
				  <tr>
				    <td><label><b>Weather description:</b></label></td>
				    <td><output name="desc">${weather.description}</output></td>
				  </tr>
				  <tr>
				    <td><label><b>Temperature in F°: </b></label> </td>
				    <td><output name="tempF">${weather.tempF}</output></td>
				  </tr>
				  <tr>
				    <td><label><b>Temperature in C°: </b></label> </td>
				    <td><output name="tempC">${weather.tempC}</output></td>
				  </tr>
				  <tr>
				    <td><label><b>Sunrise:</b></label></td>
				    <td><output name="sunrise">${weather.sunrise}</output></td>
				  </tr>
				  <tr>
				    <td><label><b>Sunset:</b></label></td>
				    <td><output name="sunset">${weather.sunset}</output></td>
				  </tr>
				</table>
		    
		    <br/>
		        <form action="/">
				    <input class="button" type="submit" value="home" />
				</form> 
	</div>
</body>
</html>
