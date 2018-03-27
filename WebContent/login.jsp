<%@page
	import="java.util.*, com.shopping.service.*, com.shopping.domain.*, com.shopping.web.*"%>
<html>
<head>
<title>Login Page</title>
</head>
<header><%@include file="header.jsp" %></header>
<body>
	<form name="LoginForm" method="post" action="cs">

		<%
			String invalidUser = (String) request.getAttribute("invalidUser");
			if (invalidUser != null) {
		%>

		<h3 style="color:red;"><%=invalidUser%></h3>


		<%
			}
		%>

			Username: <input type="text" name="username" /> 
			Password: <input type="password" name="password" /> 
			<input type="submit" name="submit" value="Login" /> 
			<input type="reset" value="Reset" /> 

			<input type="hidden" name="page" value="login" />
	</form>
</body>
<footer><%@include file="footer.jsp" %></footer>
</html>