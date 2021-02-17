function redirectToSignUpPage()
{
	 
	
	  $.ajax(
       {
        url  : "./signup",
        contentType: "application/json",
        type:"GET",
       
        contentType: false,
        cache: false,
        processData: false,
        success: function(response){
	         
             window.location.replace("./signup");
	     }
});

}
function registerUser()
{


 var pass = document.getElementById("pass").value;
 var username = document.getElementById("username").value;

if(validateCredentials())

{
	  document.getElementById("registerError").innerHTML="";
      var registerData = {
			"userid": username, 
	        "password": pass
           
  		   }
       

     $.ajax(
       {
        url  : "http://localhost:8080/VSToDoList/register",
       headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
			
        },
        type:"POST",
        data : JSON.stringify(registerData),
        contentType: 'application/json',
        cache: false,
        processData: false,
        success: function(response){
	         if(response.statusCode == "0")
	        { 
		      document.getElementById("registerError").innerHTML="";
			 alert("Successfully registered. Please Sign In now.");
             console.log(response);
			 window.location.replace("http://localhost:8080/VSToDoList/");
             }
            else if(response.statusCode == "1")
            {
	       
	         document.getElementById("registerError").innerHTML="User Name already Exists.";
            }
            else 
            {
	          document.getElementById("registerError").innerHTML="Please try again later";
            }
	     }
});
}
else
{
	document.getElementById("registerError").innerHTML="Username/Password cannot be empty.!";
}
}

function validateCredentials()
{
	var pass = document.getElementById("pass").value;
     var username = document.getElementById("username").value;
     if(pass == null || pass=="" || username == null || username =="")
     {
	     return false;
      }
    return true;
	
}


function login()
{


 var pass = document.getElementById("pass").value;
 var username = document.getElementById("username").value;
 document.getElementById("registerError").innerHTML="";
if(validateCredentials())

{
      var data = {
			"userid": username, 
	        "password": pass
		   }
     $.ajax(
       {
        url  : "http://localhost:8080/VSToDoList/validateUser",
       headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST",
        data : JSON.stringify(data),
        contentType: 'application/json',
        cache: false,
        processData: false,
        success: function(response){
	         if(response.statusCode == "0")
	        { 
		     document.getElementById("registerError").innerHTML="";
			 alert("Successfully logged in ");
             console.log(response);
			 window.location.replace("http://localhost:8080/VSToDoList/home");
             }
            else if(response.statusCode == "1")
            {
	         
	         document.getElementById("registerError").innerHTML="Invalid Credentials";
            }
            else 
            {
	         document.getElementById("registerError").innerHTML="Please try again later";
            }
	     }
});
}
else
{
	document.getElementById("registerError").innerHTML="Username/Password cannot be empty.!";
	
}
}




 