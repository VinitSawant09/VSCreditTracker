package com.vscredittracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.vscredittracker.model.CreditCard;
import com.vscredittracker.model.OutputVO;


@CrossOrigin
@Component
@Controller
public class CreditTrackerController {
	
	@Autowired
	UserService objUserService;
	

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
	 
	 @RequestMapping(value = "/home",method = RequestMethod.GET)  
	 
	 public String home(HttpServletRequest request)
	 {  
	    System.out.println("Inside home method of CreditTrackerController");
	    
	    try
	    {
	    	if(request.getSession().getAttribute("id")!="0" && request.getSession().getAttribute("id")!="" )
			{
	    		System.out.println(request.getSession().getAttribute("id"));
	    	    System.out.println("Redirecting to Home page.!!");
	    	    return "home";
			}
	    	
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    }
	    System.out.println("End home method of CreditTrackerController");
	   
         return "401";
     } 
	 
 @RequestMapping(value = "/adminHome",method = RequestMethod.GET)  
	 
	 public String adminhome(HttpServletRequest request)
	 {  
	    System.out.println("Inside adminhome method of CreditTrackerController");
	    
	    try
	    {
	    	if(request.getSession().getAttribute("id")!="0" && request.getSession().getAttribute("id")!="" )
			{
	    		System.out.println(request.getSession().getAttribute("id"));
	    	    System.out.println("Redirecting to adminhome page.!!");
	    	    return "adminHome";
			}
	    	
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    }
	    System.out.println("End adminhome method of CreditTrackerController");
	   
         return "401";
     } 
	 
	 
	 @RequestMapping(value = "/logout",method = RequestMethod.GET)  
	
	 public String logout(HttpServletRequest request)
	 {  
	    System.out.println("Inside logout method of CreditTrackerController");
	    System.out.println("Redirecting to login page !!");
	    request.getSession().setAttribute("id","0");
	    System.out.println("End logout method of CreditTrackerController");
        return "index";  
     }  
	 
