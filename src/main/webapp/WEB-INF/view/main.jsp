<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">

<style>
* {
  box-sizing: border-box;
}

input[type=text], select, textarea {
  width: 50%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

input[type=submit] {
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

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

.col-25 {
  float: left;
  width: 15%;
  margin-top: 6px;
}

.col-75 {
  float: left;
  width: 35%;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 50%;
    margin-top: 0;
  }
}
</style>
<body>
<div class="container">
	<form  action="/weather" method="get" class="">
		 
        <h2>Weather Website</h2>
    
        <hr>
     
		 <div class="row">
		    <div class="col-25">
		      <label for="country">Select you city:</label>
		    </div>
		    <div class="col-75">
		      <select id="city" name="city"> 
		      	<option value="London">London</option>
				<option value="Hong+Kong">Hong Kong</option>
		      </select>
		    </div>
		  </div>   
		  <br/> 
		  <div class="row">
		    <input type="submit" value="Submit">
		  </div>
	</form>
</div>
</body>
</html>
