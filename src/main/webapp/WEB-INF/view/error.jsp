<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<style>
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
</style>
<body>
	<div>
		<ul class="form-style-1">
		    <li>
		        <h2>Weather Error Page</h2>
		        <br></br>
		    </li>
		    <li>
		        <label>Something went wrong when consulting the weather service: </label>
		        <textarea name="json" class="field-long field-textarea">${message}</textarea>
		    </li>
		    <li>
		        <form action="/">
				    <input type="submit" value="home" />
				</form>
		    </li>
		</ul>
	</div>
</body>
</html>
