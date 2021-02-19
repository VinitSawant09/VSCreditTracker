package com.vscredittracker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vscredittracker.model.User;
import com.vscredittracker.service.UserService;
import com.vstodolist.dao.UserDAO;
import com.vstodolist.model.LoginVO;
import com.vscredittracker.model.OutputVO;


@CrossOrigin
@Component
@Controller
@Scope("session")
public class CreditTrackerController {

	@RequestMapping(value = "/",method = RequestMethod.GET)  
	
	 public String landing()
	 {  
	    System.out.println("Inside landing method of CreditTrackerController");
	    System.out.println("Redirecting to login page.!!");
       return "index";  
    }  
	
	 @RequestMapping(value = "/signup",method = RequestMethod.GET)  
		
	 public String signup()
	 {  
	    System.out.println("Inside signup method of CreditTrackerController");
	    System.out.println("Redirecting to Sign up page.!!");
        return "signup";  
     } 
	 
	 @RequestMapping(value = "/registerUser", method = { RequestMethod.GET, RequestMethod.POST })  
     @ResponseBody
	 public OutputVO registerUser(@RequestBody User  objUser,HttpServletRequest request)
	 {
		System.out.println("Inside registerUser method of CreditTrackerController");
        UserService user= new UserService();
        OutputVO lOutputVO = new OutputVO();
        boolean result = false;
        try
        {
	        String userId=objUser.getUserid();
	        String password = objUser.getPassword();
	        
	        if(validatePassword(password) && validateUserId(userId))
	        {
	        	result = user.registerUser(objUser);
		        System.out.println(result);
		        
		        if (result)
		        {
		        	lOutputVO.setStatus("Success creating new User.!!");
		        	lOutputVO.setStatusCode("0");
		        }
		        else
		        {
		        	lOutputVO.setStatus("User Name Already Exists/ Failure Creating User");
		        	lOutputVO.setStatusCode("1");
		        	
		        }
		    }
        }
        catch(Exception e)
        {
        	lOutputVO.setStatus("Failure Creating User");
        	lOutputVO.setStatusCode("1");
        }
        
		System.out.println("End of registerUser method of CreditTrackerController");
        return lOutputVO; 
         
    }  
	 
	 
 @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)  
	 
     @ResponseBody
	 public OutputVO login(@RequestBody User  objUser,HttpServletRequest request)
	 {
		 System.out.println("Inside login method of CreditTrackerController");
		 
        UserService user= new UserService();
       
        OutputVO lOutputVO = new OutputVO();
        
        try 
        {
	        String userId=objUser.getUserid();
	        String password = objUser.getPassword();
	        
	        if(validatePassword(password) && validateUserId(userId))
	        {
	        boolean result = user.validateLogin(objUser);
	        System.out.println(result);
	       
	        if (result)
	        {
	        	lOutputVO.setStatus("Success.!!");
	        	lOutputVO.setStatusCode("0");
	        	int id = 0;
	        	id= (int)ud.getUserId(objUser);
	        	request.getSession().setAttribute("userId", id);
	        	System.out.println("Actual user id is :"+id);
	        }
	        else
	        {
	        	lOutputVO.setStatus("Invalid Credentials.!!");
	        	lOutputVO.setStatusCode("1");
	        	
	        }
	        }
        }
        catch(Exception e)
        {
        	lOutputVO.setStatus("Error Logging In");
        	lOutputVO.setStatusCode("1");
        }
        return lOutputVO; 
    }  
	 
	 public boolean validateUserId(String userid)
	 {
		 if(userid==null || userid.equals(""))
		 {
			 return false;
		 }
		 return true;
	 }
	 
	 public boolean validatePassword(String pass)
	 {
		 if(pass==null || pass.equals(""))
		 {
			 return false;
		 }
		 return true;
	 }
}
