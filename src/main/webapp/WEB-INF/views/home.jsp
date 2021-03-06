<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-2.2.0.min.js"
		type="text/javascript"></script>
<title>VS Credit Tracker</title>
<script>
function validate(evt) {
	  var theEvent = evt || window.event;

	  // Handle paste
	  if (theEvent.type === 'paste') {
	      key = event.clipboardData.getData('text/plain');
	  } else {
	  // Handle key press
	      var key = theEvent.keyCode || theEvent.which;
	      key = String.fromCharCode(key);
	  }
	  var regex = /[0-9]|\./;
	  if( !regex.test(key) ) {
	    theEvent.returnValue = false;
	    if(theEvent.preventDefault) theEvent.preventDefault();
	  }
	}
</script>

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
		<img src="<c:url value="/resources/img/creditcard.jpg"/>"> <span class="logo-name">VS Credit Tracker</span>
	</div>
    <div class="menu-bar-div">
	<nav class="menu-bar">

		<ul>
			
			<li><a  id="featuredCars" onClick="displayAddCreditForm()">Add Credit Card</a></li>
			<li><a  href="<c:url value="/home" />" >View Credit Cards</a></li>
			
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
      <th class="th-sm">Edit

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
       <th>Edit
      </th>
         
    </tr>
    </tfoot>
    </table>
</div>

<div class="form-style-5" id="addForm" width="50%">
<form>
<fieldset>
<legend><span class="number">1</span> Add New Credit Card</legend>
<input type="text" id="creditCardNumber" pattern="\d*" onkeypress='validate(event)' maxlength="19" name="field1"  placeholder="Your Credit Card Number *">
<input type="text"id="creditCardMerchant" name="field2" maxlength="30" placeholder="Credit Card Merchant *">
<input type="text" id="creditCardLimit" name="field3" onkeypress='validate(event)' maxlength="12" placeholder="Credit Card Limit *">
<label for="job">Credit Card Expiry:</label>
<input type="month" id="start" name="start"
       min="2021-02" value="2021-02">
<br>

 <span id="creditError" class="creditError">
 </span>
<br>
</fieldset>

<input type="button" onClick="addCreditCard()" value="Add Credit Card" />
</form>
</div>

<div class="form-style-5" id="editForm" width="50%">
<form>
<fieldset>
<legend><span class="number">1</span> Edit Credit Card</legend>
<input type="text" id="creditCardNumberEdit"  name="field1" disabled>
<input type="text"id="creditCardMerchantEdit" name="field2" maxlength="30" disabled>
<input type="text" id="creditCardLimitEdit" name="field3" disabled>
<label for="job">Credit Card Expiry:</label>
<input type="month" id="editstart" name="start"
       min="2021-02" value="2021-02">
<br>

 <span id="creditEditError" class="creditEditError">
 </span>
<br>
</fieldset>

<input type="button" onClick="editCreditCard()" value="Edit Credit Card" />
</form>
</div>

<!-- about us section-->	
	<div class="index-about-us" id="about-us">
		<hr class="ihorizontal-line-start">
		
		<h2 class="featured-car-heading">ABOUT US</h2>
		<hr class="ihorizontal-line-end">

		<div class="about-us-content" id="about-us-content">
			<p>Founded by Vinit Sawant, VS Credit Tracker is a website which helps you to keep track of all the 
	          credit cards.</p>
			<br>
			<p>VS Credit Tracker is a pet project to demonstrate the learnings from the founders's educational as well as work experience</p>
			<br>
			<p>Technology used: JAVA8, Spring 5, Hibernate 5, HSQL etc.</p>
		</div>
	</div>
<!-- contact us section-->	
	<div class="index-contact-us" id="contact-us">
		<hr class="horizontal-line-start">
		<h2 class="featured-car-heading">CONTACT US</h2>
		<hr class="horizontal-line-end">
	</div>

	<div class="contact-us-content" id="contact-us-content">

		<div class="address">
			<h3>ADDRESS</h3>
			<p>
				NO. 3-4,<br> PORTLAND STREET NORTH, <br> DUBLIN 01.<br>
				D-01
			</p>

		</div>

		<div id="contact-numbers" class="contact-numbers">
			<h3>CONTACT NUMBERS</h3>
			<p>
				+353 33 333 3333 <br> <br> <br> <br>

			</p>
		</div>

		<div class="opening-hrs">
			<h3>OPENING HOURS</h3>

			<p>
				Mon-Thu : 09:00 - 17:30 <br> Fri : 09:00 - 17:00 <br> Sat
				: 10:00 - 15:00 <br> Sun : CLOSED
			</p>
		</div>
		<div class="email">
			<h3>EMAIL</h3>
			<p>vscredittracker@sales.ie</p>
			<br> <br> <br>

		</div>
	</div>
	<!-- footer section-->	
	<div class="footer">
		<p>Powered by VS &copy; 2021</p>
	</div>
	
	<!-- scripts -->	
</body>
</html>