	 @RequestMapping(value = "/registerUser", method = { RequestMethod.POST })  
     @ResponseBody
	 public OutputVO registerUser(@RequestBody User  objUser,HttpServletRequest request)
	 {
		System.out.println("Inside registerUser method of CreditTrackerController");
       
        OutputVO lOutputVO = new OutputVO();
        boolean result = false;
        try
        {
	        String userId=objUser.getUserid();
	        String password = objUser.getPassword();
	        
	        if(validatePassword(password) && validateUserId(userId))
	        {
	        	result = objUserService.registerUser(objUser);
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
	 
	 
 @RequestMapping(value = "/login", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)  
	 
     @ResponseBody
	 public OutputVO login(@RequestBody User  objUser,HttpServletRequest request)
	 {
		System.out.println("Inside login method of CreditTrackerController");
		 
        
       
        OutputVO lOutputVO = new OutputVO();
        
        try 
        {
	        String userId=objUser.getUserid();
	        String password = objUser.getPassword();
	        
	        if(validatePassword(password) && validateUserId(userId))
	        {
	        boolean result = objUserService.validateLogin(objUser);
	        System.out.println(result);
	       
	        if (result)
	        {
	        	lOutputVO.setStatus("Success.!!");
	        	lOutputVO.setStatusCode("0");
	        	if(userId.equals("admin") && password.equals("admin"))
	        	{
	        		
	        		lOutputVO.setCreditCardList(objUserService.getAllCreditCardAfterUpdation());
	        	}
	        	int id = 0;
	        	id= (int)objUserService.fetchUserId(objUser);
	        	request.getSession().setAttribute("id", id);
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
        System.out.println("End login method of CreditTrackerController");
		 
        return lOutputVO; 
    }  
 
 
 @RequestMapping(value = "/add", method = { RequestMethod.POST })  
 @ResponseBody
 public OutputVO add(@RequestBody CreditCard  objCreditCard,HttpServletRequest request)
 {
	System.out.println("Inside add method of CreditTrackerController");
	OutputVO lOutputVO = new OutputVO();
	
	
   
    List<CreditCard> creditCardList= new ArrayList<CreditCard>();
    boolean result = false;
    try
    {
    	int i = (int) request.getSession().getAttribute("id");
    	
    	objCreditCard.setId(i);
    	if(validateCreditCard(objCreditCard))
    	{
	    result = objUserService.addCreditCard(objCreditCard);
	    System.out.println(result);
	    
	    if(result)
	    {
	    	lOutputVO.setStatus("Credit card added Successfully!!");
	    	lOutputVO.setStatusCode("0");
	    	creditCardList = objUserService.getCreditCardAfterUpdation(objCreditCard);
	    	lOutputVO.setCreditCardList(creditCardList);
	    }
	    else
	    {
	
	    	   
	    	lOutputVO.setStatus("Failed adding Credit card.!");
	    	lOutputVO.setStatusCode("1");
	    }
       }
    }
    catch(Exception e)
    {
    
   
    	lOutputVO.setStatus("Failed adding Credit card.!");
    	lOutputVO.setStatusCode("1");
    	
    }
    System.out.println("End of add method of CreditTrackerController");
    return lOutputVO;  
}
    
      
 @RequestMapping(value = "/update", method = { RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE)  
 
	@ResponseBody
	 public OutputVO update(@RequestBody CreditCard  objCreditCard,HttpServletRequest request)
	 {
		 System.out.println("Inside update method of CreditTrackerController");
	   
	    OutputVO lOutputVO = new OutputVO();
	    int result = 0;   
	    
	    try
	    {
	    	result = objUserService.update(objCreditCard);
	   
	   
	    if(result==1)
	    {
	    	lOutputVO.setStatus("Success updating to Credit Card.!!");
	    	lOutputVO.setStatusCode("0");
	    	
	    }
	    else {
	    	lOutputVO.setStatus("Failure updating Credit Card.!!");
	    	lOutputVO.setStatusCode("1");
	    	
	    }
	    }
	    catch(Exception e)
	    {
	        lOutputVO.setStatus("Failed");
	    	
	    	lOutputVO.setStatusCode("1");
	    	
	
	    	
	    }
	    System.out.println("End update method of CreditTrackerController");
	    return lOutputVO; 
	}  
 
 
 @RequestMapping(value = "/getAllCreditDetails", method = { RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)  
 
	@ResponseBody
	 public OutputVO getAllCreditDetails(HttpServletRequest request)
	 {
		System.out.println("Inside getAllCreditDetails method of CreditTrackerController");
	    List <CreditCard> creditCardList = null;
	    OutputVO lOutputVO = new OutputVO();
	   
	   
	    try
	    {
	    	creditCardList = objUserService.getAllCreditCardAfterUpdation();
	   
	   
	    if(creditCardList!= null)
	    {
	    	lOutputVO.setCreditCardList(creditCardList);
	    	lOutputVO.setStatus("Success fetching Credit Card.!!");
	    	lOutputVO.setStatusCode("0");
	    	
	    }
	    else 
	    {
	    	lOutputVO.setStatus("Failure fetching Credit Card.!!");
	    	lOutputVO.setStatusCode("1");
	    	
	    }
	    }
	    catch(Exception e)
	    {
	        lOutputVO.setStatus("Failed");
	    	
	    	lOutputVO.setStatusCode("1");
	    	
	
	    	
	    }
	    System.out.println("End getAllCreditDetails method of CreditTrackerController");
	    return lOutputVO; 
	}  

 @RequestMapping(value = "/getSelfCreditDetails", method = { RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)  
 
	@ResponseBody
	 public OutputVO getSelfCreditDetails(HttpServletRequest request)
	 {
		System.out.println("Inside getSelfCreditDetails method of CreditTrackerController");
	    List <CreditCard> creditCardList = null;
	    OutputVO lOutputVO = new OutputVO();
	    CreditCard  objCreditCard = new CreditCard();
	   
	    try
	    {
	    	int i = (int) request.getSession().getAttribute("id");
	    	objCreditCard.setId(i);
	    	creditCardList = objUserService.getCreditCardAfterUpdation(objCreditCard);
	   
	   
	    if(creditCardList!= null)
	    {
	    	lOutputVO.setCreditCardList(creditCardList);
	    	lOutputVO.setStatus("Success fetching Credit Card.!!");
	    	lOutputVO.setStatusCode("0");
	    	
	    }
	    else 
	    {
	    	lOutputVO.setStatus("Failure fetching Credit Card.!!");
	    	lOutputVO.setStatusCode("1");
	    	
	    }
	    }
	    catch(Exception e)
	    {
	        lOutputVO.setStatus("Failed");
	    	
	    	lOutputVO.setStatusCode("1");
	    	
	
	    	
	    }
	    System.out.println("End getSelfCreditDetails method of CreditTrackerController");
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
	 
	 
	 public boolean validateCreditCard(CreditCard objCreditCard)
	 {
		 System.out.println("Inside validateCreditCard method of CreditTrackerController");
		 boolean result = false;
		 
		 if(objCreditCard.getCreditCardNumber()!= null
		    )
		 { 
			result = true;
		 }
		 System.out.println(result);
		 
		 System.out.println("End of validateCreditCard method of CreditTrackerController");
		 return result;
	 }
	 
	 
}
