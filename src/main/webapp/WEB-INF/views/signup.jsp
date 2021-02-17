<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
   
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
   
    
  
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome-4.7.0/css/font-awesome.min.css"/>">
 
 <script src="<c:url value="/resources/js/login.js"/>"></script>

  <title>Sign in</title>
  </head>
<body>
  <div class="main">
    <p class="sign" align="center">Sign up</p>
    <form class="form1">
      <input class="un " type="text" id="username" align="center" placeholder="Username"  maxlength="30">
      <input class="pass" type="password" id="pass" align="center" placeholder="Password"  maxlength="30">
    
    
      <a class="submit" onClick=" registerUser()" align="center">Sign up</a>
      <br>
      <br>
        <span id="registerError" class="errorLogin">
     </span>
      
         </form>       
    </div>
   
</body>

</html>

