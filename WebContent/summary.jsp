<%@page import = "java.util.*, com.shopping.domain.Item, java.lang.*"%>
<html>
<head>
<title>Summary Page</title>
</head>
<header><%@include file = "header.jsp" %></header>
<menu><%@include file = "menu.jsp" %></menu>
<body>
<form action="cs" name="loginForm" method="post">
		<%
			Map<Item, Integer> cartItemPrice = (Map<Item, Integer>) session.getAttribute("cartitemprice");
			if (cartItemPrice != null) {
		%>

	<table>
		
		<tr>
			<th>Name</th>
			<th>Quantity</th>
			<th>Price</th>
		</tr>
		
				<%
					for (Map.Entry<Item, Integer> entry : cartItemPrice.entrySet()) {
				%>			
		
		<tr>
			<td><%=entry.getKey().getName() %></td>
			<td><%=entry.getValue().intValue() / entry.getKey().getPrice() %></td>
			<td><%=entry.getValue().intValue() %></td>
		</tr>
		
		
				<%	
					}
				%>			
		
		
		<%
			}
		%>
		
		<%
			int totalPrice = (int) session.getAttribute("carttotalprice");	
		%>
		
		<tr>
			<td>Grand Total:	</td>
			<td><%=totalPrice %></td>
		</tr>
	
	</table>
	
		
		
		
		<input type="submit" name="action" value="Back To Cart">
		<input type="submit" name="action" value="Checkout">
		
		<input type="hidden" name="page" value="summary" />
		
		



</form>
</body>
<footer><%@include file = "footer.jsp" %></footer>
</html>
