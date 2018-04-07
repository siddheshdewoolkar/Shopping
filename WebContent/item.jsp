<%@page
	import="java.util.*, com.shopping.service.*, com.shopping.domain.*"%>
<html>
<head>
<title>Items Page</title>
</head>
<header><%@include file="header.jsp" %></header>
<menu><%@include file="menu.jsp" %></menu>
<body>
	<form name="CartForm" method="post" action="cs">

		<h2>Welcome to the Shopping Website</h2>

		<%
		String itemsAddedMessage=(String)request.getAttribute("itemsaddedmessage");
		if(itemsAddedMessage!=null){
		%>
		
		<span style="color: red"><%=itemsAddedMessage %></span>
		
		<%
		}
		%>


		<%
			String addToCartMessage=(String)session.getAttribute("addToCartMessage");
			if(addToCartMessage!=null){
				out.println(addToCartMessage);
			}
		%>


		<%
			List<Item> items = (List<Item>) request.getAttribute("itemlist"); // was items --> itemlist
			if (items != null) {
		%>
		<table>
			<tr>
				<td></td>
				<td>ID</td>
				<td>Item</td>
				<td>Price</td>
				<td>Quantity</td>
			</tr>

			<%
				for (Item item : items) {
			%>

			<tr>
				<td><input type="checkbox" name="chkItem"
					value="<%=item.getId()%>" /></td>
				<td><%=item.getId()%></td>	
				<td><%=item.getName()%></td>
				<td><%=item.getPrice()%></td>
				<td><input type="text" name="qty<%=item.getId()%>" /></td>

			</tr>

			<%
				}
			%>

		</table>

		<%
			}
		%>


		<input type="submit" name="action" value="Checkout" />
		<input type="submit" name="action" value="Add to cart" /> 
 
		<input	type="hidden" name="page" value="item" />
	</form>
	
</body>
<footer><%@include file="footer.jsp" %></footer>
</html>