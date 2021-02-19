<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-2.2.0.min.js"
		type="text/javascript"></script>
<title>VS Credit Tracker</title>

<!-- css  -->


<link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache,no-store ,must-revalidate");
%>

<div class="logo-banner">
		<img src="<c:url value="/resources/img/todoicon.png"/>"> <span class="logo-name">VS Credit Tracker</span>
	</div>
    <div class="menu-bar-div">
	<nav class="menu-bar">

		<ul>
			
			<li><a href="#" id="featuredCars" onClick="displayToDoForm()">Add Credit Card</a></li>
			<li><a href="#" onClick="viewActiveToDo()" >View Credit Cards</a></li>
			
			<li><a   href="<c:url value="/logout" />">Log Out</a></li>
		</ul>
	</nav>
	</div>
</body>
</html>