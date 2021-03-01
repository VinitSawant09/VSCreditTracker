package com.vscredittracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
	
	private Logger logger = Logger.getLogger(CreditTrackerController.class);

	@RequestMapping(value = "/",method = RequestMethod.GET)  
	
	 public String landing()
	 {  
		logger.info("Inside landing method of CreditTrackerController");
		logger.info("Redirecting to login page.!!");
       return "index";  
    }  
	
	 @RequestMapping(value = "/signup",method = RequestMethod.GET)  
		
	 public String signup()
	 {  
		 logger.info("Inside signup method of CreditTrackerController");
		 logger.info("Redirecting to Sign up page.!!");
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
	    		logger.info(request.getSession().getAttribute("id"));
	    		logger.info("Redirecting to Home page.!!");
	    	    return "home";
			}
	    	
	    }
	    catch(Exception e)
	    {
	    	logger.error(e);
	    }
	    System.out.println("End home method of CreditTrackerController");
	   
         return "401";
     } 
	 
 @RequestMapping(value = "/adminHome",method = RequestMethod.GET)  
	 
	 public String adminhome(HttpServletRequest request)
	 {  
	 	logger.info("Inside adminhome method of CreditTrackerController");
	    
	    try
	    {
	    	if(request.getSession().getAttribute("id")!="0" && request.getSession().getAttribute("id")!="" )
			{
	    		logger.info(request.getSession().getAttribute("id"));
	    		logger.info("Redirecting to adminhome page.!!");
	    	    return "adminHome";
			}
	    	
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    }
	    logger.info("End adminhome method of CreditTrackerController");
	   
         return "401";
     } 
	 
	 
	 @RequestMapping(value = "/logout",method = RequestMethod.GET)  
	
	 public String logout(HttpServletRequest request)
	 {  
		 logger.info("Inside logout method of CreditTrackerController");
		 logger.info("Redirecting to login page !!");
	    request.getSession().setAttribute("id","0");
	    logger.info("End logout method of CreditTrackerController");
        return "index";  
     }  
	 
	 @RequestMapping(value = "/registerUser", method = { RequestMethod.POST })  
     @ResponseBody
	 public OutputVO registerUser(@RequestBody User  objUser,HttpServletRequest request)
	 {
		 logger.info("Inside registerUser method of CreditTrackerController");
       
        OutputVO lOutputVO = new OutputVO();
        boolean result = false;
        try
        {
	        String userId=objUser.getUserid();
	        String password = objUser.getPassword();
	        
	        if(validatePassword(password) && validateUserId(userId))
	        {
	        	result = objUserService.registerUser(objUser);
		        
		        
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
        
        logger.info("End of registerUser method of CreditTrackerController");
        return lOutputVO; 
         
    }  
	 
	 
 @RequestMapping(value = "/login", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)  
	 
     @ResponseBody
	 public OutputVO login(@RequestBody User  objUser,HttpServletRequest request)
	 {
	    logger.info("Inside login method of CreditTrackerController");
		 
        
       
        OutputVO lOutputVO = new OutputVO();
        
        try 
        {
	        String userId=objUser.getUserid();
	        String password = objUser.getPassword();
	        
	        if(validatePassword(password) && validateUserId(userId))
	        {
	        boolean result = objUserService.validateLogin(objUser);
	       
	       
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
	        	logger.info("Actual user id is :"+id);
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
        logger.info("End login method of CreditTrackerController");
		 
        return lOutputVO; 
    }  
 
 
 @RequestMapping(value = "/add", method = { RequestMethod.POST })  
 @ResponseBody
 public OutputVO add(@RequestBody CreditCard  objCreditCard,HttpServletRequest request)
 {
	 logger.info("Inside add method of CreditTrackerController");
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
    logger.info("End of add method of CreditTrackerController");
    return lOutputVO;  
}
    
      
 @RequestMapping(value = "/update", method = { RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE)  
 
	@ResponseBody
	 public OutputVO update(@RequestBody CreditCard  objCreditCard,HttpServletRequest request)
	 {
	    logger.info("Inside update method of CreditTrackerController");
	   
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
	    logger.info("End update method of CreditTrackerController");
	    return lOutputVO; 
	}  
 
 
 @RequestMapping(value = "/getAllCreditDetails", method = { RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)  
 
	@ResponseBody
	 public OutputVO getAllCreditDetails(HttpServletRequest request)
	 {
	    logger.info("Inside getAllCreditDetails method of CreditTrackerController");
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
	    logger.info("End getAllCreditDetails method of CreditTrackerController");
	    return lOutputVO; 
	}  

 @RequestMapping(value = "/getSelfCreditDetails", method = { RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)  
 
	@ResponseBody
	 public OutputVO getSelfCreditDetails(HttpServletRequest request)
	 {
	    logger.info("Inside getSelfCreditDetails method of CreditTrackerController");
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
	    logger.info("End getSelfCreditDetails method of CreditTrackerController");
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
		 logger.info("Inside validateCreditCard method of CreditTrackerController");
		 boolean result = false;
		 
		 if(objCreditCard.getCreditCardNumber()!= null
		    )
		 { 
			result = true;
		 }
		 logger.info(result);
		 
		 logger.info("End of validateCreditCard method of CreditTrackerController");
		 return result;
	 }
	 
	 
}
