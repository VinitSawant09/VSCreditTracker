 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
   
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <script src="<c:url value="/resources/js/login.js"/>"></script>
    
  
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome-4.7.0/css/font-awesome.min.css"/>">
  <title>VS Credit Tracker</title>
  </head>
<body>
  <div class="main">
    <p class="sign" align="center">Sign in</p>
    <form class="form1">
      <input class="un " id="username" type="text" align="center" placeholder="Username" maxlength="30">
      <input class="pass" id="pass" type="password" align="center" placeholder="Password" maxlength="30">
      <a class="submit" align="center" onClick="login()">Sign in</a>
      <br>
      
       <p class="newuser" align="center"><a onClick="redirectToSignUpPage()">New User?</p>
       <br>
       
       <span id="registerError" class="errorLogin">
     </span>
      
      </form>          
    </div>
   
</body>

</html>

