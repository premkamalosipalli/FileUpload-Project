<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Data Manipulation</title>
</head>
<body style="background-color:silver">
	<h1 align="center">Enter Your Address To Verify</h1>
	<div align="center">
	<form action="fileUpload" method="post" enctype="multipart/form-data">
	     Select file to upload: <input type="file" name="uploadFile" /> <br />
		<table>
			<tr>
			<td>Enter the STLOC_ID:</td>
			<td><input type="text" id="stloc_id" name="stloc_id"/></td>
			</tr>
			<tr>
			<td>Enter the IDENTIFIER:</td>
			<td><input type="text" id="identifier" name="identifier"/></td>
			</tr>
			<tr>
			<td>Enter the PHONE:</td>
			<td><input type="text" id="phone" name="phone"/></td>
			</tr>
			<tr>
			<td>Enter the FAX:</td>
			<td><input type="text" id="fax" name="fax"/></td>
			</tr>
			<tr>
			<td>Enter the ADDRESS1:</td>
			<td><input type="text" id="address1" name="address1"/></td>
			</tr>
			<tr>
			<td>Enter the ADDRESS2:</td>
			<td><input type="text" id="address2" name="address2"/></td>
			</tr>
			<tr>
			<td>Enter the ADDRESS3:</td>
			<td><input type="text" id="address3" name="address3"/></td>
			</tr>
			<tr>
			<td>Enter the CITY:</td>
			<td><input type="text" id="city" name="city"/></td>
			</tr>
			<tr>
			<td>Enter the STATE:</td>
			<td><input type="text" id="state" name="state"/></td>
			</tr>
			<tr>
			<td>Enter the COUNTRY:</td>
			<td><input type="text" id="country" name="country"/></td>
			</tr>
			<tr>
			<td>Enter the ZIPCODE:</td>
			<td><input type="text" id="zipcode" name="zipcode"/></td>
			</tr>
			<tr>
			<td>Enter the ACTIVE:</td>
			<td><input type="text" id="active" name="active"/></td>
			</tr>
			<tr>
			<td colspan="5" align="center"><input type="submit" value="SUBMIT"/></td>
			</tr>
		</table>		
	</form>
	</div>
</body>
</html>