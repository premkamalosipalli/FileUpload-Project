<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display the Database</title>
<style>
table {
  border-collapse: collapse;
}

table, td, th {
  border: 1px solid black;
}
</style>
</head>
<body style="background-color:silver">
	<form action="Welcome.html" method="post">
	        <h2>Click here to Go back: <input type="submit" value="Back"></h2>
	</form>
	<%
	Connection conn = null;
	Statement stmt = null;
	ResultSet resultset; 
	
	try {
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		stmt = conn.createStatement();
	} catch (ClassNotFoundException | SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	%>
	<table>
	<tr>
	<td>STLOC_ID</td>
	<td>IDENTIFIER</td>
	<td>PHONE</td>
	<td>FAX</td>
	<td>ADDRESS1</td>
	<td>ADDRESS2</td>
	<td>ADDRESS3</td>
	<td>CITY</td>
	<td>STATE</td>
	<td>COUNTRY</td>
	<td>ZIPCODE</td>
	<td>ACTIVE</td>
	</tr>
	<%
	resultset=stmt.executeQuery("select * from Source");
	while(resultset.next()){
		
	%>

	<tr>
	<td><%=resultset.getInt(1)%></td>
	<td><%=resultset.getString(2)%></td>
	<td><%=resultset.getString(3)%></td>
	<td><%=resultset.getString(4)%></td>
	<td><%=resultset.getString(5)%></td>
	<td><%=resultset.getString(6)%></td>
	<td><%=resultset.getString(7)%></td>
	<td><%=resultset.getString(8)%></td>
	<td><%=resultset.getString(9)%></td>
	<td><%=resultset.getString(10)%></td>
	<td><%=resultset.getString(11)%></td>
	<td><%=resultset.getString(12)%></td>
	</tr>
	
	<%}%>
	</table>
</body>
</html>