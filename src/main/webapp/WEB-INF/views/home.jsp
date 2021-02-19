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

 <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.19.1/moment.min.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
 
<!-- css  -->
 <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
	  <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
	  <script src="<c:url value="/resources/js/home.js"/>"></script>
	  
</head>
<body onLoad="onLoad()">
<%
response.setHeader("Cache-Control","no-cache,no-store ,must-revalidate");
%>

	<div class="logo-banner">
		<img src="<c:url value="/resources/img/todoicon.png"/>"> <span class="logo-name">VS Credit Tracker</span>
	</div>
    <div class="menu-bar-div">
	<nav class="menu-bar">

		<ul>
			
			<li><a href="#" id="featuredCars" onClick="displayAddCreditForm()">Add Credit Card</a></li>
			<li><a href="#" onClick="viewActiveToDo()" >View Credit Cards</a></li>
			
			<li><a   href="<c:url value="/logout" />">Log Out</a></li>
		</ul>
	</nav>
	</div>
	<br>
	<br>
	<div id="allCreditCardTable" class="allCreditCardTable" >
    <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
        <thead>
        <tr>
       
      <th class="th-sm">Credit card number

      </th>
      <th class="th-sm">Merchant name

      </th>
      <th class="th-sm">Expiry Date

      </th>
      <th class="th-sm">Max Limit

      </th>
           
    </tr>
    </thead>
    <tbody id ="transactOrder">

    </tbody>
    <tfoot class="tfoot">
    <tr>
      
      <th>Credit card Number
      </th>
      <th>Merchant name
      </th>
      <th>Expiry Date
      </th>
      <th>Max Limit
      </th>
         
    </tr>
    </tfoot>
    </table>
</div>
</body>
</html>