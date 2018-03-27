<html>
<head>
<title>Help Page</title>
</head>
<header><%@include file = "header.jsp" %></header>
<body>
<form action="cs" method="post" name="helpForm">
<table>
<tr><td>1 - Login</td></tr>
<tr><td>2 - Select the product checkout</td></tr>
<tr><td>3 - Provide the quantity</td></tr>
<tr><td>4 - Add the items to cart</td></tr>
<tr><td>5 - Proceed to checkout</td></tr>
<tr><td>6 - Confirm payment</td></tr>
</table>

<input type="submit" name="action" value="Back To Cart">
<input type="hidden" name="page" value="help">
</form>
</body>
<footer><%@include file = "footer.jsp" %></footer>
</html>