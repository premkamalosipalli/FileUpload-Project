<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Data Manipulation</title>
</head>
<body>
	<h1>Enter Your Data To Verify</h1>
	<form action="dataManipulationServlet" method="post" enctype="multipart/form-data">
		<table>
			<tr>
			<td>Enter the STLOC_ID:</td>
			<td><input type="text" name="stloc_id"/></td>
			</tr>
			<tr>
			<td>Enter the IDENTIFIER:</td>
			<td><input type="text" name="identifier"/></td>
			</tr>
			<tr>
			<td>Enter the PHONE:</td>
			<td><input type="text" name="phone"/></td>
			</tr>
			<tr>
			<td>Enter the FAX:</td>
			<td><input type="text" name="fax"/></td>
			</tr>
			<tr>
			<td>Enter the ADDRESS1:</td>
			<td><input type="text" name="address1"/></td>
			</tr>
			<tr>
			<td>Enter the ADDRESS2:</td>
			<td><input type="text" name="address2"/></td>
			</tr>
			<tr>
			<td>Enter the ADDRESS3:</td>
			<td><input type="text" name="address3"/></td>
			</tr>
			<tr>
			<td>Enter the CITY:</td>
			<td><input type="text" name="city"/></td>
			</tr>
			<tr>
			<td>Enter the STATE:</td>
			<td><input type="text" name="state"/></td>
			</tr>
			<tr>
			<td>Enter the COUNTRY:</td>
			<td><input type="text" name="country"/></td>
			</tr>
			<tr>
			<td>Enter the ZIPCODE:</td>
			<td><input type="text" name="zipcode"/></td>
			</tr>
			<tr>
			<td>Enter the ACTIVE:</td>
			<td><input type="text" name="active"/></td>
			</tr>
		</table>		
	</form>
</body>
</html>