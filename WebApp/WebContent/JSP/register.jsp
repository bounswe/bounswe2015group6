<%@ page language="java" import = "com.web.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<form action="/WebApp/TestServlet" method = "POST">
	NAME: <input type = "text" name = "name">
	PASSWORD: <input type="text" name= "pass"><br></br>
	<input type="submit" value = "Register">
	</form>
	<br></br>
	
	<%
		java.util.ArrayList<User> st = new TestServlet().testJndiDataSource();
	%>
	<table>
	<tr>
		<td>USERNAME</td>
		<td>PASSWORD</td>
	</tr>
	<%for(User temp: st){%>
	<tr>
		<td><%=temp.name %></td>
		<td><%=temp.pass%>
	</tr>
	<%} %>
	</table>
	
</body>
</html>